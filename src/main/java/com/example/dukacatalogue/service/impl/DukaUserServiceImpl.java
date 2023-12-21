package com.example.dukacatalogue.service.impl;

import com.example.dukacatalogue.entity.user.Role;
import com.example.dukacatalogue.entity.user.Status;
import com.example.dukacatalogue.entity.user.User;
import com.example.dukacatalogue.exception.NotFoundException;
import com.example.dukacatalogue.repository.DukaUserRepository;
import com.example.dukacatalogue.service.DukaUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class DukaUserServiceImpl implements DukaUserService {

    @Autowired
    private DukaUserRepository dukaUserRepository;

    @Override
    public List<User> getAllCustomers() {
        List<User> users = dukaUserRepository.findAll().stream().filter(user -> user.getRole().equals(Role.CUSTOMER)).toList();
        String noun = "Customers";
        if (users.size() == 1) noun = "Customer";
        log.info("Found {} {}", users.size(), noun);
        return users;
    }

    @Override
    public User getUserById(String id) throws NotFoundException {
        Optional<User> user = dukaUserRepository.findById(id);
        if (!user.isPresent()) {
            log.info("Account not found.");
            throw new NotFoundException("Account not found.");
        } else {
            log.info("Found {}", user.get());
        }
        return user.get();
    }

    @Override
    public User save(User user) {
        user.setStatus(Status.ACTIVE);
        if (user.getRole() == null) user.setRole(Role.CUSTOMER);
        if (user.getCreatedBy() == null) user.setCreatedBy("SELF-ONBOARD");
        User savedUser = dukaUserRepository.save(user);
        log.info("Saved {}", savedUser);
        return savedUser;
    }

    @Override
    public User update(String id, User user) throws NotFoundException {
        Optional<User> existingUser = dukaUserRepository.findById(id);
        if (!existingUser.isPresent()) {
            log.info("Account not found.");
            throw new NotFoundException("Update failed. Account not found");
        }
        User updatedUser = dukaUserRepository.save(user);
        log.info("Updated {}", updatedUser);
        return updatedUser;
    }

    @Override
    public String delete(String id) throws NotFoundException {
        Optional<User> user = dukaUserRepository.findById(id);
        if (!user.isPresent()) {
            log.info("Account not found.");
            throw new NotFoundException("Delete failed. Account not found");
        }
        log.info("Deleted {}", user.get());
        dukaUserRepository.deleteById(id);
        return "Success";
    }

    @Override
    public List<User> getAllStaff() {
        List<User> users = dukaUserRepository.findAll().stream().filter(user -> !user.getRole().equals(Role.CUSTOMER)).toList();
        log.info("Found {} Staff", users.size());
        return users;
    }
}
