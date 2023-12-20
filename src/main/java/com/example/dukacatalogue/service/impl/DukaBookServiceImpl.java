package com.example.dukacatalogue.service.impl;

import com.example.dukacatalogue.entity.book.Book;
import com.example.dukacatalogue.exception.BookNotFoundException;
import com.example.dukacatalogue.repository.DukaBookRepository;
import com.example.dukacatalogue.service.DukaBookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class DukaBookServiceImpl implements DukaBookService {

    @Autowired
    private DukaBookRepository dukaBookRepository;

    @Override
    public List<Book> getAll() {
        List<Book> books = dukaBookRepository.findAll();
        String noun = "Books";
        if (books.size() == 1) noun = "Book";
        log.info("Found {} {}", books.size(), noun);
        return books;
    }

    @Override
    public Book getById(String id) throws BookNotFoundException {
        Optional<Book> book = dukaBookRepository.findById(id);
        if (!book.isPresent()) {
            log.info("Book not found.");
            throw new BookNotFoundException("Book not found.");
        } else {
            log.info("Found {}", book.get());
        }
        return book.get();
    }

    @Override
    public Book save(Book book) {
        Book savedBook = dukaBookRepository.save(book);
        log.info("Saved {}", savedBook);
        return savedBook;
    }

    @Override
    public Book update(String id, Book book) throws BookNotFoundException {
        Optional<Book> existingBook = dukaBookRepository.findById(id);
        if (!existingBook.isPresent()) {
            log.info("Update failed. Book not found.");
            throw new BookNotFoundException("Update failed. Book not found.");
        }
        Book updatedBook = dukaBookRepository.save(book);
        log.info("Updated {}", updatedBook);
        return updatedBook;
    }

    @Override
    public String delete(String id) throws BookNotFoundException {
        Optional<Book> book = dukaBookRepository.findById(id);
        if (!book.isPresent()) {
            log.info("Delete failed. Book not found.");
            throw new BookNotFoundException("Delete failed. Book not found.");
        }
        log.info("Deleted {}", book.get());
        dukaBookRepository.deleteById(id);
        return "Success";
    }

}
