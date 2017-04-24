package com.watermark.service.impl;

import com.watermark.dao.DocumentDao;
import com.watermark.exception.TicketNotfoundException;
import com.watermark.exception.WaterMarkNotCompletedException;
import com.watermark.model.entity.Document;
import com.watermark.model.factory.DocumentFactory;
import com.watermark.model.request.DocumentRequestDto;
import com.watermark.model.response.TicketIdResponse;
import com.watermark.service.WaterMarkService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Slf4j
@Service
public class WaterMarkServiceImpl implements WaterMarkService {

    private DocumentDao documentDao;

    @Autowired
    public WaterMarkServiceImpl(DocumentDao documentDao) {this.documentDao = documentDao;}

    @Override
    public Document getWatermarkByTicketId(Long id) {
        Document document = documentDao.findDocument(id);
        if (document == null)
            throw new TicketNotfoundException("Ticket Not Found");
        else if (!document.hasWatermark)
            throw new WaterMarkNotCompletedException("Watermark still processing");
        return documentDao.findDocument(id);
    }


    @Override
    public TicketIdResponse createJournalWatermark(DocumentRequestDto requestDto) {
        Document document = DocumentFactory.getDocumentType(requestDto);
        documentDao.create(document);

        //simulate watermark operations takes 5 seconds
        Document finalDocument = document;
        Thread t = new Thread() {
            public void run() {
                handleWaterMarkOperations(finalDocument, documentDao);
            }
        };
        t.start();
        return TicketIdResponse.builder().id(document.getId()).build();
    }

    private void handleWaterMarkOperations(Document document, DocumentDao documentDaoThread) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            log.debug("Watermark is generated Error {}");
        }
        document.hasWatermark = true;
        documentDaoThread.update(document);
    }

}
