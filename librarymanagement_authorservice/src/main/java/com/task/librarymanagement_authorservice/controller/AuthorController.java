package com.task.librarymanagement_authorservice.controller;

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

import com.task.librarymanagement_authorservice.entity.Author;
import com.task.librarymanagement_authorservice.services.AuthorService;

@RestController
@RequestMapping("/api/author")
public class AuthorController {
    private AuthorService _authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this._authorService = authorService;
    }

    @GetMapping()
    public ResponseEntity<List<Author>> GetAllAuthor(@RequestParam(required = false) String author) {
        return ResponseEntity.status(HttpStatus.OK).body(_authorService.getAllAuthors(author));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> GetByid(@PathVariable Long id)
    {
        Author retunAuthor = _authorService.findById(id);
        if(retunAuthor != null)
            return ResponseEntity.ok(retunAuthor);
        else
            return ResponseEntity.noContent().build();
    }

    @PostMapping()
    public ResponseEntity<Author> SaveAuthor(Author author) {
        Author retunAuthor = _authorService.addAuthor(author);
        return ResponseEntity.status(HttpStatus.CREATED).body(retunAuthor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Author> UpdateAuthor(@PathVariable Long id, Author author) {
        Author retunAuthor = _authorService.updateAuthor(id, author);
        if (retunAuthor != null)
            return ResponseEntity.ok(retunAuthor);
        else
            return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> DeleteAuthor(@PathVariable Long id)
    {
        Author retunAuthor = _authorService.deleteAuthor(id);
        if (retunAuthor != null)
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.notFound().build();
    }
}
