/**
 * 
 */
package com.user.profile.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import com.user.profile.model.User;
import com.user.profile.repo.UserRepository;
import com.user.profile.util.UserServiceUtil;

/**
 * @author Subba
 *
 */
@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserServiceUtil userServiceUtil;
	
	@Value("${sort.in.backend}")
	private boolean sortInBackend;
	
	/**
	 * Get users based on give input.
	 */
	@Override
	@Cacheable(cacheNames = "userCache", key="#pageNumber")
	public List<User> getUsers(int pageNumber, int size, String sortBy) {
		//First get all users and cache or index, next time onwards instead of making database query read from cache/index.
		//Considering time limitation I would go with simple ehcache technique
		List<User> users = null;
		Pageable paging = null;
		StopWatch stopWatch = new StopWatch();
		stopWatch.start("getUsers");
		
	    pageNumber = pageNumber <= 0? 0: pageNumber-1;
		
	    if(sortBy != null && !sortBy.isEmpty()) {
			paging = PageRequest.of(pageNumber, size, Sort.by(sortBy));
		}else {
			paging = PageRequest.of(pageNumber, size);
		}
		Page<User> pagedResult = userRepository.findAll(paging);
		if (pagedResult.hasContent()) {
			users = pagedResult.getContent();
		} else {
			users = new ArrayList<User>();
		}
		stopWatch.stop();
		System.out.println(stopWatch.prettyPrint());
		System.out.println("Total Time took to quering and sorting  the records from DB for getUsers :: [" +  stopWatch.getLastTaskTimeMillis() +" ms]");
		return users;
	}
	
	/**
	 * Create number of users based on the iput.
	 */
	@Override
	public void createUsers(int numberOfUsers) {
		List<User> users = new ArrayList<>();
		for(int i=0;i<numberOfUsers;i++) {
			User user = new User();
			user.setId("UserID"+i);
			if(i%5 ==0) {
				user.setAge(i*2);
			}else {
				user.setAge(i);
			}
			
			if(i%5 ==0) {
				user.setName("test");
			}else {
				user.setName("test"+i);
			}
			
			user.setAddress1("address1");
			user.setAddress2("Address2");
			users.add(user);
			
		}
		userRepository.saveAll(users);
	}

	

	@Override
	public int countUsers() {
		// TODO Auto-generated method stub
		return 0;
	}

	
	/**
	 * This method will get the users from DB with age/name/age&name combination.
	 */
	@Override
	@Cacheable(cacheNames = "userCache", key="#pageNumber")
	public List<User> queryAndfilterUsersByMultiAttrs(int pageNumber, int size, Integer filterByAge, String filterByName,
			String sortBy) {
		List<User> users = null;
		Pageable paging = null;
		StopWatch stopWatch = new StopWatch();
		stopWatch.start("queryAndfilterUsersByMultiAttrs");
		
		User user = new User();
		if(filterByAge != null && filterByAge > 0) {
			user.setAge(filterByAge);
		}
		if(filterByName != null && !filterByName.isEmpty()){
			user.setName(filterByName);
		}
	    pageNumber = pageNumber <= 0? 0: pageNumber-1;
		Example<User> example = Example.of(user, ExampleMatcher.matching());
		if(sortBy != null && !sortBy.isEmpty()) {
			paging = PageRequest.of(pageNumber, size, Sort.by(sortBy));
		}else {
			paging = PageRequest.of(pageNumber, size);
		}
		Page<User> pagedResult = userRepository.findAll(example, paging);
		if (pagedResult.hasContent()) {
			users = pagedResult.getContent();
			if(sortInBackend) {
				users = userServiceUtil.filterAndSortUsers(users, size, filterByName, sortBy);
			}
			
		} else {
			users = new ArrayList<User>();
		}
		stopWatch.stop();
		System.out.println(stopWatch.prettyPrint());
		System.out.println("Total Time took to quering and sorting  the records from DB for queryAndfilterUsersByMultiAttrs :: [" +  stopWatch.getLastTaskTimeMillis() +" ms]");
		return users;
	}

}
