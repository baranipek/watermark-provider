package com.watermark.model.domain;

import com.watermark.request.WatermarkRequestDto;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Journal extends WatermarkRequestDto {
    public Journal(String title, String author) {
        super(title,author);
    }
}
