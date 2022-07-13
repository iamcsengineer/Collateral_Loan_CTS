package com.risk.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.risk.pojo.AuthResponse;

//Interaction with authorization microservice
@FeignClient(url="${auth.url}",name="${auth.name}")
public interface AuthClient {
	
	@GetMapping(path="/validate")
	public AuthResponse verifyToken(@RequestHeader(name="Authorization",required=true)String token);
}
