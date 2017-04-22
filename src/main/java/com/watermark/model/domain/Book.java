package com.watermark.model.domain;


import com.watermark.model.enumeration.TopicType;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper=true)
public class Book extends Document {
    private TopicType topic;
    @Builder
    public Book(String title, String author,TopicType  topic){
        super(title,author);
        this.topic = topic;
    }
}
