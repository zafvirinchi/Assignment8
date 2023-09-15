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

import com.task.librarymanagement.entity.Library;
import com.task.librarymanagement.services.LibraryService;

@RestController
@RequestMapping("/api/library-branches")
public class LibraryController {
    private LibraryService _libraryService;

    @Autowired
    public LibraryController(LibraryService libraryService) {
        this._libraryService = libraryService;
    }

    @GetMapping()
    public ResponseEntity<List<Library>> GetAllLibrary(@RequestParam(required = false) String library) {
        return ResponseEntity.status(HttpStatus.OK).body(_libraryService.getAllLibrary(library));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Library> GetByid(@PathVariable Long id)
    {
        Library retunLibrary = _libraryService.findById(id);
        if(retunLibrary != null)
            return ResponseEntity.ok(retunLibrary);
        else
            return ResponseEntity.noContent().build();
    }

    @PostMapping()
    public ResponseEntity<Library> SaveLibrary(Library library) {
        Library retunLibrary = _libraryService.addLibrary(library);
        return ResponseEntity.status(HttpStatus.CREATED).body(retunLibrary);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Library> UpdateLibrary(@PathVariable Long id, Library library) {
        Library retunLibrary = _libraryService.updateLibrary(id, library);
        if (retunLibrary != null)
            return ResponseEntity.ok(retunLibrary);
        else
            return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> DeleteLibrary(@PathVariable Long id)
    {
        Library retunLibrary = _libraryService.deleteLibrary(id);
        if (retunLibrary != null)
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.notFound().build();
    }
}
