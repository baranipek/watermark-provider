package com.watermark.model.domain;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper=true)
public class Journal extends Document {
    @Builder
    public Journal(String title, String author){
        super(title,author);
    }
}
