package com.rest.springboot.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table (name="ticket")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Ticket {
	@Id
	@Column (name="ticket_id")
	private Integer ticketId;
	@Column (name="passenger_Name", nullable = false)
	private String passengerName;
	@Column (name="source_Station")
	private String sourceStation;
	@Column (name="dest_Station")
	private String destStation;
	@Column (name="email")
	private String email;
	
}
