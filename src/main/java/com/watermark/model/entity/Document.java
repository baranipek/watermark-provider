package com.watermark.model.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.watermark.model.domain.Watermark;
import lombok.Builder;

@org.springframework.data.mongodb.core.mapping.Document(collection = "documents")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Document extends BaseDocument<Integer> {

    public String author;

    public String title;

    public Watermark watermark;


    @Builder
    public Document(Integer id, String author, String title, Watermark watermark) {
        super.id = id;
        this.author = author;
        this.title = title;
        this.watermark = watermark;
    }

}
