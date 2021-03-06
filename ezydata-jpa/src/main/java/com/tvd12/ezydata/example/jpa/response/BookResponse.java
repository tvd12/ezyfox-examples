package com.tvd12.ezydata.example.jpa.response;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BookResponse {
    private final Long bookId;
    private final CategoryResponse category;
    private final AuthorResponse author;
    private final String bookName;
    private final BigDecimal price;
    private final LocalDate releaseDate;
    private final LocalDateTime releaseTime;
}