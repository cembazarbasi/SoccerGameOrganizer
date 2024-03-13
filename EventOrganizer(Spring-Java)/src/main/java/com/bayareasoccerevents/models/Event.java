package com.bayareasoccerevents.models;

import java.sql.Time;
import java.util.Date;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="events")
@Data
public class Event {
	
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String eventName;
	private String eventDate;	
	private String eventTime;	
	private String eventLocation;
	private int capacity;
	
	
}
