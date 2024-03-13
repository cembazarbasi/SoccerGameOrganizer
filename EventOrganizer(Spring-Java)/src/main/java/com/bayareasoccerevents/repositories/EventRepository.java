package com.bayareasoccerevents.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bayareasoccerevents.models.Event;


public interface EventRepository extends JpaRepository<Event, Integer>{

	Event findByEventName(String eventName);

}
