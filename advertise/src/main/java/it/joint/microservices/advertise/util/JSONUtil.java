package it.joint.microservices.advertise.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class JSONUtil {
		
	public static String serializeToJson(Object object) throws JsonProcessingException {
        
    	ObjectMapper mapper = new ObjectMapper();
    	mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        String jsonInString = mapper.writeValueAsString(object);
        return jsonInString;
    }

}
