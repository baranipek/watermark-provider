package com.watermark.service.impl;

import com.watermark.dao.DocumentRepository;
import com.watermark.model.entity.Document;
import com.watermark.model.request.Watermark;
import com.watermark.model.response.TicketIdResponse;
import com.watermark.service.WaterMarkService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Slf4j
@Service
public class WaterMarkServiceImpl implements WaterMarkService {
    @Resource
    private DocumentRepository documentRepository;


    @Override
    public Document getWatermarkByTicketId(Long id) {
        return null;
    }


    @Override
    public TicketIdResponse createJournalWatermark(Watermark requestDto) {
        Document document = null;


        //simulate watermark operations takes 5 seconds
        Document finalDocument = document;
        Thread t = new Thread() {
            public void run() {
                handleWaterMarkOperations(finalDocument);
            }
        };
        t.start();
        return null;
    }

    private void handleWaterMarkOperations(Document document) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            log.debug("Watermark is generated Error {}");
        }


    }

}
