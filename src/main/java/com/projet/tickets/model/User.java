package com.projet.tickets.model;

import java.io.Serializable;

import org.springframework.context.annotation.Description;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "_user")
@Description("Gestion des utilisateurs")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Min(value = 2, message = "Le nombre de caractère minimal est 2.")
	@Max(value = 25, message = "Le nombre de caractère maximal est 25.")
	@NotNull
	private String username;

	@Min(value = 2, message = "Le nombre de caractère minimal est 2.")
	@Max(value = 25, message = "Le nombre de caractère maximal est 50.")
	@Email(regexp = ".*@.*\\..*", message = "Le mail doit être un mail valide.")
	@NotNull
	private String email;

	@Column(name = "pass")
	@NotNull
	private String password;

	public User() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
