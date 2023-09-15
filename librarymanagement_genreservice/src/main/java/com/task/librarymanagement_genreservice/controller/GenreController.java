package com.task.librarymanagement_genreservice.controller;

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

import com.task.librarymanagement_genreservice.entity.Genre;
import com.task.librarymanagement_genreservice.services.GenreService;

@RestController
@RequestMapping("/api/genre")
public class GenreController {
    private GenreService _genreService;

    @Autowired
    public GenreController(GenreService genreService) {
        this._genreService = genreService;
    }

    @GetMapping()
    public ResponseEntity<List<Genre>> GetAllAuthor(@RequestParam(required = false) String genre) {
        return ResponseEntity.status(HttpStatus.OK).body(_genreService.getAllGenres(genre));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Genre> GetByid(@PathVariable Long id)
    {
        Genre retunGenre = _genreService.findById(id);
        if(retunGenre != null)
            return ResponseEntity.ok(retunGenre);
        else
            return ResponseEntity.noContent().build();
    }

    @PostMapping()
    public ResponseEntity<Genre> SaveGenre(Genre genre) {
        Genre retunGenre = _genreService.addGenre(genre);
        return ResponseEntity.status(HttpStatus.CREATED).body(retunGenre);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Genre> UpdateGenre(@PathVariable Long id, Genre genre) {
        Genre retunGenre = _genreService.updateGenre(id, genre);
        if (retunGenre != null)
            return ResponseEntity.ok(retunGenre);
        else
            return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> DeleteGenre(@PathVariable Long id)
    {
        Genre retunGenre = _genreService.deleteGenre(id);
        if (retunGenre != null)
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.notFound().build();
    }
}
