package com.rest.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rest.springboot.entities.Ticket;
import com.rest.springboot.service.TicketBookingService;

@RestController
@RequestMapping(value="/api/tickets") //this is the entry point of the url from REST API which is http://localhost:8080/api/tickets
public class TicketBookingController {
	@Autowired
	private TicketBookingService ticketBookingService;
	@PostMapping(value="/create") //Post means Insert command //The rest API is http://localhost:8080/api/tickets/create
	public Ticket createTicket(@RequestBody Ticket ticket) { //pass ticket object as args 
		return ticketBookingService.createTicket(ticket);
	}
	@GetMapping(value="/ticket/{ticketId}") //http://localhost:8080/api/tickets/ticket/103
	public Ticket getTicketById(@PathVariable("ticketId")Integer ticketId) {
		return ticketBookingService.getTicketById(ticketId);
	}
	@GetMapping(value="/ticket/alltickets") //http://localhost:8080/api/tickets/ticket/alltickets
	public List<Ticket> getAllBookedTicket() {
		return ticketBookingService.getAllBookedTicket();
	}
	@DeleteMapping(value="/ticket/{ticketId}") //http://localhost:8080/api/tickets/ticket/103
	public void deleteTicket(@PathVariable("ticketId")Integer ticketId) {
		ticketBookingService.deleteTicket(ticketId);
	}
	@PutMapping(value="/ticket/{ticketId}/{newEmail:.+}")
	public Ticket updateTicket(@PathVariable("ticketId")Integer ticketId, @PathVariable("newEmail")String newEmail) {
		return ticketBookingService.updateTicket(ticketId, newEmail);
	}
}
