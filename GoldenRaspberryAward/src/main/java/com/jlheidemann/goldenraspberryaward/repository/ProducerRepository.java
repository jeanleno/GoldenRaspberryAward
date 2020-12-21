package com.jlheidemann.goldenraspberryaward.repository;

import com.jlheidemann.goldenraspberryaward.entity.Producer;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jean.heidemann
 */
@Repository
public interface ProducerRepository extends JpaRepository<Producer, Long> {
    List<Producer> findByNameContaining(@Param("name") String name);
    Producer findByName(@Param("name") String name);
}
