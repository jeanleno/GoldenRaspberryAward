package com.jlheidemann.goldenraspberryaward.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jlheidemann.goldenraspberryaward.entity.Producer;
import com.jlheidemann.goldenraspberryaward.repository.ProducerRepository;

/**
 *
 * @author jean.heidemann
 */
@Service
public class ProducerService {
    @Autowired
    private ProducerRepository producerRepository;
    	
    
    public void saveProducer(Producer producer) {
        this.producerRepository.saveAndFlush(producer);
    }
    
    public List<Producer> findByNameContaining(String name) {
        return this.producerRepository.findByNameContaining(name);
    }
    
    public Producer findByName(String name) {
        return this.producerRepository.findByName(name);
    }
    
    public List<Producer> findAll() {
        return this.producerRepository.findAll();
    }
}
