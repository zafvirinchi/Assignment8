package com.task.librarymanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.task.librarymanagement.entity.Library;

@Repository
public interface LibraryRepository extends JpaRepository<Library, Long> {
    @Query("SELECT b FROM Library b WHERE b.name LIKE %?1%")
    List<Library> search(String library);
}
