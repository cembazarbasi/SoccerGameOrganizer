package com.bayareasoccerevents.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bayareasoccerevents.models.Event;
import com.bayareasoccerevents.repositories.EventRepository;

@Service
public class EventService {
	
	
	@Autowired
	private EventRepository eventRepository;
	
	public Event saveEvent(Event event) {
		return eventRepository.save(event);
	}
	
	public List<Event> saveEvents(List<Event> events) {
		return eventRepository.saveAll(events);
	}
	
	public List<Event> getEvents(){
		return eventRepository.findAll();
	}
	
	public Event getEventById(int id){
		return eventRepository.findById(id).orElse(null);
	}
	
	public Event getEventByEventName(String eventName){
		return eventRepository.findByEventName(eventName);
	}
	
	public String deleteEvent(int id) {
		eventRepository.deleteById(id);
		return "event removed!! "+id;
	}
	
	public Event updateEvent(Event event) {
		Event existingEvent = eventRepository.findById(event.getId()).orElse(null);
		existingEvent.setEventName(event.getEventName());
		existingEvent.setEventDate(event.getEventDate());
		existingEvent.setEventTime(event.getEventTime());
		existingEvent.setEventLocation(event.getEventLocation());
		existingEvent.setCapacity(event.getCapacity());
		return eventRepository.save(existingEvent);
	}
}
