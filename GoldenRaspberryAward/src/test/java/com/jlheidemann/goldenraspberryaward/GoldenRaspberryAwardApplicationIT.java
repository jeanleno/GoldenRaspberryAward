package com.jlheidemann.goldenraspberryaward;

import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jlheidemann.goldenraspberryaward.resource.AwardResource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = GoldenRaspberryAwardApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class GoldenRaspberryAwardApplicationIT {

	@LocalServerPort
	private int port;

	TestRestTemplate restTemplate = new TestRestTemplate();

	HttpHeaders headers = new HttpHeaders();
	
	@Test
	public void findAwards() throws JSONException {
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/award"),
				HttpMethod.GET, entity, String.class);

		String expected = "{\"min\":{\"producer\":\"Joel Silver\",\"interval\":1,\"previousWin\":1990,\"followingWin\":1991},\"max\":{\"producer\":\"Matthew Vaughn\",\"interval\":13,\"previousWin\":2002,\"followingWin\":2015}}";

		JSONAssert.assertEquals(expected, response.getBody(), false);
	}
	
	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}

}
