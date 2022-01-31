/**
 * 
 */
package com.user.profile.util;

import java.util.Comparator;

import org.springframework.stereotype.Component;

import com.user.profile.model.User;

/**
 * @author Subba
 *
 */
@Component
public class AgeComparator implements Comparator<User>{

	@Override
	public int compare(User o1, User o2) {
		// TODO Auto-generated method stub
		return o1.getAge() - o2.getAge();
	}

}
