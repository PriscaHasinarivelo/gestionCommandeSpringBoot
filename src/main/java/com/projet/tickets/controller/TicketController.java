package com.projet.tickets.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projet.tickets.model.Ticket;
import com.projet.tickets.services.impl.TicketServices;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@Description("Gestion des Tickets simples API.")
@Api(tags = "Crud tickets API")
@RequestMapping("/api")
public class TicketController {

	private TicketServices ticketServices;

	private static final String GESTION_DES_TICKETS = "Gestion des Tickets";
	private static final String LISTE_DES_TICKETS = "Liste de tous les tickets";
	private static final String PROPRIETE_TICKET = "Propriété d'un tickets";
	private static final String IDENTIFIANT_INVALID = "L'identifiants doit être un nombre entier.";
	private static final String TICKET_NON_TROUVE = "Ticket inéxistant!";
	private static final String CREATION_TICKET = "Création d'un ticket";
	private static final String MODIFICATION_TICKET = "Modification d'un ticket";
	private static final String UN_25_TITRE = "La valeur de titre doit  contenir 1 à 25 caractères.";
	private static final String UN_50_DESCRIPTION = "La valeur du description doit contenir 1 à 50 caractères.";
	private static final String CONTROL_STATUS = "Le status de doit contenir qu'une seule caractère.";
	private static final String TICKET_ENREGISTRER = "Ticket enregistré.";
	private static final String MODIFICATION_ENREGISTRER = "Modification du ticket enregistré.";
	private static final String SUPPRESSION_TICKET = "Suppresion d'un ticket";
	private static final String TICKET_SUPPRIMER = "Ticket supprimé.";
	private static final String ASSIGNER = "Assigné un ticket à un utilisateur";
	private static final String ASSIGNATION = "Assignation du ticket enregistré.";
	private static final String UTILISATEUR_NON_TROUVE = "Utilisateur inéxistant!";
	private static final String TICKET_OWNER = "Vous n'êtes pas propriétaire du ticket, veuillez assigné les tickets qui sont la votre. Merci.";

	public TicketController(TicketServices ticketServices) {
		super();
		this.ticketServices = ticketServices;
	}

	public TicketServices getTicketServices() {
		return ticketServices;
	}

	@Autowired
	public void setTicketServices(TicketServices ticketServices) {
		this.ticketServices = ticketServices;
	}

	@Operation(description = GESTION_DES_TICKETS, summary = LISTE_DES_TICKETS)
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = LISTE_DES_TICKETS, content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = Ticket.class)) }) })
	@GetMapping("/tickets")
	public List<Ticket> getAllTickets() throws Exception {
		return ticketServices.findAllTicket();
	}

	@Operation(description = GESTION_DES_TICKETS, summary = PROPRIETE_TICKET)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = PROPRIETE_TICKET, content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Ticket.class)) }),
			@ApiResponse(responseCode = "406", description = IDENTIFIANT_INVALID, content = @Content),
			@ApiResponse(responseCode = "404", description = TICKET_NON_TROUVE, content = @Content) })
	@GetMapping("/tickets/{id}")
	public Ticket getTicketById(@PathVariable("id") int id) throws Exception {
		return ticketServices.findById(id);
	}

	@Operation(description = GESTION_DES_TICKETS, summary = CREATION_TICKET)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = TICKET_ENREGISTRER, content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Ticket.class)) }),
			@ApiResponse(responseCode = "406", description = "<ul><li>" + UN_25_TITRE + "</li><li>" + UN_50_DESCRIPTION
					+ "</li><li>" + CONTROL_STATUS + "</li></ul>", content = @Content) })
	@PostMapping("/tickets")
	public List<Ticket> createTicket(@RequestBody Ticket ticket) throws Exception {
		ticketServices.createTicket(ticket);
		return getAllTickets();
	}

	@Operation(description = GESTION_DES_TICKETS, summary = MODIFICATION_TICKET)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = MODIFICATION_ENREGISTRER, content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Ticket.class)) }),
			@ApiResponse(responseCode = "406", description = "<ul>" + "<li>" + UN_25_TITRE + "</li> <li>"
					+ UN_50_DESCRIPTION + "</li><li>" + CONTROL_STATUS + "</li><li>" + IDENTIFIANT_INVALID + "</li>"
					+ "</ul>", content = @Content),
			@ApiResponse(responseCode = "404", description = TICKET_NON_TROUVE, content = @Content) })
	@PutMapping("/tickets/{id}")
	public Ticket updateTicket(@RequestBody Ticket ticket, @PathVariable("id") int id) throws Exception {
		ticketServices.updateTicket(ticket, id);
		return getTicketById(id);
	}

	@Operation(description = GESTION_DES_TICKETS, summary = ASSIGNER)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = ASSIGNATION, content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Ticket.class)) }),
			@ApiResponse(responseCode = "404", description = "<ul><li>" + "</li>" + UTILISATEUR_NON_TROUVE + "<li>"
					+ TICKET_NON_TROUVE + "</li>" + "</ul>", content = @Content),
			@ApiResponse(responseCode = "406", description = TICKET_OWNER, content = @Content) })
	@PostMapping("/tickets/{id}/assign/{userId}")
	public Ticket assigneTicketForUser(@PathVariable("id") int idTicket, @PathVariable("userId") int idUser)
			throws Exception {
		return ticketServices.assigneTicketForUser(idTicket, idUser);
	}

	@Operation(description = GESTION_DES_TICKETS, summary = SUPPRESSION_TICKET)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = TICKET_SUPPRIMER, content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Ticket.class)) }),
			@ApiResponse(responseCode = "404", description = TICKET_NON_TROUVE, content = @Content) })

	@DeleteMapping("/tickets/{id}")
	public void deleteTicket(@PathVariable("id") int id) throws Exception {
		ticketServices.deleteTicket(id);
	}

}
