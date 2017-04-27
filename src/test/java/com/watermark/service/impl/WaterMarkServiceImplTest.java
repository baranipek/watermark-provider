package com.watermark.service.impl;

import com.watermark.exception.DocumentNotfoundException;
import com.watermark.exception.WaterMarkNotCompletedException;
import com.watermark.model.domain.Watermark;
import com.watermark.model.entity.Document;
import com.watermark.repository.DocumentRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.MockMvcPrint;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@SpringBootTest
@AutoConfigureMockMvc(print = MockMvcPrint.LOG_DEBUG)
@RunWith(MockitoJUnitRunner.class)
public class WaterMarkServiceImplTest {

    @Mock
    private DocumentRepository documentRepository;

    @InjectMocks
    private WaterMarkServiceImpl service;

    private static AtomicInteger ticketIds;

    @Before
    public void setUp() throws Exception {
        ticketIds = new AtomicInteger();
    }

    @Test
    public void getWatermarkByTicketId() throws Exception {
        Document document = mock(Document.class);
        Watermark watermark = mock(Watermark.class);
        document.watermark = watermark;
        when(documentRepository.findOne(1L)).thenReturn(document);
        service.getWatermarkByTicketId(1L);
        assertEquals(document, document);

    }

    @Test(expected = DocumentNotfoundException.class)
    public void throwDocumentNotfoundExceptionWhenWatermarkIsNotFound() throws Exception {
        when(documentRepository.findOne(anyLong())).thenReturn(null);
        service.getWatermarkByTicketId(1L);
    }

    @Test(expected = WaterMarkNotCompletedException.class)
    public void throwWaterMarkNotCompletedExceptionWhenWatermarkIsOnProgress() throws Exception {
        Document document = mock(Document.class);
        when(documentRepository.findOne(1L)).thenReturn(document);
        service.getWatermarkByTicketId(1L);
        assertEquals(document, document);

    }

}