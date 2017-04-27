package com.watermark.model.domain;


import com.watermark.model.enumeration.TopicEnum;
import com.watermark.request.WatermarkRequestDto;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Book  extends WatermarkRequestDto {
    private TopicEnum topic;

    public Book(String title, String author,TopicEnum topic) {
        super(title,author);
        this.topic = topic;
    }
}
