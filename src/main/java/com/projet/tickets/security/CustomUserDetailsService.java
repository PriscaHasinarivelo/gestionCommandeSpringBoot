package com.projet.tickets.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.projet.tickets.exception.user.UserNotFoudException;
import com.projet.tickets.model.User;
import com.projet.tickets.repository.IUserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private IUserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) {
		User user = null;
		BCryptPasswordEncoder bCryptEncoder = new BCryptPasswordEncoder();
		try {
			List<User> users = userRepo.findAll();

			for (User user2 : users) {
				if (user2.getUsername().trim().equals(username.trim())) {
					user = user2;
					break;
				}
			}

		} catch (Exception e) {
			throw new UserNotFoudException();
		}

		if (user == null) {
			throw new UserNotFoudException();
		} else {

			return new org.springframework.security.core.userdetails.User(user.getUsername(),
					bCryptEncoder.encode(user.getPassword()), new ArrayList<>());
		}

	}

}
