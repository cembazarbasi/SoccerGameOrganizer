package com.bayareasoccerevents.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.bayareasoccerevents.models.Participants;
import com.bayareasoccerevents.services.ParticipantsService;

@RestController
public class ParticipantsController {

	@Autowired
	private ParticipantsService participantsService;
	
	@PostMapping("/addParticipant")
	public Participants addParticipant(@RequestBody Participants participants) {
		return participantsService.saveParticipant(participants);
	}
	
	@GetMapping("/participants")
	public List<Participants> findAllParticipants(){
		return participantsService.getAllParticipants();
	}
	
	@GetMapping("/participants/getParticipantsByEventId/{eventId}")
	public List<Participants> findParticipantsByEventId(@PathVariable int eventId) {
	    return participantsService.getParticipantsByEventId(eventId);
	}		
	
	@DeleteMapping("/deleteAttendee/{eventId}/{attendee}")
	public String deleteParticipant(@PathVariable int eventId, @PathVariable String attendee) {
		 participantsService.deleteParticipant(eventId, attendee);
		 return "RSVP Canceled";
	}
	
}
