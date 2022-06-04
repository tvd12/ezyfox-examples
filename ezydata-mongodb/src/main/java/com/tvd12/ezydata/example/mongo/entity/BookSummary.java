package com.tvd12.ezydata.example.mongo.entity;

import com.tvd12.ezyfox.annotation.EzyId;
import com.tvd12.ezyfox.database.annotation.EzyCollection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@EzyCollection
@AllArgsConstructor
@NoArgsConstructor
public class BookSummary {
    @EzyId
    private long bookId;
    private Book book;
    private Author author;
    private List<Category> categories;
}
