package com.user.profile.util;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.user.profile.model.User;

/**
 * @author Subba
 *
 */
@Component
public class UserServiceUtil {
	

	@Autowired
	private AgeComparator ageComparator;
	
	@Autowired
	private NameComparator nameComparator;

	/**
	 * Filter and/sort users using input.
	 * 
	 * @param users
	 * @param ageFilter
	 * @param nameFilter
	 * @param sortBy
	 * @return users
	 */
	public List<User> filterAndSortUsers(List<User> users, int ageFilter, String nameFilter, String sortBy){
		if(sortBy != null && !sortBy.isEmpty()) {
			users = users.parallelStream().filter(user -> user.getAge() > ageFilter || user.getName().equalsIgnoreCase(nameFilter)).sorted(getComparator(sortBy)).collect(Collectors.toList());
		}else {
			users = users.parallelStream().filter(user -> user.getAge() > ageFilter || user.getName().equalsIgnoreCase(nameFilter)).collect(Collectors.toList());
		}
		return users;
	}
	
	/**
	 * @param sortBy
	 * @return comparator
	 */
	private Comparator<User> getComparator(String sortBy){
		if(sortBy.isEmpty() && sortBy.equals("age")) {
			return ageComparator;
		}else {
			return nameComparator;
		}
	}
}
