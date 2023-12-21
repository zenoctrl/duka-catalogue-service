package com.example.dukacatalogue.controller;

import com.example.dukacatalogue.entity.book.Book;
import com.example.dukacatalogue.entity.Response;
import com.example.dukacatalogue.service.DukaBookService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/duka/api/v1/catalogue")
public class DukaBookController {

    @Autowired
    private DukaBookService dukaBookService;

    @GetMapping("/books")
    public ResponseEntity<Response> getAllBooks() {
        log.info("Fetching Books ...");
        Response response = new Response();
        List<Book> books = dukaBookService.getAll();
        response.setStatus(200);
        response.setMessage("Books fetched successfully.");
        response.setData(books);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<Response> getBookById(@PathVariable String id) throws Exception {
        log.info("Fetching book with id {} ...", id);
        Response response = new Response();
        Book book = dukaBookService.getById(id);
        response.setStatus(200);
        response.setMessage("Book fetched successfully.");
        response.setData(book);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/books")
    public ResponseEntity<Response> save(@Valid @RequestBody Book book) {
        log.info("Saving {} ...", book);
        Response response = new Response();
        Book newBook = dukaBookService.save(book);
        response.setStatus(201);
        response.setMessage("Book saved successfully.");
        response.setData(newBook);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/books/{id}")
    public ResponseEntity<Response> update(@PathVariable String id, @Valid @RequestBody Book book) throws Exception {
        log.info("Updating {} ...", book);
        Response response = new Response();
        Book updatedBook = dukaBookService.update(id, book);
        response.setStatus(201);
        response.setMessage("Book updated successfully.");
        response.setData(updatedBook);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<Response> delete(@PathVariable String id) throws Exception {
        log.info("Deleting book with id {} ...", id);
        Response response = new Response();
        if (dukaBookService.delete(id).equalsIgnoreCase("success")) {
            response.setStatus(200);
            response.setMessage("Book deleted successfully.");
        }
        response.setData(null);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
