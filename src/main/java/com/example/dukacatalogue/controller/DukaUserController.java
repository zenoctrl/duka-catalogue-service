package com.example.dukacatalogue.controller;

import com.example.dukacatalogue.entity.Response;
import com.example.dukacatalogue.entity.book.Book;
import com.example.dukacatalogue.entity.user.User;
import com.example.dukacatalogue.service.DukaUserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/duka/api/v1/catalogue/users")
public class DukaUserController {

    @Autowired
    private DukaUserService dukaUserService;

    @GetMapping("/customers")
    public ResponseEntity<Response> getAllCustomers() {
        log.info("Fetching customers ...");
        Response response = new Response();
        List<User> users = dukaUserService.getAllCustomers();
        response.setStatus(200);
        response.setMessage("Customers fetched successfully");
        response.setData(users);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<Response> getCustomerById(@PathVariable String id) throws Exception {
        log.info("Fetching account with id {}", id);
        Response response = new Response();
        User user = dukaUserService.getUserById(id);
        response.setStatus(200);
        response.setMessage("Account fetched successfully.");
        response.setData(user);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/customers")
    public ResponseEntity<Response> saveCustomer(@Valid @RequestBody User user) {
        log.info("Saving {}", user);
        Response response = new Response();
        User newUser = dukaUserService.save(user);
        response.setStatus(201);
        response.setMessage("Customer created successfully.");
        response.setData(newUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/customers/{id}")
    public ResponseEntity<Response> updateCustomer(@PathVariable String id, @RequestBody User user) throws Exception {
        log.info("Updating {}", user);
        Response response = new Response();
        User updatedUser = dukaUserService.update(id, user);
        response.setStatus(201);
        response.setMessage("Customer updated successfully.");
        response.setData(updatedUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/customers/{id}")
    public ResponseEntity<Response> deleteCustomer(@PathVariable String id) throws Exception{
        log.info("Deleting account with id {}", id);
        Response response = new Response();
        if (dukaUserService.delete(id).equalsIgnoreCase("success")) {
            response.setStatus(200);
            response.setMessage("Account deleted successfully.");
        }
        response.setData(null);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/staff")
    public ResponseEntity<Response> getAllStaff() {
        log.info("Fetching staff ...");
        Response response = new Response();
        List<User> users = dukaUserService.getAllStaff();
        response.setStatus(200);
        response.setMessage("Customers staff successfully");
        response.setData(users);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/staff/{id}")
    public ResponseEntity<Response> getStaffById(@PathVariable String id) throws Exception {
        log.info("Fetching account with id {}", id);
        Response response = new Response();
        User user = dukaUserService.getUserById(id);
        response.setStatus(200);
        response.setMessage("Account fetched successfully.");
        response.setData(user);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/staff")
    public ResponseEntity<Response> saveStaff(@Valid @RequestBody User user) {
        log.info("Saving {}", user);
        Response response = new Response();
        User newUser = dukaUserService.save(user);
        response.setStatus(201);
        response.setMessage("Staff created successfully.");
        response.setData(newUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/staff/{id}")
    public ResponseEntity<Response> updateStaff(@PathVariable String id, @RequestBody User user) throws Exception {
        log.info("Updating {}", user);
        Response response = new Response();
        User updatedUser = dukaUserService.update(id, user);
        response.setStatus(201);
        response.setMessage("Staff updated successfully.");
        response.setData(updatedUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/staff/{id}")
    public ResponseEntity<Response> deleteStaff(@PathVariable String id) throws Exception{
        log.info("Deleting account with id {}", id);
        Response response = new Response();
        if (dukaUserService.delete(id).equalsIgnoreCase("success")) {
            response.setStatus(200);
            response.setMessage("Account deleted successfully.");
        }
        response.setData(null);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
