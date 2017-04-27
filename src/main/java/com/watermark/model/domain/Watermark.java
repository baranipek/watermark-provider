package com.watermark.model.domain;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.watermark.model.enumeration.ContentEnum;
import com.watermark.model.enumeration.TopicEnum;
import com.watermark.request.WatermarkRequestDto;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Watermark extends WatermarkRequestDto{

    ContentEnum content;

    TopicEnum topic;

    public Watermark(String title, String author,ContentEnum content, TopicEnum topic) {
        super(title,author);
        this.content =content;
        this.topic=topic;
    }
}
