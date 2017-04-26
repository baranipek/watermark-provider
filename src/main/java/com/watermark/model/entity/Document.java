package com.watermark.model.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.watermark.model.domain.Watermark;
import com.watermark.model.enumeration.ContentEnum;
import com.watermark.model.enumeration.TopicEnum;
import lombok.Builder;

@org.springframework.data.mongodb.core.mapping.Document(collection = "documents")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Document extends BaseDocument<Integer> {

    public String author;

    public String title;

    public Watermark watermark;

    public ContentEnum content;

    public TopicEnum topic;

    @Builder
    public Document(Integer id, String author, String title, Watermark watermark,
             ContentEnum content, TopicEnum topic) {
        super.id = id;
        this.author = author;
        this.title = title;
        this.watermark = watermark;
        this.content = content;
        this.topic = topic;
    }

}
