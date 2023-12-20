package com.example.dukacatalogue.service;

import com.example.dukacatalogue.entity.book.Book;

import java.util.List;

public interface DukaBookService {
    public List<Book> getAll();

    public Book getById(String id) throws Exception;

    public Book save(Book book);

    public Book update(String id, Book book) throws Exception;

    public String delete(String id) throws Exception;
}
