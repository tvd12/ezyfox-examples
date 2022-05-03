package com.tvd12.ezydata.example.redis.response;

import com.tvd12.ezydata.example.redis.entity.Author;
import com.tvd12.ezydata.example.redis.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@AllArgsConstructor
public class BookResponse {
    private Long bookId;
    private Category category;
    private Author author;
    private String bookName;
    private BigDecimal price;
    private Date releaseTime;
}