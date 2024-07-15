package com.projet.tickets.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projet.tickets.model.User;

public interface IUserRepository extends JpaRepository<User, Integer> {

	public User findByUsername(String username);

}
