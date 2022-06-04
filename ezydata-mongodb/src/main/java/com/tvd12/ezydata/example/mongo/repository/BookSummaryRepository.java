package com.tvd12.ezydata.example.mongo.repository;

import com.tvd12.ezydata.example.mongo.entity.BookSummary;
import com.tvd12.ezydata.mongodb.EzyMongoRepository;
import com.tvd12.ezyfox.database.annotation.EzyRepository;

@EzyRepository
public interface BookSummaryRepository extends EzyMongoRepository<Long, BookSummary> {}