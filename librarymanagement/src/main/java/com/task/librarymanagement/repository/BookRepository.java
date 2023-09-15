package com.task.librarymanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.task.librarymanagement.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {    
    @Query("SELECT b FROM Book b WHERE b.name LIKE %?1%" + " OR b.name LIKE %?1%" + " OR b.author LIKE %?1%")
    List<Book> search(String title, String author);
}