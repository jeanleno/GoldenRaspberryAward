package com.jlheidemann.goldenraspberryaward.resource;

import com.jlheidemann.goldenraspberryaward.entity.Producer;
import com.jlheidemann.goldenraspberryaward.service.ProducerService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author jean.heidemann
 */
@RestController
public class ProducerResource {
    @Autowired
    private ProducerService producerService;
    
    @GetMapping(path = "/producers")
    public List<Producer> findAll() {
        return this.producerService.findAll();
    }
    
    @GetMapping(path = "/producers/{name}")
    public List<Producer> findByName(@PathVariable("name") String name) {
        return this.producerService.findByNameContaining(name);
    }
}
