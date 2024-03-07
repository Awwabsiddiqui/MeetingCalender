package com.example.springrest.entity;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface RepoSubEnt extends JpaRepository<SubEnt, Integer>, CrudRepository<SubEnt, Integer> {
	
	//public Optional<Ent> findTopByEntAndMeetingDateLessThanEqualAndMeetingEndDateGreaterThanEqual(Ent ent, Date meetingStartDate, Date meetingEndDate);
//select * from meetingcalenderhibernate where name=? and meeting_date>=? and meeting_end_date<=? limit 1

}
