package com.watermark.model.domain;


import com.watermark.model.enumeration.TopicEnum;
import com.watermark.model.request.DocumentRequestDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book  extends DocumentRequestDto{
    private TopicEnum topic;
}
