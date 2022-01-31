package com.user.profile.resource;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.user.profile.model.User;
import com.user.profile.service.UserServiceImpl;

/**
 * @author Subba
 *
 */
@RestController
public class UserResourceImpl implements UserResource{
	
	@Autowired 
	private UserServiceImpl userService;
	
	@Override
	public List<User> getUsers(int pageNumber, int size, String sortBy) {
		StopWatch stopWatch = new StopWatch();
		stopWatch.start("UserResourceImpl.getUsers()");
		List<User> users = userService.getUsers(pageNumber, size, sortBy);
		stopWatch.stop();
		System.out.println(stopWatch.prettyPrint());
		System.out.println("Total Time took to get the data :: [" +  stopWatch.getLastTaskTimeMillis() +" ms]");
		return users;
	}
	
    public ResponseEntity<User> createUsers(@RequestBody User user, int noOfUsers) 
    {
    	userService.createUsers(noOfUsers);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand("Created").toUri();
        return ResponseEntity.created(location).build();
    }

	@Override
	public int countUsers() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<User> filterAndGetUsersByMultiValues(int pageNumber, int size, Integer filterByAge, String filterByName,
			String sortBy) {
		StopWatch stopWatch = new StopWatch();
		stopWatch.start("UserResourceImpl.filterAndGetUsersByMultiValues()");
		List<User> users = userService.queryAndfilterUsersByMultiAttrs(pageNumber, size, filterByAge, filterByName, sortBy);
		stopWatch.stop();
		System.out.println(stopWatch.prettyPrint());
		System.out.println("Total Time took to get the data :: [" +  stopWatch.getLastTaskTimeMillis() +" ms]");
		return users;
	}

	
}
