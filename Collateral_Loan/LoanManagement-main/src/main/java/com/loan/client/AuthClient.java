package com.loan.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.loan.pojo.AuthResponse;

//Used feignClient for interaction with Authorization Microservice
@FeignClient(url="${auth.url}",name="${auth.name}")
public interface AuthClient {
	
	//for Providing Authorization by verifying token
	@GetMapping("/validate")
	public AuthResponse verifyToken(@RequestHeader(name="Authorization",required=true)String token);
}
