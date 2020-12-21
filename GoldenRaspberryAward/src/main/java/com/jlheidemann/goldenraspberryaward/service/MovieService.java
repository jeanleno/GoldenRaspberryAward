package com.jlheidemann.goldenraspberryaward.service;

import com.jlheidemann.goldenraspberryaward.entity.Movie;
import com.jlheidemann.goldenraspberryaward.repository.MovieRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author jean.heidemann
 */
@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;
    
    public void saveMovie(Movie movie) {
        this.movieRepository.saveAndFlush(movie);
    }
    
    public List<Movie> findAll() {
        return this.movieRepository.findAll();
    }
}
