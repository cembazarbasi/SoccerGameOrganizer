package com.bayareasoccerevents.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bayareasoccerevents.models.Event;

public interface EventRepository extends JpaRepository<Event, Integer>{

	Event findByEventName(String eventName);
}
