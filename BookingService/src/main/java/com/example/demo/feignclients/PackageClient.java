package com.example.demo.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.demo.dto.PackageDTO;
import java.util.List;

/**
 * Feign Client interface for interacting with the Package Service. This allows
 * seamless communication between microservices using HTTP requests.
 */
@FeignClient(name = "package-service", url = "http://localhost:8083/api/packages")
// Declares this as a Feign client with a specific microservice name and base URL.
public interface PackageClient {

	/**
	 * Fetches all available packages from the Package Service.
	 * 
	 * @return A list of PackageDTO objects.
	 */
	@GetMapping // Maps this method to a GET request on the specified URL.
	List<PackageDTO> fetchAllPackages();
}
