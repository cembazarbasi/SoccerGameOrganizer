package com.bayareasoccerevents.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.bayareasoccerevents.models.Participants;
import com.bayareasoccerevents.repositories.ParticipantsRepository;

@Service
public class ParticipantsService {
	
	@Autowired
	private ParticipantsRepository participantsRepository;
	
	public Participants saveParticipant(Participants participants) {
		return participantsRepository.save(participants);
	}
	
	public List<Participants> saveParticipants(List<Participants> participants){
		return participantsRepository.saveAll(participants);
	}
	
	public List<Participants> getParticipantsByEventId(int eventId){
		return participantsRepository.findByEventId(eventId);
	}
	
	public Participants getParticipantsByAttendee(String attendee){
		return participantsRepository.findByAttendee(attendee);
	}
	
	
	@Transactional
    public void deleteParticipant(int eventId, String attendee) {
        participantsRepository.deleteByEventIdAndAttendee(eventId, attendee);
    }

	public List<Participants> getAllParticipants() {
		return participantsRepository.findAll();
		
}

	

	

	

	
}
