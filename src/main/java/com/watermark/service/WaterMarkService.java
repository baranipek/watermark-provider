package com.watermark.service;


import com.watermark.model.entity.Document;
import com.watermark.model.response.TicketIdResponse;
import com.watermark.request.WatermarkRequestDto;

public interface WaterMarkService {

    Document getWatermarkByTicketId(Long id);

    TicketIdResponse createJournalWatermark(WatermarkRequestDto requestDto);
}
