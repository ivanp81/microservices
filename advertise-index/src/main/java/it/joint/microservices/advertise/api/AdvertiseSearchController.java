package it.joint.microservices.advertise.api;

import it.joint.microservices.advertise.domain.model.Advertise;
import it.joint.microservices.advertise.domain.repository.AdvertiseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api")
public class AdvertiseSearchController {

	private final Logger log = LoggerFactory.getLogger(AdvertiseSearchController.class);

	private AdvertiseRepository advertiseRepository;
	
	@Autowired
	public AdvertiseSearchController(AdvertiseRepository advertiseRepository) {
		this.advertiseRepository = advertiseRepository;
	}

    @GetMapping("/advertises/{id}")
    public Advertise getAdvertise(@PathVariable String id) {
        log.debug("REST request to get Advertise : {}", id);
        Advertise advertise = advertiseRepository.findOne(id);
        return advertise;
    }	
}
