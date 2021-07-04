package com.tvd12.ezyfox.example.bean.repository;

import com.tvd12.ezyfox.bean.annotation.EzySingleton;
import com.tvd12.ezyfox.example.bean.controller.BookController;
import com.tvd12.ezyfox.example.bean.entity.Book;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@EzySingleton
public class BookRepository {

    private final BookController bookController;

    public void save(Book book) {
        System.out.println("saved book: " + book);
    }
}
