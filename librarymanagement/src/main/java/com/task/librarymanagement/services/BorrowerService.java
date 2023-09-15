package com.task.librarymanagement.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task.librarymanagement.entity.Borrower;
import com.task.librarymanagement.repository.BorrowerRepository;

@Service
public class BorrowerService {
    private final BorrowerRepository _borrowerRepository;
    Logger logger = LoggerFactory.getLogger(BorrowerService.class);

    @Autowired
    public BorrowerService(BorrowerRepository borrowerRepository) {
        this._borrowerRepository = borrowerRepository;
    }

    public List<Borrower> getAllBorrower(String borrower)
    {
        try {
            if (borrower != null)
                return _borrowerRepository.search(borrower);

            return _borrowerRepository.findAll();
        } catch (Exception ex) {
            logger.error("Error in BorrowerService --> getAllBorrower " + ex);
            throw ex;
        }
    }

    public Borrower findById(Long id) {
        try {
            return _borrowerRepository.findById(id).get();
        } catch (Exception ex) {
            logger.error("Error in BorrowerService --> findById " + ex);
            throw ex;
        }
    }

    public Borrower addBorrower(Borrower borrower) {
        try {
            return _borrowerRepository.save(borrower);
        } catch (Exception ex) {
            logger.error("Error in BorrowerService --> addBorrower " + ex);
            throw ex;
        }
    }

    public Borrower updateBorrower(Long id, Borrower borrower) {
        try {
            Borrower borrowerDetails = _borrowerRepository.findById(id).get();
            if (borrowerDetails != null) {
                borrowerDetails.setName(borrower.getName()); 
                borrowerDetails.setAddress(borrower.getAddress());
                borrowerDetails.setPhoneNum(borrower.getPhoneNum());               
                borrowerDetails.setBooks(borrower.getBooks());
                return _borrowerRepository.save(borrowerDetails);
            } else {
                return null;
            }
        } catch (Exception ex) {
            logger.error("Error in BorrowerService --> updateBorrower " + ex);
            throw ex;
        }
    }

    public Borrower deleteBorrower(Long id) {
        try {
            Borrower borrowerDetails = _borrowerRepository.findById(id).get();
            if (borrowerDetails != null) {
                _borrowerRepository.deleteById(id);
                return borrowerDetails;
            } else {
                return null;
            }
        } catch (Exception ex) {
            logger.error("Error in BorrowerService --> deleteBorrower " + ex);
            throw ex;
        }
    }
}
