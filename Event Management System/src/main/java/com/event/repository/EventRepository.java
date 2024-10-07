package com.event.repository;

import com.event.model.EventModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<EventModel, Long>
{
    Optional<EventModel> findById(Long id);
}
