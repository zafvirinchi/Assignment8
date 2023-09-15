package com.task.librarymanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.task.librarymanagement.entity.Book;
import com.task.librarymanagement.services.BookService;
import com.task.librarymanagement.services.CommonService;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private BookService _bookService;
    private CommonService _commonService;

    @Autowired
    public BookController(BookService bookService, CommonService commonService) {
        this._bookService = bookService;
        this._commonService = commonService;
    }

    @GetMapping()
    public ResponseEntity<List<Book>> GetAllBook(@RequestParam(required = false) String title,
            @RequestParam(required = false) String author) {
        return ResponseEntity.status(HttpStatus.OK).body(_bookService.findAllBooks(title, author));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> GetByid(@PathVariable Long id)
    {
        Book retunBook = _bookService.findById(id);
        if(retunBook != null)
            return ResponseEntity.ok(retunBook);
        else
            return ResponseEntity.noContent().build();
    }

    @PostMapping()
    public ResponseEntity<Book> SaveBook(Book book) {
        Book resultBook = _bookService.addBook(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(resultBook);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> UpdateBook(@PathVariable Long id, Book book) {
        Book resultBook = _bookService.updateBook(id, book);
        if (resultBook != null)
            return ResponseEntity.ok(resultBook);
        else
            return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> DeleteBook(@PathVariable Long id)
    {
        Book resultBook = _bookService.deleteBook(id);
        if (resultBook != null)
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.notFound().build();
    }

    @GetMapping("/genre/{id}")
    public ResponseEntity<List<Book>> GetBooksByGenre(@PathVariable Long id)
    {
        List<Book> retunBooks = _commonService.getAllBooksByGenre(id);
        if(retunBooks != null)
            return ResponseEntity.ok(retunBooks);
        else
            return ResponseEntity.noContent().build();
    }

    @GetMapping("/author/{id}")
    public ResponseEntity<List<Book>> GetBooksByAuthor(@PathVariable Long id)
    {
        List<Book> retunBooks = _commonService.getAllBooksByAuthor(id);
        if(retunBooks != null)
            return ResponseEntity.ok(retunBooks);
        else
            return ResponseEntity.noContent().build();
    }

    @GetMapping("/library-branch/{id}")
    public ResponseEntity<List<Book>> GetBooksByLibrary_branch(@PathVariable Long id)
    {
        List<Book> retunBooks = _commonService.getAllBooksBylibrary_branch(id);
        if(retunBooks != null)
            return ResponseEntity.ok(retunBooks);
        else
            return ResponseEntity.noContent().build();
    }

    @GetMapping("/borrower/{id}")
    public ResponseEntity<List<Book>> GetBooksByBorrower(@PathVariable Long id)
    {
        List<Book> retunBooks = _commonService.getAllBooksByBorrower(id);
        if(retunBooks != null)
            return ResponseEntity.ok(retunBooks);
        else
            return ResponseEntity.noContent().build();
    }
}
