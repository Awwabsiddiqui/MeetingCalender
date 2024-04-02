package com.example.springrest.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface RepoSubEnt extends JpaRepository<SubEnt, Integer>, CrudRepository<SubEnt, Integer> {
	
	//public Optional<Ent> findTopByEntAndMeetingDateLessThanEqualAndMeetingEndDateGreaterThanEqual(Ent ent, Date meetingStartDate, Date meetingEndDate);
//select * from meetingcalenderhibernate where name=? and meeting_date>=? and meeting_end_date<=? limit 1

}
