package com.watermark.repository;

import com.watermark.model.entity.Document;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DocumentRepository extends MongoRepository<Document, Long> {
}