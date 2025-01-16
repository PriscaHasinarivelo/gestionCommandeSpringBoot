package com.projet.tickets.tefyproject.model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class CategorieArticle implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id_categorie_article;
	private String reference;
	private String libelle;

	public CategorieArticle() {
		super();
	}

	public int getId_categorie_article() {
		return id_categorie_article;
	}

	public void setId_categorie_article(int id_categorie_article) {
		this.id_categorie_article = id_categorie_article;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}


}
