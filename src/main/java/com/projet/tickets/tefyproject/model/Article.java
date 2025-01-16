package com.projet.tickets.tefyproject.model;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "article", uniqueConstraints = @UniqueConstraint(columnNames = { "reference", "libelle" }))
public class Article implements Serializable {

	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id_article;
	private String reference;
	private String libelle;
	private Double prix;

	// c'est possible d'avoir des différentes prix par différente date
	private LocalDate date;
	private boolean disponible = false;
	private String url_photos;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_categorie_article")
	private CategorieArticle categorie_article;

	public Article() {
		super();
	}

	public int getId() {
		return id_article;
	}

	public void setId(int id) {
		this.id_article = id;
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

	public Double getPrix() {
		return prix;
	}

	public void setPrix(Double prix) {
		this.prix = prix;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public boolean isDisponible() {
		return disponible;
	}

	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}

	public String getUrl_photos() {
		return url_photos;
	}

	public void setUrl_photos(String url_photos) {
		this.url_photos = url_photos;
	}

	public CategorieArticle getCategorie_article() {
		return categorie_article;
	}

	public void setCategorie_article(CategorieArticle categorie_article) {
		this.categorie_article = categorie_article;
	}

}
