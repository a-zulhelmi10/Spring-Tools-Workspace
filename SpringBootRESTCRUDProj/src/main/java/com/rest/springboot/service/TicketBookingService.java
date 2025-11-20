package com.rest.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.springboot.entities.Ticket;
import com.rest.springboot.repository.TicketBookingRepository;

@Service
public class TicketBookingService {
	@Autowired //inject ticjetbookingrepo interface so that we dont need to create setter or construtor injection
	private TicketBookingRepository ticketBookingRepository;
	public Ticket createTicket(Ticket ticket) {
		return ticketBookingRepository.save(ticket);
	}
	public Ticket getTicketById(Integer ticketId) {
		return ticketBookingRepository.findById(ticketId).get();
	}
	public List<Ticket> getAllBookedTicket(){
		return ticketBookingRepository.findAll();
	}
	public void deleteTicket(Integer ticketId) {
		ticketBookingRepository.deleteById(ticketId);
	}
	public Ticket updateTicket(Integer ticketId, String newEmail) {
		Ticket ticketFromDb = ticketBookingRepository.findById(ticketId).get();
		ticketFromDb.setEmail(newEmail);
		Ticket updatedTicket =ticketBookingRepository.save(ticketFromDb);
		return updatedTicket;
	}
}
