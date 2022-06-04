package com.tvd12.ezydata.example.mongo.controller;

import com.tvd12.ezydata.database.repository.EzyMaxIdRepository;
import com.tvd12.ezydata.example.mongo.converter.EntityToResponseConverter;
import com.tvd12.ezydata.example.mongo.converter.RequestToEntityConverter;
import com.tvd12.ezydata.example.mongo.entity.Author;
import com.tvd12.ezydata.example.mongo.entity.Book;
import com.tvd12.ezydata.example.mongo.entity.BookSummary;
import com.tvd12.ezydata.example.mongo.entity.Category;
import com.tvd12.ezydata.example.mongo.repository.AuthorRepository;
import com.tvd12.ezydata.example.mongo.repository.BookRepository;
import com.tvd12.ezydata.example.mongo.repository.BookSummaryRepository;
import com.tvd12.ezydata.example.mongo.repository.CategoryRepository;
import com.tvd12.ezydata.example.mongo.request.AddBookRequest;
import com.tvd12.ezydata.example.mongo.response.BookResponse;
import com.tvd12.ezyfox.io.EzyStrings;
import com.tvd12.ezyfox.util.Next;
import com.tvd12.ezyhttp.core.exception.HttpBadRequestException;
import com.tvd12.ezyhttp.core.exception.HttpNotFoundException;
import com.tvd12.ezyhttp.server.core.annotation.*;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@AllArgsConstructor
@Controller("/api/v1")
public class BookController {
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final BookSummaryRepository bookSummaryRepository;
    private final CategoryRepository categoryRepository;
    private final EzyMaxIdRepository maxIdRepository;
    private final EntityToResponseConverter entityToResponseConverter;
    private final RequestToEntityConverter requestToEntityConverter;

    @DoPost("/book/add")
    public BookResponse addBook(@RequestBody AddBookRequest request) {
        Book existedBook = bookRepository.findByNameAndAuthorId(
            request.getBookName(),
            request.getAuthorId()
        );
        if (existedBook != null) {
            throw new HttpBadRequestException(
                "author: " + request.getAuthorId() +
                    " has already registered book: " + request.getBookName()
            );
        }
        Author author = authorRepository.findById(request.getAuthorId());
        if (author == null) {
            throw new HttpBadRequestException(
                "author: " + request.getAuthorId() + " not found"
            );
        }
        Category category = categoryRepository.findById(request.getCategoryId());
        if (category == null) {
            throw new HttpBadRequestException(
                "category: " + request.getCategoryId() + " not found"
            );
        }

        long bookId = maxIdRepository.incrementAndGet("book");
        Book book = requestToEntityConverter.toBookEntity(request, bookId);
        bookRepository.save(book);
        bookSummaryRepository.save(
            new BookSummary(
                bookId,
                book,
                author,
                Collections.singletonList(category)
            )
        );
        return entityToResponseConverter.toBookResponse(book, author, category);
    }

    @DoGet("/books/{bookId}")
    public BookResponse getBook(@PathVariable Long bookId) {
        Book book = bookRepository.findById(bookId);
        if (book == null) {
            throw new HttpNotFoundException("not found book with id: " + bookId);
        }
        Author author = authorRepository.findById(book.getAuthorId());
        Category category = categoryRepository.findById(book.getCategoryId());
        return entityToResponseConverter.toBookResponse(
            book,
            author,
            category
        );
    }

    @DoGet("/books/{bookId}/summary")
    public BookSummary getBookSummary(@PathVariable Long bookId) {
        BookSummary book = bookSummaryRepository.findById(bookId);
        if (book == null) {
            throw new HttpNotFoundException("not found book with id: " + bookId);
        }
        return book;
    }

    @DoGet("/books")
    public List<BookResponse> getBooks(
        @RequestParam("lower_than") String lowerThan,
        @RequestParam("upper_than") String upperThan,
        @RequestParam("size") int size
    ) {
        List<Book> books = null;
        if (EzyStrings.isEmpty(upperThan)) {
            books = EzyStrings.isEmpty(lowerThan)
                ? bookRepository.findBooks(Next.fromSkipLimit(0, size))
                : bookRepository.findByNameLt(lowerThan, Next.fromSkipLimit(0, size));
        } else {
            books = bookRepository.findByNameGt(upperThan, Next.fromSkipLimit(0, size));
        }
        return decorateBooksToResponse(books);
    }

    @DoGet("/books/in")
    public List<BookResponse> getBooks(@RequestParam("ids") Long[] bookIds) {
        List<Book> books = bookRepository.findListByIds(Arrays.asList(bookIds));
        return decorateBooksToResponse(books);
    }

    private List<BookResponse> decorateBooksToResponse(List<Book> books) {
        List<Long> authorIds = books.stream()
            .map(Book::getAuthorId)
            .collect(Collectors.toList());
        List<Long> categoryIds = books.stream()
            .map(Book::getCategoryId)
            .collect(Collectors.toList());
        Map<Long, Author> authors = authorRepository.findListByIds(authorIds)
            .stream()
            .collect(Collectors.toMap(Author::getId, it -> it));
        Map<Long, Category> categories = categoryRepository.findListByIds(categoryIds)
            .stream()
            .collect(Collectors.toMap(Category::getId, it -> it));
        return entityToResponseConverter.toBooksResponse(
            books,
            authors,
            categories
        );
    }

    @DoGet("/books/expected-revenue")
    public BigDecimal getExpectedRevenue() {
        return bookRepository.sumPrice().getSum();
    }

    @DoGet("/books/count-by-author-id")
    public int getCountByAuthorId(@RequestParam long authorId) {
        return bookRepository.countByAuthorId(authorId);
    }

    @DoGet("/books/count-by-author-id-gt")
    public int getCountByAuthorIdGt(@RequestParam long authorId) {
        return bookRepository.countByAuthorIdGt(authorId);
    }

    @DoDelete("/books/delete-by-author-id-gt")
    public boolean deleteByAuthorIdGt(@RequestParam long authorId) {
        bookRepository.deleteByAuthorIdGt(authorId);
        return Boolean.TRUE;
    }
}