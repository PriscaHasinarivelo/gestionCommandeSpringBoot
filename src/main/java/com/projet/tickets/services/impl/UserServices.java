package com.projet.tickets.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.projet.tickets.exception.FormatIdentifiantExeption;
import com.projet.tickets.exception.ticket.TicketDescriptionMalSaisieException;
import com.projet.tickets.exception.user.LongeurMailInvalidException;
import com.projet.tickets.exception.user.MailInvalidException;
import com.projet.tickets.exception.user.UserNotFoudException;
import com.projet.tickets.model.User;
import com.projet.tickets.repository.IUserRepository;
import com.projet.tickets.services.intf.IUserServices;

@Service
public class UserServices implements IUserServices {

	@Autowired
	private IUserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public UserServices() {
		super();
	}

	@Override
	public List<User> findAllUser() {
		return userRepository.findAll();
	}

	@Override
	public User findById(int id) throws Exception {
		if (!String.valueOf(id).matches("[^a-zA-Z]*")) {
			throw new FormatIdentifiantExeption();
		}

		if (!userRepository.existsById(id)) {
			throw new UserNotFoudException();
		}
		return userRepository.findById(id).get();
	}

	@Override
	public List<User> createUser(User user) throws Exception {
		validateSaisie(user);
		userRepository.save(user);
		return userRepository.findAll();
	}

	@Override
	public User updateUser(User user, int id) throws Exception {

		if (!String.valueOf(id).matches("[^a-zA-Z]*")) {
			throw new FormatIdentifiantExeption();
		}

		if (!userRepository.existsById(id)) {
			throw new UserNotFoudException();
		}

		validateSaisie(user);

		User updateuser = findById(id);
		updateuser.setEmail(user.getEmail());
		updateuser.setUsername(user.getUsername());
		updateuser.setPassword(passwordEncoder.encode(user.getPassword()));

		return userRepository.save(updateuser);
	}

	private void validateSaisie(User user) {
		if (user.getUsername().length() > 50) {
			throw new TicketDescriptionMalSaisieException();
		}
		if (!user.getEmail().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+$")) {
			throw new MailInvalidException();
		}
		if (user.getEmail().length() > 50) {
			throw new LongeurMailInvalidException();
		}

	}

	@Override
	public void deleteUser(int id) throws Exception {
		if (!String.valueOf(id).matches("[^a-zA-Z]*")) {
			throw new FormatIdentifiantExeption();
		}

		if (!userRepository.existsById(id)) {
			throw new UserNotFoudException();
		}

		userRepository.deleteById(id);
	}

}
