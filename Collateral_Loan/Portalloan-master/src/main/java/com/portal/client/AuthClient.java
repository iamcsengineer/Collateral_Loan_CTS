package com.portal.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.portal.pojo.AuthResponse;
import com.portal.pojo.UserData;

// For Interaction with Authorization Microservice
@FeignClient(url="${auth.url}",name="${auth.name}")
public interface AuthClient {
	
	@PostMapping("/login")
	public UserData login(@RequestBody UserData userlogincredentials);
	
	@GetMapping("/validate")
	public AuthResponse verifyToken(@RequestHeader(name="Authorization",required=true)String token);
}
