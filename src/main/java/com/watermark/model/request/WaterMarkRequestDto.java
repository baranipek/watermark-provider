package com.watermark.model.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Optional;
import com.watermark.model.domain.Book;
import com.watermark.model.domain.Document;
import com.watermark.model.domain.Journal;
import com.watermark.model.enumeration.TopicType;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class WaterMarkRequestDto {
    @JsonIgnore
    private Document document;

    @JsonCreator
    public WaterMarkRequestDto(@JsonProperty("title") String title,
                               @JsonProperty("author") String author,
                               @JsonProperty("topic") TopicType topic) {
        if (topic == null) {
            document = Journal.builder().author(author).title(title).build();
        } else {
            document = Book.builder().topic(topic).author(author).title(title).build();
        }

    }


}
