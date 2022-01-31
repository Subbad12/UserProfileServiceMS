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
public class NameComparator implements Comparator<User>{

	@Override
	public int compare(User o1, User o2) {
		 return o1.getName().compareTo( o2.getName() );
	}

}
