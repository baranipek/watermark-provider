package com.watermark.model.factory;


import com.watermark.model.domain.Book;
import com.watermark.model.domain.Journal;
import com.watermark.model.domain.Watermark;
import com.watermark.model.entity.Document;
import com.watermark.model.enumeration.ContentEnum;
import com.watermark.request.WatermarkRequestDto;

public class DocumentFactory {
    public static Document getDocumentType(WatermarkRequestDto requestDto, Integer sequenceIcrementar) {
        Document document = null;
        if (requestDto instanceof Journal) {
            document = Document.builder().title(requestDto.getTitle()).author(requestDto.getAuthor()).watermark(null).id(sequenceIcrementar).build();

        } else if (requestDto instanceof Book) {
            document = Document.builder().title(requestDto.getTitle()).author(requestDto.getAuthor()).watermark(null).id(sequenceIcrementar).build();
        }
        return document;
    }

    public static Watermark getWatermarkType(WatermarkRequestDto requestDto) {
        Watermark watermark = null;
        if (requestDto instanceof Journal) {
            watermark = new Watermark(requestDto.getTitle(), requestDto.getAuthor(), ContentEnum.journal, null);

        } else if (requestDto instanceof Book) {
            watermark = new Watermark(requestDto.getTitle(), requestDto.getAuthor(), ContentEnum.book, ((Book) requestDto).getTopic());
            ;
        }
        return watermark;
    }

}

