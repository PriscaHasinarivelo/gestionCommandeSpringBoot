package com.projet.tickets.model;

import java.io.Serializable;

import org.springframework.context.annotation.Description;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@Entity
@Description("Gestion des tickets simple")
public class Ticket implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Min(value = 2, message = "Le nombre de caractère minimal est 2.")
	@Max(value = 25, message = "Le nombre de caractère maximal est 25.")
	private String titre;

	@Min(value = 2, message = "Le nombre de caractère minimal est 2.")
	@Max(value = 50, message = "Le nombre de caractère maximal est 50.")
	@Column(name = "ticket_description")
	private String description;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_user")
	private User user;

	@Min(value = 7, message = "Le nombre de caractère minimal est 7.")
	@Max(value = 8, message = "Le nombre de caractère maximal est 8.")
	@Column(name = "ticket_status")
	private StatusTicket status;

	@Override
	public String toString() {

		return String.format("Ticket[id=%d, titre=%s, description=%s]", id, titre, description);
	}

	public Ticket() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public StatusTicket getStatus() {
		return status;
	}

	public void setStatus(StatusTicket status) {
		this.status = status;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public enum StatusTicket {
		Annuler("A", "Annuler"), Terminer("T", "Terminer"), En_Cours("E", "En cours");

		private String libelle;

		private String code;

		public String getCode() {
			return code;
		}

		public String getLibelle() {
			return libelle;
		}

		StatusTicket(String code, String libelle) {
			this.libelle = libelle;
			this.code = code;
		}

	}

}
