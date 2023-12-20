package com.example.dukacatalogue.repository;

import com.example.dukacatalogue.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DukaUserRepository extends JpaRepository<User, String> {
}
