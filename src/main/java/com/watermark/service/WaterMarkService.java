package com.watermark.service;


import com.watermark.exception.TicketNotfoundException;
import com.watermark.exception.WaterMarkNotCompletedException;
import com.watermark.model.domain.Document;
import com.watermark.model.response.WaterMarkResponseDto;

public interface WaterMarkService {
    int generateWaterMark(Document document);

    WaterMarkResponseDto getWatermarkByTicketId(Integer id) throws TicketNotfoundException, WaterMarkNotCompletedException;
}
