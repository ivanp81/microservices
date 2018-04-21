package it.joint.microservices.advertise.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import it.joint.microservices.advertise.domain.model.Advertise;

public class JSONUtil {

    public static Advertise deserializeToObject(String object) throws IOException {

	ObjectMapper objectMapper = new ObjectMapper();

	TypeReference<Advertise> mapType = new TypeReference<Advertise>() {
	};

	return objectMapper.readValue(object, mapType);
    }

    public static String serializeToJson(Object object) throws JsonProcessingException {

	ObjectMapper mapper = new ObjectMapper();
	mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
	String jsonInString = mapper.writeValueAsString(object);
	return jsonInString;
    }

}
