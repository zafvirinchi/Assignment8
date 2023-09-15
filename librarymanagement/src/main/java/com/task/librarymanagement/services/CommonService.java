package com.task.librarymanagement.services;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.task.librarymanagement.entity.Author;
import com.task.librarymanagement.entity.Book;
import com.task.librarymanagement.entity.Borrower;
import com.task.librarymanagement.entity.Genre;
import com.task.librarymanagement.entity.Library;
import com.task.librarymanagement.repository.BorrowerRepository;

import com.task.librarymanagement.repository.LibraryRepository;

@Service
public class CommonService {
    
    private final  LibraryRepository _LibraryRepository;
    private final  BorrowerRepository _borrowerRepository;
    Logger logger = LoggerFactory.getLogger(CommonService.class);

    @Autowired
    public CommonService(LibraryRepository libraryRepository, BorrowerRepository borrowerRepository) {
        this._LibraryRepository = libraryRepository;
        this._borrowerRepository = borrowerRepository;
    }

    public List<Book> getAllBooksByGenre(Long id)
    {
        try {
            HashMap<String, Long> params = new HashMap<>();
            params.put("id", id);

            ResponseEntity<Genre> response = new RestTemplate().getForEntity("http://localhost:8200/api/genre/{id}", Genre.class, params);
            return (List<Book>) response.getBody().getBooks();
        } catch (Exception ex) {
            logger.error("Error in CommonService --> getAllBooksByGenre " + ex);
            throw ex;
        }
    }

    public List<Book> getAllBooksByAuthor(Long id)
    {
        try {
            HashMap<String, Long> params = new HashMap<>();
            params.put("id", id);

            ResponseEntity<Author> response = new RestTemplate().getForEntity("http://localhost:8300/api/author/{id}", Author.class, params);
            return (List<Book>) response.getBody().getBooks();

        } catch (Exception ex) {
            logger.error("Error in CommonService --> getAllBooksByAuthor " + ex);
            throw ex;
        }
    }

    public List<Book> getAllBooksBylibrary_branch(Long id)
    {
        try {
            Library library = _LibraryRepository.findById(id).get();
            return (List<Book>) library.getBooks();
        } catch (Exception ex) {
            logger.error("Error in CommonService --> getAllBooksBylibrary_branch " + ex);
            throw ex;
        }
    }

    public List<Book> getAllBooksByBorrower(Long id)
    {
        try {
            Borrower borrower = _borrowerRepository.findById(id).get();
            return (List<Book>) borrower.getBooks();
        } catch (Exception ex) {
            logger.error("Error in CommonService --> getAllBooksByBorrower " + ex);
            throw ex;
        }
    }
}
