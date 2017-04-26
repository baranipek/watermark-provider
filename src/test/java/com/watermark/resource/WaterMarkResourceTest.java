
package com.watermark.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.watermark.config.MongoConfig;
import com.watermark.model.domain.Book;
import com.watermark.model.domain.Journal;
import com.watermark.model.domain.Watermark;
import com.watermark.model.enumeration.TopicEnum;
import com.watermark.model.response.TicketIdResponse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.MockMvcPrint;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(print = MockMvcPrint.LOG_DEBUG)
public class WaterMarkResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MongoConfig mongoConfig;

    @Test
    public void postBookAndReturnTicketIdAsExpected() throws Exception {
        AtomicInteger ticketIds = new AtomicInteger();
        Watermark book = new Book("Book", "Stephen Hawking", TopicEnum.Business);
        MvcResult result = mockMvc.perform(post("/watermark/book").
                contentType(MediaType.APPLICATION_JSON_UTF8).
                content(convertObjectToJson(book))).
                andExpect(status().isCreated()).andReturn();
        String content = result.getResponse().getContentAsString();
        TicketIdResponse response = TicketIdResponse.builder().id(ticketIds.incrementAndGet()).build();
        Assert.assertTrue(content.contains(response.getId().toString()));

    }

    @Test
    public void postJournalAndReturnTicketIdAsExpected() throws Exception {
        AtomicInteger ticketIds = new AtomicInteger();
        Watermark journal = new Journal("Journal", "Stephen Hawking");
        MvcResult result = mockMvc.perform(post("/watermark/journal").
                contentType(MediaType.APPLICATION_JSON_UTF8).
                content(convertObjectToJson(journal))).
                andExpect(status().isCreated()).andReturn();
        String content = result.getResponse().getContentAsString();
        TicketIdResponse response = TicketIdResponse.builder().id(ticketIds.incrementAndGet()).build();
        Assert.assertTrue(content.contains(response.getId().toString()));
        mongoConfig.destroy();
    }

    @Test
    public void getWatermarkWithoutId() throws Exception {
        mockMvc.perform(get("/watermark")).
                andExpect(status().isNotFound());
    }

    @Test
    public void ticketNotFoundWithWrongId() throws Exception {
        mockMvc.perform(get("/watermark/13113213")).
                andExpect(status().isNotFound());
    }

    @Test
    public void waterBookWatermarkIsNotReadyInCaseOfConsecutiveCalls() throws Exception {
        Watermark book = new Book("Book", "Stephen Hawking", TopicEnum.Business);
        MvcResult result = mockMvc.perform(post("/watermark/book").
                contentType(MediaType.APPLICATION_JSON_UTF8).
                content(convertObjectToJson(book))).
                andExpect(status().isCreated()).andReturn();
        String content = result.getResponse().getContentAsString();

        mockMvc.perform(get("/watermark/" + getDigits(content)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());

    }

    @Test
    public void watermarkIsReadyAfterFiveSeconds() throws Exception {
        Watermark book = new Book("Stephen", "Computer", TopicEnum.Business);
        MvcResult result = mockMvc.perform(post("/watermark/book").
                contentType(MediaType.APPLICATION_JSON_UTF8).
                content(convertObjectToJson(book))).
                andExpect(status().isCreated()).andReturn();
        String content = result.getResponse().getContentAsString();

        Thread.sleep(5000);
        mockMvc.perform(get("/watermark/" + getDigits(content)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").value("book"));


    }

    private static byte[] convertObjectToJson(Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsBytes(object);
    }

    private String getDigits(String input) {
        int length = input.length();
        String result = "";
        for (int i = 0; i < length; i++) {
            Character character = input.charAt(i);
            if (Character.isDigit(character)) {
                result += character;
            }
        }
        return result;
    }

}
