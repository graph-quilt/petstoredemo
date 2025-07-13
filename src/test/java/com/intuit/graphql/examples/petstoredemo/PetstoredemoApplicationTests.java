package com.intuit.graphql.examples.petstoredemo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PetstoredemoApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	void contextLoads() {
	}

	@Test
	void petQuery() {
		String query = "{\"query\": \"{ pet(id: 1) { id name } }\"}";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<>(query, headers);

		ResponseEntity<String> response = restTemplate.postForEntity("/graphql", entity, String.class);

		assertTrue(response.getStatusCode().is2xxSuccessful());
		assertTrue(response.getBody().contains("Buddy"));
	}
}
