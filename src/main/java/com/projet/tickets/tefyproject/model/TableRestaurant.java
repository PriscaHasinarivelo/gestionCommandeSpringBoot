package com.projet.tickets.tefyproject.model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "tablerestaurant", uniqueConstraints = @UniqueConstraint(columnNames = { "numero" }))
public class TableRestaurant implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id_table_restaurant;
	private int numero;

	public TableRestaurant() {
		super();
	}

	public int getId() {
		return id_table_restaurant;
	}

	public void setId(int id) {
		this.id_table_restaurant = id;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

}
