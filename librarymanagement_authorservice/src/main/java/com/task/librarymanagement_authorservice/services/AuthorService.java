package com.task.librarymanagement_authorservice.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task.librarymanagement_authorservice.entity.Author;
import com.task.librarymanagement_authorservice.repository.AuthorRepository;

@Service
public class AuthorService {
    private final AuthorRepository _authorRepository;
    Logger logger = LoggerFactory.getLogger(AuthorService.class);

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this._authorRepository = authorRepository;
    }

    public List<Author> getAllAuthors(String author)
    {
        try {
            if (author != null)
                return _authorRepository.search(author);

            return _authorRepository.findAll();
        } catch (Exception ex) {
            logger.error("Error in AuthorService --> getAllAuthors " + ex);
            throw ex;
        }
    }

    public Author findById(Long id) {
        try {
            return _authorRepository.findById(id).get();
        } catch (Exception ex) {
            logger.error("Error in AuthorService --> findById " + ex);
            throw ex;
        }
    }

    public Author addAuthor(Author author) {
        try {
            return _authorRepository.save(author);
        } catch (Exception ex) {
            logger.error("Error in AuthorService --> addAuthor " + ex);
            throw ex;
        }
    }

    public Author updateAuthor(Long id, Author author) {
        try {
            Author authorDetails = _authorRepository.findById(id).get();
            if (authorDetails != null) {
                authorDetails.setName(author.getName());
                authorDetails.setDescription(author.getDescription());
                authorDetails.setBooks(author.getBooks());
                return _authorRepository.save(authorDetails);
            } else {
                return null;
            }
        } catch (Exception ex) {
            logger.error("Error in AuthorService --> updateAuthor " + ex);
            throw ex;
        }
    }

    public Author deleteAuthor(Long id) {
        try {
            Author authorDetails = _authorRepository.findById(id).get();
            if (authorDetails != null) {
                _authorRepository.deleteById(id);
                return authorDetails;
            } else {
                return null;
            }
        } catch (Exception ex) {
            logger.error("Error in AuthorService --> deleteAuthor " + ex);
            throw ex;
        }
    }
}
