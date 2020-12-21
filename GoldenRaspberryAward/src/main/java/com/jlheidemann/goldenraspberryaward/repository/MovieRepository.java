package com.jlheidemann.goldenraspberryaward.repository;

import com.jlheidemann.goldenraspberryaward.entity.Movie;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jean.heidemann
 */
@Repository
public interface MovieRepository extends JpaRepository<Movie, Long>{
    List<Movie> findByWinnerTrue();
}
