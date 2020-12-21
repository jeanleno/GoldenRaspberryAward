package com.jlheidemann.goldenraspberryaward.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jlheidemann.goldenraspberryaward.service.AwardService;
import com.jlheidemann.goldenraspberryaward.service.dto.AwardsDto;

/**
*
* @author jean.heidemann
*/
@RestController
public class AwardResource {

	@Autowired
	private AwardService awardService;
	
	@GetMapping(path = "/award")
	private AwardsDto findAwards() {
		return awardService.findAwards();
	}
}
