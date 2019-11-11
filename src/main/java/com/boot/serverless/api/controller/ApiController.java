package com.boot.serverless.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {

	@GetMapping("/v1/user")
	public ResponseEntity<String> getFirstUser() {
		return new ResponseEntity<String>("Successful message", HttpStatus.OK);
	}
}
