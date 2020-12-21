package com.jlheidemann.goldenraspberryaward.repository;

import com.jlheidemann.goldenraspberryaward.entity.Studio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jean.heidemann
 */
@Repository
public interface StudioRepository extends JpaRepository<Studio, Long>{
    Studio findByName(@Param("name") String name);
}
