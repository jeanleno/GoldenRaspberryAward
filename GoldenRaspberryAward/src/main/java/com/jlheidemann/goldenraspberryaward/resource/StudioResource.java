package com.jlheidemann.goldenraspberryaward.resource;

import com.jlheidemann.goldenraspberryaward.entity.Studio;
import com.jlheidemann.goldenraspberryaward.service.StudioService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author jean.heidemann
 */
@RestController
public class StudioResource {
    @Autowired
    private StudioService studioService;
    
    @GetMapping(path = "/studios")
    public List<Studio> findAll() {
        return this.studioService.findAll();
    }
}
