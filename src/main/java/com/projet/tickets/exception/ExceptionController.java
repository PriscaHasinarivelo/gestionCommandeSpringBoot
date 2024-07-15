package com.projet.tickets.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.projet.tickets.exception.ticket.StatusInvalidException;
import com.projet.tickets.exception.ticket.TicketDescriptionMalSaisieException;
import com.projet.tickets.exception.ticket.TicketNotFoundException;
import com.projet.tickets.exception.ticket.TicketTitreMalSaisieException;
import com.projet.tickets.exception.user.MailInvalidException;
import com.projet.tickets.exception.user.UserNotFoudException;
import com.projet.tickets.exception.user.UserNotOwnerException;

@ControllerAdvice
public class ExceptionController {

	// Ticket
	@ExceptionHandler(value = TicketNotFoundException.class)
	public ResponseEntity<Object> exception(TicketNotFoundException exception) {
		return new ResponseEntity<>("Ticket inexistant!", HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = TicketDescriptionMalSaisieException.class)
	public ResponseEntity<Object> exception(TicketDescriptionMalSaisieException exception) {
		return new ResponseEntity<>("La valeur de champ description doit contenir un mot de 50 caractères.",
				HttpStatus.NOT_ACCEPTABLE);
	}

	@ExceptionHandler(value = TicketTitreMalSaisieException.class)
	public ResponseEntity<Object> exception(TicketTitreMalSaisieException exception) {
		return new ResponseEntity<>("La valeur de champ titre doit contenir un mot de 25 caractères.",
				HttpStatus.NOT_ACCEPTABLE);
	}

	@ExceptionHandler(value = StatusInvalidException.class)
	public ResponseEntity<Object> exception(StatusInvalidException exception) {
		return new ResponseEntity<>("La status est invalide.", HttpStatus.NOT_ACCEPTABLE);
	}

	// User
	@ExceptionHandler(value = UserNotFoudException.class)
	public ResponseEntity<Object> exception(UserNotFoudException exception) {
		return new ResponseEntity<>("Utilisateur inexistant!", HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = FormatIdentifiantExeption.class)
	public ResponseEntity<Object> exception(FormatIdentifiantExeption exception) {
		return new ResponseEntity<>("Les identifiants doivent être un nombre entier.", HttpStatus.NOT_ACCEPTABLE);
	}

	@ExceptionHandler(value = MailInvalidException.class)
	public ResponseEntity<Object> exception(MailInvalidException exception) {
		return new ResponseEntity<>("Valeur du mail erronée, un mail doit contenir un . et un @ .",
				HttpStatus.NOT_ACCEPTABLE);
	}

	@ExceptionHandler(value = UserNotOwnerException.class)
	public ResponseEntity<Object> exception(UserNotOwnerException exception) {
		return new ResponseEntity<>(
				"Vous n'êtes pas propriétaire du ticket, veuillez assigné les tickets qui sont la votre. Merci.",
				HttpStatus.NOT_ACCEPTABLE);
	}

}
