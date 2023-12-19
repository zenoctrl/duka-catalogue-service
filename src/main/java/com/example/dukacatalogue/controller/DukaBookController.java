package com.example.dukacatalogue.controller;

import com.example.dukacatalogue.entity.Book;
import com.example.dukacatalogue.entity.Response;
import com.example.dukacatalogue.exception.BookNotFoundException;
import com.example.dukacatalogue.service.DukaBookService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController("/duka/api/v1/catalogue")
public class DukaBookController {

    @Autowired
    private DukaBookService dukaBookService;

    @GetMapping("/books")
    public Response getAllBooks() {
        log.info("Fetching Books ...");
        Response response = new Response();
        List<Book> books = dukaBookService.getAll();
        response.setStatus(200);
        response.setMessage("Books fetched successfully.");
        response.setData(books);
        return response;
    }

    @GetMapping("/books/{id}")
    public Response getBookById(@PathVariable String id) throws Exception {
        log.info("Fetching book with id {} ...", id);
        Response response = new Response();
        Book book = dukaBookService.getById(id);
        response.setStatus(200);
        response.setMessage("Book fetched successfully.");
        response.setData(book);
        return response;
    }

    @PostMapping("/books")
    public Response save(@Valid @RequestBody Book book) {
        log.info("Saving {} ...", book);
        Response response = new Response();
        Book newBook = dukaBookService.save(book);
        response.setStatus(200);
        response.setMessage("Book saved successfully");
        response.setData(newBook);
        return response;
    }

    @PutMapping("/books/{id}")
    public Response update(@PathVariable String id, @Valid @RequestBody Book book) throws Exception {
        log.info("Updating {} ...", book);
        Response response = new Response();
        Book updatedBook = dukaBookService.update(id, book);
        response.setStatus(200);
        response.setMessage("Book saved successfully");
        response.setData(updatedBook);
        return response;
    }

    @DeleteMapping("/books/{id}")
    public Response delete(@PathVariable String id) throws Exception {
        log.info("Deleting book with id {} ...", id);
        Response response = new Response();
        if (dukaBookService.delete(id).equalsIgnoreCase("success")) {
            response.setStatus(200);
            response.setMessage("Book deleted successfully");
        }
        response.setData(null);
        return response;
    }
}
