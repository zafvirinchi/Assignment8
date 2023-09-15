package com.task.librarymanagement.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task.librarymanagement.entity.Library;
import com.task.librarymanagement.repository.LibraryRepository;

@Service
public class LibraryService {
    private final LibraryRepository _libraryRepository;
    Logger logger = LoggerFactory.getLogger(LibraryService.class);

    @Autowired
    public LibraryService(LibraryRepository libraryRepository) {
        this._libraryRepository = libraryRepository;
    }

    public List<Library> getAllLibrary(String library)
    {
        try {
            if (library != null)
                return _libraryRepository.search(library);

            return _libraryRepository.findAll();
        } catch (Exception ex) {
            logger.error("Error in LibraryService --> getAllLibrary " + ex);
            throw ex;
        }
    }

    public Library findById(Long id) {
        try {
            return _libraryRepository.findById(id).get();
        } catch (Exception ex) {
            logger.error("Error in LibraryService --> findById " + ex);
            throw ex;
        }
    }

    public Library addLibrary(Library library) {
        try {
            return _libraryRepository.save(library);
        } catch (Exception ex) {
            logger.error("Error in LibraryService --> addLibrary " + ex);
            throw ex;
        }
    }

    public Library updateLibrary(Long id, Library library) {
        try {
            Library libraryDetails = _libraryRepository.findById(id).get();
            if (libraryDetails != null) {
                libraryDetails.setName(library.getName());                
                libraryDetails.setBooks(library.getBooks());
                return _libraryRepository.save(libraryDetails);
            } else {
                return null;
            }
        } catch (Exception ex) {
            logger.error("Error in LibraryService --> updateLibrary " + ex);
            throw ex;
        }
    }

    public Library deleteLibrary(Long id) {
        try {
            Library libraryDetails = _libraryRepository.findById(id).get();
            if (libraryDetails != null) {
                _libraryRepository.deleteById(id);
                return libraryDetails;
            } else {
                return null;
            }
        } catch (Exception ex) {
            logger.error("Error in LibraryService --> deleteLibrary " + ex);
            throw ex;
        }
    }
}
