package com.example.dukacatalogue.repository;

import com.example.dukacatalogue.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DukaBookRepository extends JpaRepository<Book, String> {
}
