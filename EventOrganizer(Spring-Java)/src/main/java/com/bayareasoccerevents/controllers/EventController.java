package com.bayareasoccerevents.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bayareasoccerevents.models.Event;
import com.bayareasoccerevents.services.EventService;

@RestController

public class EventController {
	
	@Autowired
	private EventService eventService;
	
	
	@PostMapping("/dashboard")
	public Event addEvent(@RequestBody Event event) {
		return eventService.saveEvent(event);
	}
	
	@PostMapping("/addEvents")
	public List<Event> addEvents(@RequestBody List<Event> events) {
		return eventService.saveEvents(events);
	}
	
	@GetMapping("/events")
	public List<Event> findAllEvents(){
		return eventService.getEvents();
	}
	
	@GetMapping("/events/getEventById/{id}")
	public Event findEventById(@PathVariable int id) {
		return eventService.getEventById(id);
	}
	
	@GetMapping("/events/{eventName}")
	public Event findEventByName(@PathVariable String eventName) {
		return eventService.getEventByEventName(eventName);
	}
	
	
	@PutMapping("/update")
	public Event updateEvent(@RequestBody Event event) {
		return eventService.updateEvent(event);

	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteEvent(@PathVariable int id) {
		return eventService.deleteEvent(id);
	}
}