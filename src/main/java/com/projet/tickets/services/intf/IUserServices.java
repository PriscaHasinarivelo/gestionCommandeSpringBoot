package com.projet.tickets.services.intf;

import java.util.List;

import com.projet.tickets.model.User;

public interface IUserServices {
	public List<User> findAllUser() throws Exception;

	public User findById(int id) throws Exception;

	public List<User> createUser(User user) throws Exception;

	public User updateUser(User user, int id) throws Exception;

	public void deleteUser(int id) throws Exception;
}
