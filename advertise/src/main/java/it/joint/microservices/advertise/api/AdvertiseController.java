package it.joint.microservices.advertise.api;

import it.joint.microservices.advertise.domain.model.Advertise;
import it.joint.microservices.advertise.domain.repository.AdvertiseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api")
public class AdvertiseController {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	private AdvertiseRepository advertiseRepository;
	
	@Autowired
	public AdvertiseController(AdvertiseRepository advertiseRepository) {
		this.advertiseRepository = advertiseRepository;
	}

	@PostMapping("/advertises")
    public Advertise createAdvertise(@Valid @RequestBody Advertise advertise) {
    	log.info("REST request to createAdvertise : {}", advertise);	
        Advertise createdAdvertise = advertiseRepository.save(advertise);
        return createdAdvertise;
    }
	
	@PutMapping("/advertises")
    public Advertise updateAdvertise(@Valid @RequestBody Advertise advertise) {
    	log.info("REST request to updateAdvertise : {}", advertise);	
        Advertise createdAdvertise = advertiseRepository.save(advertise);
        return createdAdvertise;
    }
	
	@DeleteMapping("/advertises/{id}")
    public void deleteAdvertise(@PathVariable String id) {
        log.info("REST request to deleteAdvertise : {}", id);
        advertiseRepository.delete(id);
    }
	
    @GetMapping("/advertises/{id}")
    public Advertise getAdvertise(@PathVariable String id) {
        log.info("REST request to getAdvertise : {}", id);
        Advertise advertise = advertiseRepository.findOne(id);
        return advertise;
    }
}
