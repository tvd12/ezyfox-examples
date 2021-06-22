package com.tvd12.ezyfox.example.bean;

import com.tvd12.ezyfox.bean.EzyBeanContext;
import com.tvd12.ezyfox.example.bean.controller.BookController;
import com.tvd12.ezyfox.example.bean.entity.Book;

public class EzyFoxBeanExample {
    public static void main(String[] args) {
        final EzyBeanContext beanContext = EzyBeanContext.builder()
            .scan("com.tvd12.ezyfox.example.bean")
            .build();
        final BookController bookController =
            (BookController)beanContext.getBean(BookController.class);
        bookController.saveBook(new Book(1L, "EzyFox in action"));
    }
}
