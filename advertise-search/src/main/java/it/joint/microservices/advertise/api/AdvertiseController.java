package it.joint.microservices.advertise.api;

import it.joint.microservices.advertise.domain.model.Advertise;
import it.joint.microservices.advertise.domain.repository.AdvertiseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api")
public class AdvertiseController {

	private final Logger log = LoggerFactory.getLogger(AdvertiseController.class);

	private AdvertiseRepository advertiseRepository;
	
	@Autowired
	public AdvertiseController(AdvertiseRepository advertiseRepository) {
		this.advertiseRepository = advertiseRepository;
	}

    @GetMapping("/advertises/{id}")
    public Advertise getAdvertise(@PathVariable String id) {
        log.info("REST request to getAdvertise : {}", id);
        Advertise advertise = advertiseRepository.findOne(id);
        return advertise;
    }	
}
