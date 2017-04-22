package com.watermark.model.response;


import com.watermark.model.domain.Book;
import com.watermark.model.domain.Document;
import com.watermark.model.domain.Journal;
import com.watermark.model.enumeration.ContentType;
import com.watermark.model.enumeration.TopicType;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WaterMarkResponseDto extends Document {

    private ContentType content;
    private TopicType topic;

    @Builder
    public WaterMarkResponseDto(String title, String author, TopicType topic, ContentType content) {
        super(title, author);
        this.topic = topic;
        this.content = content;
    }

    @Builder
    public WaterMarkResponseDto(String title, String author, ContentType content) {
        super(title, author);
        this.content = content;
    }


    public static WaterMarkResponseDto convertFromDto(Document document) {
        WaterMarkResponseDto waterMarkResponseDto = null;
        if (document instanceof Book) {
            Book book = (Book) document;
            waterMarkResponseDto = com.watermark.model.response.WaterMarkResponseDto.builder().author(book.getAuthor()).
                    content(ContentType.book).title(book.getTitle()).topic(book.getTopic()).build();
        } else if (document instanceof Journal) {
            Journal journal = (Journal) document;
            waterMarkResponseDto = com.watermark.model.response.WaterMarkResponseDto.builder().author(journal.getAuthor()).
                    content(ContentType.journal).title(journal.getTitle()).build();
        }
        return waterMarkResponseDto;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("{content:" +'"'+content+'"').
                append(",title:" + ""+'"'+super.title+'"')
                .append(",author:" +  '"'+super.author+'"');
        if (topic != null) sb.append(",topic:" + '"'+topic+'"'+"}");
        else sb.append("}");
        return sb.toString();
    }
}
