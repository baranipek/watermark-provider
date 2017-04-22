package com.watermark.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.watermark.model.domain.Book;
import com.watermark.model.domain.Document;
import com.watermark.model.enumeration.TopicType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
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
    private static AtomicInteger ticketIds;

    @Before
    public void setUp() throws Exception {
        ticketIds = new AtomicInteger();
    }

    @Test
    public void postWatermarkAndReturnTicketIdAsExpected() throws Exception {
        Document book = new Book("Book", "Stephen Hawking", TopicType.Business);
        MvcResult result = mockMvc.perform(post("/watermark").
                contentType(MediaType.APPLICATION_JSON_UTF8).
                content(convertObjectToJson(book))).
                andExpect(status().isCreated()).andReturn();
        String content = result.getResponse().getContentAsString();
        int returnedTicket = Integer.parseInt(content);
        Assert.assertEquals(ticketIds.incrementAndGet(), returnedTicket);
    }


    @Test
    public void getWatermarkWithoutId() throws Exception {
        mockMvc.perform(get("/watermark")).
                andExpect(status().isMethodNotAllowed());
    }

    @Test
    public void ticketNotFoundWithWrongId() throws Exception {
        mockMvc.perform(get("/watermark/13113213")).
                andExpect(status().isNotFound());
    }

    @Test()
    public void waterMarkIsNotReadyInCaseOfConsecutiveCalls() throws Exception {
        Document book = new Book("Book", "Stephen Hawking", TopicType.Business);
        MvcResult result = mockMvc.perform(post("/watermark").
                contentType(MediaType.APPLICATION_JSON_UTF8).
                content(convertObjectToJson(book))).
                andExpect(status().isCreated()).andReturn();
        String content = result.getResponse().getContentAsString();

        mockMvc.perform(get("/watermark/" + content))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void waterMarkIsReadyAfterFifteenSeconds() throws Exception {
        Document book = Book.builder().author("Stephen").title("Computer").
                topic(TopicType.Business).build();
        MvcResult result = mockMvc.perform(post("/watermark").
                contentType(MediaType.APPLICATION_JSON_UTF8).
                content(convertObjectToJson(book))).
                andExpect(status().isCreated()).andReturn();
        String content = result.getResponse().getContentAsString();

        Thread.sleep(15000);
        mockMvc.perform(get("/watermark/" + content))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").value("book"));

    }


    private static byte[] convertObjectToJson(Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsBytes(object);
    }

}