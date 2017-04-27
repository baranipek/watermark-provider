package com.watermark.service.impl;

import com.watermark.exception.DocumentNotfoundException;
import com.watermark.exception.WaterMarkNotCompletedException;
import com.watermark.model.domain.Watermark;
import com.watermark.model.entity.Document;
import com.watermark.model.factory.DocumentFactory;
import com.watermark.model.response.TicketIdResponse;
import com.watermark.repository.DocumentRepository;
import com.watermark.request.WatermarkRequestDto;
import com.watermark.service.WaterMarkService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.concurrent.atomic.AtomicInteger;

@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Slf4j
@Service
public class WaterMarkServiceImpl implements WaterMarkService {

    private AtomicInteger sequenceIncrement;

    @PostConstruct
    void init() {
        sequenceIncrement = new AtomicInteger();
    }

    @Resource
    private DocumentRepository documentRepository;

    @Override
    public Document getWatermarkByTicketId(Long id) {
        Document document = documentRepository.findOne(id);
        if (document == null) {
            throw new DocumentNotfoundException("Document Not Found");
        } else if (document.watermark == null) {
            throw new WaterMarkNotCompletedException("Watermark is still processing");
        }
        return document;
    }

    @Override
    public TicketIdResponse createJournalWatermark(WatermarkRequestDto watermark) {
        Document document = DocumentFactory.getDocumentType(watermark, sequenceIncrement.incrementAndGet());
        documentRepository.save(document);

        //simulate watermark operations takes 5 seconds
        Document updatedDocument = document;
        Thread t = new Thread() {
            public void run() {
                handleWaterMarkOperations(updatedDocument, watermark);
            }
        };
        t.start();
        return TicketIdResponse.builder().id(document.getId()).build();
    }

    protected void handleWaterMarkOperations(Document document, WatermarkRequestDto watermarkRequestDto) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            log.debug("Watermark is generated Error {}");
        }
        Watermark watermark=DocumentFactory.getWatermarkType(watermarkRequestDto);
        document.watermark = watermark;
        documentRepository.save(document);
    }

}
