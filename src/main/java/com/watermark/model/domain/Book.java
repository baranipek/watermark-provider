package com.watermark.model.domain;


import com.watermark.model.enumeration.TopicEnum;
import com.watermark.model.request.Watermark;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Book  extends Watermark {
    private TopicEnum topic;

    public Book(String title, String author,TopicEnum topic) {
        super(title,author);
        this.topic = topic;
    }
}
