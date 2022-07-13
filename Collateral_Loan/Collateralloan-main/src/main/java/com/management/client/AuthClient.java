package com.management.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import com.management.pojo.AuthResponse;

//A client that is used to perform a GET request to the authorization microservice for 
@FeignClient(url="${auth.url}",name="${auth.name}")
public interface AuthClient {
	
	@GetMapping(path="/validate")
	public AuthResponse verifyToken(@RequestHeader(name="Authorization",required=true)String token);
}
