package com.watermark.model.factory;


import com.watermark.model.domain.Book;
import com.watermark.model.domain.Journal;
import com.watermark.model.entity.Document;
import com.watermark.model.enumeration.ContentEnum;
import com.watermark.model.domain.Watermark;


public class DocumentFactory {
    public static Document getDocumentType(Watermark requestDto, Integer sequenceIcrementar) {
        Document document = null;
        if (requestDto instanceof Journal) {
            document = Document.builder().title(requestDto.getTitle()).author(requestDto.getAuthor())
                    .content(ContentEnum.journal).watermark(null).id(sequenceIcrementar).build();

        } else if (requestDto instanceof Book){
            document = Document.builder().title(requestDto.getTitle()).author(requestDto.getAuthor())
                    .content(ContentEnum.book).topic(((Book) requestDto).getTopic()).
                            watermark(null).id(sequenceIcrementar).build();
        }
        return document;
    }
}

