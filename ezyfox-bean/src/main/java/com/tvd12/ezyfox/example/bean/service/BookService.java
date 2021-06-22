package com.tvd12.ezyfox.example.bean.service;

import com.tvd12.ezyfox.bean.annotation.EzySingleton;
import com.tvd12.ezyfox.example.bean.entity.Book;
import com.tvd12.ezyfox.example.bean.repository.BookRepository;
import lombok.AllArgsConstructor;

@EzySingleton
@AllArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public void saveBook(Book book) {
        bookRepository.save(book);
    }
}
