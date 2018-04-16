package it.joint.microservices.advertise.utils;

import java.io.IOException;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import it.joint.microservices.advertise.domain.model.Advertise;

public class JSONUtil {
	
	public static Advertise derializeToObject(String object) {
        
		ObjectMapper objectMapper = new ObjectMapper();

        TypeReference<Advertise> mapType = new TypeReference<Advertise>() {
        };

        Advertise o = new Advertise();

        try {
        	o = objectMapper.readValue(object, mapType);
        } catch (IOException e) {
        }
        
        return o;
    }
	
}
