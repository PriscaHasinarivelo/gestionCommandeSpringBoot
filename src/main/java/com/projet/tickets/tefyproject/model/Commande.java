package com.projet.tickets.tefyproject.model;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "commande")
//, uniqueConstraints = @UniqueConstraint(columnNames = { "reference" })
public class Commande implements Serializable {


	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id_commande;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_article")
	private Article article;
	private String reference;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_table_restaurant")
	private TableRestaurant table_restaurant;
	private LocalDate date;
	private int nombre;

	@Enumerated(EnumType.STRING)
	private StatusCommandeEnum status;

	public Commande() {
		super();
	}

	public int getId() {
		return id_commande;
	}

	public void setId(int id) {
		this.id_commande = id;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public TableRestaurant getTable_restaurant() {
		return table_restaurant;
	}

	public void setTable_restaurant(TableRestaurant table_restaurant) {
		this.table_restaurant = table_restaurant;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}


	public int getId_commande() {
		return id_commande;
	}

	public void setId_commande(int id_commande) {
		this.id_commande = id_commande;
	}

	public StatusCommandeEnum getStatus() {
		return status;
	}

	public void setStatus(StatusCommandeEnum status) {
		this.status = status;
	}

	public int getNombre() {
		return nombre;
	}

	public void setNombre(int nombre) {
		this.nombre = nombre;
	}

	public enum StatusCommandeEnum {
		EN_ATTENTE("En attente"), VALIDE("Validé"), PAYE("Payé"), ANNULER("Annulé");

		private String libelle;

		StatusCommandeEnum(String libelle) {
			this.libelle = libelle;
		}

		public String getLibelle() {
			return libelle;
		}

		public StatusCommandeEnum libelleToEnum(String libelle) {
			switch(libelle) {
			case "En attente": return EN_ATTENTE;
			case "Validé": return VALIDE;
			case "Payé": return PAYE;
			case "Annulé":
				return ANNULER;
			default: return EN_ATTENTE;
			
			}
		}

	}

}
