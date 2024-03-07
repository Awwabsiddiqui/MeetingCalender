package com.example.springrest.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "meetingcalenderhibernatedSubEnt")
public class SubEnt {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long subId;

	@Column(name = "meeting_with")
	private String meetingWith;
	
	@Column(name = "meeting_with_id")
	private long meetingWithId;
	
	@Column(name = "meeting_date", /* nullable = false, */ columnDefinition = "DATE DEFAULT '1900-01-01 00:00'")
	// @DateTimeFormat(pattern = "dd/MM/yyyy")
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	Date meetingDate;

	@Column(name = "meeting_end_date", /* nullable = false, */ columnDefinition = "DATE DEFAULT '1900-01-01 00:00'")
	// @DateTimeFormat(pattern = "dd/MM/yyyy")
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	Date meetingEndDate;

//	@ManyToOne
//	@JoinColumn(name="sefk")
//	private Ent ent;
	
	
    @ManyToOne
    @ToString.Exclude
    public Ent ent;
	
}