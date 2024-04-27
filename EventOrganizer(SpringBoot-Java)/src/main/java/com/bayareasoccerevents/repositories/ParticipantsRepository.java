package com.bayareasoccerevents.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.bayareasoccerevents.models.Participants;

public interface ParticipantsRepository extends JpaRepository<Participants, Integer>{

	Participants findByAttendee(String attendee);

	List<Participants> findByEventId(int eventId);	

	void deleteByEventIdAndAttendee(int eventId, String attendee);

	

	

	

	
	

	

}
