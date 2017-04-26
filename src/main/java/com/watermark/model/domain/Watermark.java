package com.watermark.model.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Watermark {
    @NotNull
    @Size(max = 500)
    public String title;

    @NotNull
    @Size(max = 500)
    public String author;
}
