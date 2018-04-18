package it.joint.microservices.advertise.tests.util;

import com.fasterxml.jackson.databind.ObjectMapper;

public class TestUtil {

	public static String convertObjectToJsonString(Object object) throws Exception {
		return new ObjectMapper().writeValueAsString(object);
	}
}
