package com.tvd12.ezydata.example.jpa.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Builder
public class AddBookData {
    private final Long categoryId;
    private final Long authorId;
    private final String bookName;
    private final BigDecimal price;
    private final LocalDate releaseDate;
    private final LocalDateTime releaseTime;
}