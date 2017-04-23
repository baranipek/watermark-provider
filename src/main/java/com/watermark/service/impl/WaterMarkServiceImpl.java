package com.watermark.service.impl;

import com.watermark.exception.TicketNotfoundException;
import com.watermark.exception.WaterMarkNotCompletedException;
import com.watermark.model.domain.Document;
import com.watermark.model.response.WaterMarkResponseDto;
import com.watermark.service.WaterMarkService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Slf4j
@Service
public class WaterMarkServiceImpl implements WaterMarkService {
    private ConcurrentMap<Integer, WaterMarkResponseDto> ticketMapStore;
    private AtomicInteger ticketIds;


    @PostConstruct
    void init() {
        ticketMapStore = new ConcurrentHashMap<>();
        ticketIds = new AtomicInteger();
    }

    @Override
    public int generateWaterMark(Document document) {
        int ticketId = ticketIds.incrementAndGet();
        log.info("Creating ticket {} for document {}.", ticketId, document);

        //simulate watermark operations takes 5 seconds
        Thread t = new Thread() {
            public void run() {
                handleWaterMarkOperations(ticketMapStore, ticketId, document);
            }
        };
        t.start();

        return ticketId;
    }

    @Override
    public WaterMarkResponseDto getWatermarkByTicketId(Integer id) throws TicketNotfoundException, WaterMarkNotCompletedException {
        if (ticketMapStore == null)
            throw new TicketNotfoundException("Ticket Not Found");
        if (ticketMapStore.get(id) == null)
            throw new WaterMarkNotCompletedException("Watermark still processing");
        return ticketMapStore.get(id);
    }

    private void handleWaterMarkOperations(ConcurrentMap<Integer, WaterMarkResponseDto> ticketMapStore,
                                           int ticketId, Document document) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            log.debug("Watermark is generated Error {}", document);
        }
        WaterMarkResponseDto waterMarkResponseDto = WaterMarkResponseDto.convertFromDto(document);
        ticketMapStore.putIfAbsent(ticketId, waterMarkResponseDto);

        log.info("Watermark is generated for the document {}", document);
    }

}
