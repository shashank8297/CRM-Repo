package com.infinite.crm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infinite.crm.exception.UserNotFoundException;
import com.infinite.crm.model.Ticket;
import com.infinite.crm.repository.TicketRepository;

@Service
public class TicketService {

	@Autowired
	TicketRepository ticketRepository;

	public Ticket addTicket(Ticket newTicket) {
		// TODO Auto-generated method stub
		return ticketRepository.save(newTicket);
	}

	public List<Ticket> findTicket() {
		// TODO Auto-generated method stub
		return ticketRepository.findAll();
	}

	public Ticket findTicket(Long id) {
		// TODO Auto-generated method stub
		return ticketRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
	}

	public Ticket updateTicket(Long id, Ticket newTicket) {
		// TODO Auto-generated method stub
		return ticketRepository.findById(id).map(ticket -> {
			ticket.setUsername(newTicket.getUsername());
			ticket.setEmail(newTicket.getEmail());
			ticket.setIssue(newTicket.getIssue());
			ticket.setStatus(newTicket.getStatus());
			return ticketRepository.save(ticket);
		}).orElseThrow(() -> new UserNotFoundException(id));
	}

	public boolean exist(Long id) {
		// TODO Auto-generated method stub
		return ticketRepository.existsById(id);
		
	}

	public void deleteTicket(Long id) {
		// TODO Auto-generated method stub
		ticketRepository.deleteById(id);
	};
}
