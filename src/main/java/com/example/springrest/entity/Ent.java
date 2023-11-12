package com.example.springrest.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.*;
import javax.print.attribute.standard.DateTimeAtCompleted;

import org.hibernate.annotations.ColumnDefault;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "meetingcalenderhibernate")
public class Ent {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long id;

	private String name;

	@Column(name = "meeting_with")
	private String meetingWith;

	@ColumnDefault("''")
	private String meeting = "";

//	@Column(name = "meeting_date", nullable = false, columnDefinition = "DATETIME DEFAULT '1900-01-01 00:00:00'")
	// @Temporal(TemporalType.TIMESTAMP)
//	private Date meetingDateTime;

	@Column(name = "meeting_date", /* nullable = false, */ columnDefinition = "DATE DEFAULT '1900-01-01 00:00'")
	// @DateTimeFormat(pattern = "dd/MM/yyyy")
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	Date meetingDate;

	@Column(name = "meeting_end_date", /* nullable = false, */ columnDefinition = "DATE DEFAULT '1900-01-01 00:00'")
	// @DateTimeFormat(pattern = "dd/MM/yyyy")
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	Date meetingEndDate;

}