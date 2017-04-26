package com.watermark;

import com.watermark.dao.DocumentRepository;
import com.watermark.model.domain.Book;
import com.watermark.model.entity.BaseDocument;
import com.watermark.model.entity.Document;
import com.watermark.model.enumeration.ContentEnum;
import com.watermark.model.enumeration.TopicEnum;
import com.watermark.model.request.Watermark;
import com.watermark.service.WaterMarkService;
import org.bson.types.ObjectId;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@EnableSwagger2
@EnableMongoRepositories
@SpringBootApplication
public class WatermarkProviderApplication {

	public static void main(String[] args) {

		SpringApplication.run(WatermarkProviderApplication.class, args);
	}

	@Resource
	private DocumentRepository repository;



	@PostConstruct
	public void loadData() {


		Book book = new Book("asdsad","asdasd",TopicEnum.Business);

		Document document = new Document(2,"qweqwe","asdas",book,
				ContentEnum.book,TopicEnum.Business);


		repository.save(document);

		Document document1 = repository.findOne(2L);

		System.out.println();




	}

}
