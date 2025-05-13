package com.example.demo.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.example.demo.dto.UserDTO;

/**
 * Feign Client interface for interacting with the User Service. This allows
 * seamless communication between microservices via HTTP requests.
 */
@FeignClient(name = "user-service", url = "http://localhost:8081/api/users")
// Declares this as a Feign client for the user-service with the base URL.
public interface UserClient {

	/**
	 * Retrieves user details using the provided email.
	 * 
	 * @param email The email of the user.
	 * @return UserDTO containing user information.
	 */
	@GetMapping("/{email}") // Maps this method to a GET request for retrieving user details.
	UserDTO getUserByEmail(@PathVariable("email") String email);
}
