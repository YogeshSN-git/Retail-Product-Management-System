package com.auth.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.auth.controller.UnauthorizedException;
import com.auth.dao.UserDAO;
import com.auth.model.UserData;

@Service
public class CustomerDetailsService implements UserDetailsService {
	@Autowired
	private UserDAO userdao;

	@Override
	public UserDetails loadUserByUsername(String uid) {
		Optional<UserData> userData = userdao.findById(uid);
		if(userData==null) {
			throw new UnauthorizedException("unauthorized");
		}
		UserData user = userData.get();
		
		return new User(user.getUserid(), user.getUpassword(), new ArrayList<>());
	}

}
