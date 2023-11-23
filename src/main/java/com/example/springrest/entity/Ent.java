package com.example.springrest.entity;

import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;
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
@Table(name = "meetingcalenderhibernate")
public class Ent {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long id;

	private String name;
	private String password;

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
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	Date meetingDate;

	@Column(name = "meeting_end_date", /* nullable = false, */ columnDefinition = "DATE DEFAULT '1900-01-01 00:00'")
	// @DateTimeFormat(pattern = "dd/MM/yyyy")
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	Date meetingEndDate;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "SEFK", referencedColumnName = "id")
	public Collection<SubEnt> subEnts;

}