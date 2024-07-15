package com.projet.tickets.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projet.tickets.model.Ticket;

public interface ITicketRepository extends JpaRepository<Ticket, Integer> {

}
