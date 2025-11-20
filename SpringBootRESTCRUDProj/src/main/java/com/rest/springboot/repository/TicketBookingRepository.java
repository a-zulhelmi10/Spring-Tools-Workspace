package com.rest.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rest.springboot.entities.Ticket;

@Repository
public interface TicketBookingRepository extends JpaRepository<Ticket,Integer>{}
