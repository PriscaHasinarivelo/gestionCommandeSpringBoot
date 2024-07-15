package com.projet.tickets.services.intf;

import java.util.List;

import com.projet.tickets.model.Ticket;

public interface ITicketServices {

	public List<Ticket> findAllTicket() throws Exception;

	public Ticket findById(int id) throws Exception;

	public List<Ticket> createTicket(Ticket ticket) throws Exception;

	public Ticket updateTicket(Ticket ticket, int id) throws Exception;

	public Ticket assigneTicketForUser(int idTicket, int idUser) throws Exception;

	public void deleteTicket(int idTicket) throws Exception;

}
