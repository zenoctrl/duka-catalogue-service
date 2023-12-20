package com.example.dukacatalogue.controller;

import com.example.dukacatalogue.entity.Response;
import com.example.dukacatalogue.entity.book.Book;
import com.example.dukacatalogue.entity.user.User;
import com.example.dukacatalogue.service.DukaUserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/duka/api/v1/catalogue/users")
public class DukaUserController {

    @Autowired
    private DukaUserService dukaUserService;

    @GetMapping("/customers")
    public Response getAllCustomers() {
        log.info("Fetching customers ...");
        Response response = new Response();
        List<User> users = dukaUserService.getAllCustomers();
        response.setStatus(200);
        response.setMessage("Customers fetched successfully");
        response.setData(users);
        return response;
    }

    @GetMapping("/customers/{id}")
    public Response getCustomerById(@PathVariable String id) throws Exception {
        log.info("Fetching account with id {}", id);
        Response response = new Response();
        User user = dukaUserService.getUserById(id);
        response.setStatus(200);
        response.setMessage("Account fetched successfully.");
        response.setData(user);
        return response;
    }

    @PostMapping("/customers")
    public Response saveCustomer(@Valid @RequestBody User user) {
        log.info("Saving {}", user);
        Response response = new Response();
        User newUser = dukaUserService.save(user);
        response.setStatus(200);
        response.setMessage("Customer created successfully.");
        response.setData(newUser);
        return response;
    }

    @PutMapping("/customers/{id}")
    public Response updateCustomer(@PathVariable String id, @RequestBody User user) throws Exception {
        log.info("Updating {}", user);
        Response response = new Response();
        User updatedUser = dukaUserService.update(id, user);
        response.setStatus(200);
        response.setMessage("Customer updated successfully.");
        response.setData(updatedUser);
        return response;
    }

    @DeleteMapping("/customers/{id}")
    public Response deleteCustomer(@PathVariable String id) throws Exception{
        log.info("Deleting account with id {}", id);
        Response response = new Response();
        if (dukaUserService.delete(id).equalsIgnoreCase("success")) {
            response.setStatus(200);
            response.setMessage("Account deleted successfully.");
        }
        response.setData(null);
        return response;
    }


}
