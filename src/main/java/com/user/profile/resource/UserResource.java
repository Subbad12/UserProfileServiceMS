/**
 * 
 */
package com.user.profile.resource;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.user.profile.model.User;

/**
 * @author Subba
 *
 */
//TO-DO fix this later to avoid hard coded values
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/profile/user")
public interface UserResource {

	@GetMapping(path="/getUsers", produces = "application/json")
	public List<User> getUsers(@RequestParam(required=true,defaultValue="0") int pageNumber, @RequestParam(required=true,defaultValue="100") int size, String sortBy);
	
	@GetMapping(path="/countUsers", produces = "application/json")
	public int countUsers();
	
	@GetMapping(path="/filterUsers", produces = "application/json")
	public List<User> filterAndGetUsersByMultiValues(@RequestParam(required=true,defaultValue="0") int pageNumber, @RequestParam(required=true,defaultValue="100") int size, Integer filterByAge, String filterByName, String sortBy);
	
	@PostMapping(path="/createUsers", consumes = "application/json", produces = "application/json")
	public ResponseEntity<User> createUsers(@RequestBody User user, @RequestParam(required=true,defaultValue="100") int noOfusers);
}
