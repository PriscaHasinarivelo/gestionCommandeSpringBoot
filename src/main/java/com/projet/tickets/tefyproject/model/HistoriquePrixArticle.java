package com.projet.tickets.tefyproject.model;

import java.io.Serializable;
import java.time.LocalDate;

//@Entity
public class HistoriquePrixArticle implements Serializable {

	private static final long serialVersionUID = 1L;

//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id_historique_prix_article;
//	@ManyToOne(fetch = FetchType.EAGER)
//	@JoinColumn(name = "id_article")
	private Article article;
	private Double prix;
	private LocalDate date;

	public HistoriquePrixArticle() {
		super();
	}

	public int getId_historique_prix_article() {
		return id_historique_prix_article;
	}

	public void setId_historique_prix_article(int id_historique_prix_article) {
		this.id_historique_prix_article = id_historique_prix_article;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
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

}
