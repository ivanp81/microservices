package it.joint.microservices.advertise.api;

import it.joint.microservices.advertise.domain.model.Advertise;
import it.joint.microservices.advertise.domain.service.AdvertiseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/searchengine")
public class AdvertiseController {

	private final Logger log = LoggerFactory.getLogger(AdvertiseController.class);

	private AdvertiseService advertiseService;

	@Autowired
	public AdvertiseController(AdvertiseService advertiseService) {
		this.advertiseService = advertiseService;
	}

	@GetMapping("/advertises/_search")
	public List<Advertise> searchAdvertises(@RequestParam("q") String q) {
		
		log.info("REST request to getAdvertise : {}", q);
		return advertiseService.searchAdvertises(q);
	}
}
