package com.watermark.service;


import com.watermark.model.entity.Document;
import com.watermark.model.domain.Watermark;
import com.watermark.model.response.TicketIdResponse;

public interface WaterMarkService {

    Document getWatermarkByTicketId(Long id);

    TicketIdResponse createJournalWatermark(Watermark requestDto);
}
