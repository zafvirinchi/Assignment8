package com.task.librarymanagement_genreservice.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task.librarymanagement_genreservice.entity.Genre;
import com.task.librarymanagement_genreservice.repository.GenreRepository;

@Service
public class GenreService {
    private final GenreRepository _genreRepository;
    Logger logger = LoggerFactory.getLogger(GenreService.class);

    @Autowired
    public GenreService(GenreRepository genreRepository) {
        this._genreRepository = genreRepository;
    }

    public List<Genre> getAllGenres(String genres)
    {
        try {
            if (genres != null)
                return _genreRepository.search(genres);

            return _genreRepository.findAll();
        } catch (Exception ex) {
            logger.error("Error in GenreService --> getAllGenres " + ex);
            throw ex;
        }
    }

    public Genre findById(Long id) {
        try {
            return _genreRepository.findById(id).get();
        } catch (Exception ex) {
            logger.error("Error in GenreService --> findById " + ex);
            throw ex;
        }
    }

    public Genre addGenre(Genre genres) {
        try {
            return _genreRepository.save(genres);
        } catch (Exception ex) {
            logger.error("Error in GenreService --> addGenre " + ex);
            throw ex;
        }
    }

    public Genre updateGenre(Long id, Genre genres) {
        try {
            Genre genresDetails = _genreRepository.findById(id).get();
            if (genresDetails != null) {
                genresDetails.setName(genres.getName());                
                genresDetails.setBooks(genres.getBooks());
                return _genreRepository.save(genresDetails);
            } else {
                return null;
            }
        } catch (Exception ex) {
            logger.error("Error in GenreService --> updateGenre " + ex);
            throw ex;
        }
    }

    public Genre deleteGenre(Long id) {
        try {
            Genre genreDetails = _genreRepository.findById(id).get();
            if (genreDetails != null) {
                _genreRepository.deleteById(id);
                return genreDetails;
            } else {
                return null;
            }
        } catch (Exception ex) {
            logger.error("Error in GenreService --> deleteGenre " + ex);
            throw ex;
        }
    }
}
