package com.watermark.model.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class Document {
    @JsonIgnore
    protected String title;
    @JsonIgnore
    protected String author;
}
