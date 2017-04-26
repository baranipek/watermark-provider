package com.watermark.model.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Journal extends Watermark {
    public Journal(String title, String author) {
        super(title,author);
    }
}
