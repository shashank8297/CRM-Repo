package com.infinite.crm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.infinite.crm.exception.UserNotFoundException;
import com.infinite.crm.model.Ticket;
import com.infinite.crm.service.TicketService;

@RestController
@CrossOrigin("http://localhost:3000")
public class TicketsController {

	@Autowired
	private TicketService ticketService;

	@PostMapping("/ticket")
	Ticket newTicket(@RequestBody Ticket newTicket) {
		return ticketService.addTicket(newTicket);
	}

	@GetMapping("/tickets")
	List<Ticket> getAllTickets() {
		return ticketService.findTicket();
	}

	@GetMapping("/ticket/{id}")
	Ticket getTicketById(@PathVariable Long id) {
		return ticketService.findTicket(id);
	}

	@PutMapping("/ticket/{id}")
	Ticket updateTicket(@RequestBody Ticket newTicket, @PathVariable Long id) {
		return ticketService.updateTicket(id,newTicket);
	}

	@DeleteMapping("/ticket/{id}")
	String deleteTicket(@PathVariable Long id) {
		if (!ticketService.exist(id)) {
			throw new UserNotFoundException(id);
		}
		ticketService.deleteTicket(id);
		return "User with id " + id + " has been deleted success.";
	}

}
