package com.tvd12.ezyfox.example.bean.controller;

import com.tvd12.ezyfox.bean.annotation.EzySingleton;
import com.tvd12.ezyfox.example.bean.entity.Book;
import com.tvd12.ezyfox.example.bean.service.BookService;
import lombok.AllArgsConstructor;

@EzySingleton
@AllArgsConstructor
public class BookController {
    private final BookService bookService;

    public void saveBook(Book book) {
        bookService.saveBook(book);
    }
}
