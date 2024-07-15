package com.projet.tickets.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.projet.tickets.exception.FormatIdentifiantExeption;
import com.projet.tickets.exception.ticket.StatusInvalidException;
import com.projet.tickets.exception.ticket.TicketDescriptionMalSaisieException;
import com.projet.tickets.exception.ticket.TicketNotFoundException;
import com.projet.tickets.exception.ticket.TicketTitreMalSaisieException;
import com.projet.tickets.exception.user.UserNotFoudException;
import com.projet.tickets.exception.user.UserNotOwnerException;
import com.projet.tickets.model.Ticket;
import com.projet.tickets.model.User;
import com.projet.tickets.repository.ITicketRepository;
import com.projet.tickets.repository.IUserRepository;
import com.projet.tickets.services.intf.ITicketServices;

@Service
public class TicketServices implements ITicketServices {

	@Autowired
	private ITicketRepository ticketRepository;

	@Autowired
	private IUserRepository userRepository;

	public TicketServices() {
		super();
	}

	@Override
	public List<Ticket> findAllTicket() throws Exception {
		return ticketRepository.findAll();
	}

	@Override
	public Ticket findById(int id) throws Exception {
		if (!String.valueOf(id).matches("[^a-zA-Z]*")) {
			throw new FormatIdentifiantExeption();
		}

		if (!ticketRepository.existsById(id)) {
			throw new TicketNotFoundException();
		}
		return ticketRepository.findById(id).get();
	}

	@Override
	public List<Ticket> createTicket(Ticket ticket) throws Exception {
		validateSaisie(ticket);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		org.springframework.security.core.userdetails.User connectedUser = null;
		if (authentication != null) {
			Object principal = authentication.getPrincipal();

			if (principal instanceof UserDetails) {
				connectedUser = (org.springframework.security.core.userdetails.User) principal;
			}

			User ownerUser = findByUsername(connectedUser.getUsername());
			ticket.setUser(ownerUser);
			ticketRepository.save(ticket);
		}

		return findAllTicket();
	}

	private void validateSaisie(Ticket ticket) {
		if (ticket.getDescription().length() > 50) {
			throw new TicketDescriptionMalSaisieException();
		}
		if (ticket.getTitre().length() > 25) {
			throw new TicketTitreMalSaisieException();
		}
		if (ticket.getStatus() == null) {
			throw new StatusInvalidException();
		}

	}

	@Override
	public Ticket updateTicket(Ticket ticket, int id) throws Exception {
		if (!String.valueOf(id).matches("[^a-zA-Z]*")) {
			throw new FormatIdentifiantExeption();
		}

		if (!ticketRepository.existsById(id)) {
			throw new TicketNotFoundException();
		}
		validateSaisie(ticket);

		Ticket updatedTicket = findById(id);
		updatedTicket.setDescription(ticket.getDescription());
		updatedTicket.setTitre(ticket.getTitre());
		updatedTicket.setStatus(ticket.getStatus());
		ticketRepository.save(updatedTicket);

		return findById(id);
	}

	@Override
	public Ticket assigneTicketForUser(int idTicket, int idUser) throws Exception {

		if (!String.valueOf(idTicket).matches("[^a-zA-Z]*")) {
			throw new FormatIdentifiantExeption();
		}

		if (!String.valueOf(idUser).matches("[^a-zA-Z]*")) {
			throw new FormatIdentifiantExeption();
		}

		if (!ticketRepository.existsById(idTicket)) {
			throw new TicketNotFoundException();
		}

		if (!userRepository.existsById(idUser)) {
			throw new UserNotFoudException();
		}

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User connectedUser = (User) authentication.getDetails();
		User userToAssign = userRepository.findById(idUser).get();
		Ticket ticket = findById(idTicket);

		// Verification si l'utilisateur connecté est bien propietaire du ticket à
		// assigné
		if (connectedUser.getUsername().trim().equals(userToAssign.getUsername().trim())
				&& connectedUser.getPassword().trim().equals(userToAssign.getPassword().trim())) {
			ticket.setUser(userToAssign);
			ticketRepository.save(ticket);
			return ticket;
		} else {
			throw new UserNotOwnerException();

		}

	}

	@Override
	public void deleteTicket(int idTicket) throws Exception {
		if (!String.valueOf(idTicket).matches("[^a-zA-Z]*")) {
			throw new FormatIdentifiantExeption();
		}

		if (!ticketRepository.existsById(idTicket)) {
			throw new TicketNotFoundException();
		}

		ticketRepository.deleteById(idTicket);
	}

	public User findByUsername(String username) {
		List<User> listeUser = userRepository.findAll();
		return listeUser.stream().filter(t -> username.trim().equals(t.getUsername().trim())).findFirst().get();

	}

}
