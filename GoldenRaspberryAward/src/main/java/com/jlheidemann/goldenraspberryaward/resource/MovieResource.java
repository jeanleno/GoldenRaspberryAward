package com.jlheidemann.goldenraspberryaward.resource;

import com.jlheidemann.goldenraspberryaward.entity.Movie;
import com.jlheidemann.goldenraspberryaward.service.MovieService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author jean.heidemann
 */
@RestController
public class MovieResource {
    @Autowired
    private MovieService movieService;
    
    @GetMapping(path = "/movies")
    public List<Movie> findAll() {
        return this.movieService.findAll();
    }
}
