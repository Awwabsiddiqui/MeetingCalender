package com.example.springrest.entity;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface Repo extends JpaRepository<Ent, Integer> , CrudRepository<Ent, Integer>{
public List<Ent> findByName(String name);

public boolean existsById(long Id);

public boolean existsByName(String name);

public boolean existsByMeetingDate(LocalDateTime meetingDate);

public Optional<Ent> findTopByMeetingWithAndMeetingDate(String meetingWith , LocalDateTime meetingDate);

public Optional<Ent> findTopByMeetingDate(Date meetingDate);
}
