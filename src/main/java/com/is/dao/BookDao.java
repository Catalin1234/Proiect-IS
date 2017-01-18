package com.is.dao;

import com.is.model.Book;

import java.util.List;

/**
 * Created by catal on 1/18/2017.
 */
public interface BookDao {

    List<Book> getAllBooks();
    void deleteBook(int bookId);
    void updateBook(Book book);
    void addBook(Book book);

}
