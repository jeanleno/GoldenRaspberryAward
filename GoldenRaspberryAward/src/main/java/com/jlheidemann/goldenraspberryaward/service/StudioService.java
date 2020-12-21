package com.jlheidemann.goldenraspberryaward.service;

import com.jlheidemann.goldenraspberryaward.entity.Studio;
import com.jlheidemann.goldenraspberryaward.repository.StudioRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author jean.heidemann
 */
@Service
public class StudioService {
    @Autowired
    private StudioRepository studioRepository;
    
    public void saveStudio(Studio studio) {
        this.studioRepository.saveAndFlush(studio);
    }
    
    public Studio findByName(String name) {
        return this.studioRepository.findByName(name);
    }
    
    public List<Studio> findAll() {
        return this.studioRepository.findAll();
    }
}
