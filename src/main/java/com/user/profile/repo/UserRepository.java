package com.user.profile.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.user.profile.model.User;

/**
 * @author Subba
 *
 */
public interface UserRepository extends JpaRepository<User, String>{

	
}
