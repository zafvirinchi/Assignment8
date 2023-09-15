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

import com.task.librarymanagement.entity.Borrower;
import com.task.librarymanagement.services.BorrowerService;

@RestController
@RequestMapping("/api/borrowers")
public class BorrowerController {
    private BorrowerService _borrowerService;

    @Autowired
    public BorrowerController(BorrowerService borrowerService) {
        this._borrowerService = borrowerService;
    }

    @GetMapping()
    public ResponseEntity<List<Borrower>> GetAllBorrower(@RequestParam(required = false) String borrower) {
        return ResponseEntity.status(HttpStatus.OK).body(_borrowerService.getAllBorrower(borrower));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Borrower> GetByid(@PathVariable Long id)
    {
        Borrower retunBorrower = _borrowerService.findById(id);
        if(retunBorrower != null)
            return ResponseEntity.ok(retunBorrower);
        else
            return ResponseEntity.noContent().build();
    }

    @PostMapping()
    public ResponseEntity<Borrower> SaveBorrower(Borrower borrower) {
        Borrower retunBorrower = _borrowerService.addBorrower(borrower);
        return ResponseEntity.status(HttpStatus.CREATED).body(retunBorrower);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Borrower> UpdateBorrower(@PathVariable Long id, Borrower borrower) {
        Borrower retunBorrower = _borrowerService.updateBorrower(id, borrower);
        if (retunBorrower != null)
            return ResponseEntity.ok(retunBorrower);
        else
            return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> DeleteBorrower(@PathVariable Long id)
    {
        Borrower retunBorrower = _borrowerService.deleteBorrower(id);
        if (retunBorrower != null)
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.notFound().build();
    }
}
