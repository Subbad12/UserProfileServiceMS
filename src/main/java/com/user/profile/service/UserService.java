/**
 * 
 */
package com.user.profile.service;

import java.util.List;

import com.user.profile.model.User;

/**
 * @author Subba
 *
 */
public interface UserService {
	public List<User> getUsers(int pageNumber, int size, String sortBy);
	public int countUsers();
	public List<User> queryAndfilterUsersByMultiAttrs(int pageNumber, int size, Integer filterByAge, String filterByName, String sortBy);
	public void createUsers(int numberOfUsers);
}
