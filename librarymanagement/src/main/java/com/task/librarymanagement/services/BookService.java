package com.task.librarymanagement.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task.librarymanagement.entity.Book;
import com.task.librarymanagement.repository.BookRepository;

@Service
public class BookService {
    private final BookRepository _bookRepository;
    Logger logger = LoggerFactory.getLogger(BookService.class);

    @Autowired
    public BookService(BookRepository bookRepository) {
        this._bookRepository = bookRepository;
    }

    public List<Book> findAllBooks(String title, String author) {
        try {
            if (title != null || author != null)
                return _bookRepository.search(title, author);

            return _bookRepository.findAll();
        } catch (Exception ex) {
            logger.error("Error in BookService --> findAllBooks " + ex);
            throw ex;
        }
    }

    public Book findById(Long id) {
        try {
            return _bookRepository.findById(id).get();
        } catch (Exception ex) {
            logger.error("Error in BookService --> findById " + ex);
            throw ex;
        }
    }

    public Book addBook(Book book) {
        try {
            return _bookRepository.save(book);
        } catch (Exception ex) {
            logger.error("Error in BookService --> addBook " + ex);
            throw ex;
        }
    }

    public Book updateBook(Long id, Book book) {
        try {
            Book bookDetails = _bookRepository.findById(id).get();
            if (bookDetails != null) {
                bookDetails.setIsbn(book.getIsbn());
                bookDetails.setName(book.getName());
                bookDetails.setAuthor(book.getAuthor());
                bookDetails.setSerialName(book.getSerialName());
                bookDetails.setDescription(book.getDescription());
                return _bookRepository.save(bookDetails);
            } else {
                return null;
            }
        } catch (Exception ex) {
            logger.error("Error in BookService --> updateBook " + ex);
            throw ex;
        }
    }

    public Book deleteBook(Long id) {
        try {
            Book bookDetails = _bookRepository.findById(id).get();
            if (bookDetails != null) {
                _bookRepository.deleteById(id);
                return bookDetails;
            } else {
                return null;
            }
        } catch (Exception ex) {
            logger.error("Error in BookService --> deleteBook " + ex);
            throw ex;
        }
    }
}
