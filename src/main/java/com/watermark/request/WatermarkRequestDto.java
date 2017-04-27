package com.watermark.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class WatermarkRequestDto {
    @NotNull
    @Size(max = 500)
    String title;

    @NotNull
    @Size(max = 500)
    String author;
}
