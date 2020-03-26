package com.example.csrf.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class Endpoints {

	@GetMapping("/getUser")
	public String getData() {
		return "OK";
	}

	@PostMapping("/saveUser")
	public String postData(@RequestBody Object body) {
		return "Data has been saved";
	}

	@PutMapping("/updateUser")
	public String putData(@RequestBody Object body) {
		return "Data has been updated";
	}

}
