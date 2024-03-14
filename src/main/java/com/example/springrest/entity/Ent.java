package com.example.springrest.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
	private String authority;

//	@Column(name = "meeting_with")
//	private String meetingWith;

//	@ColumnDefault("''")
//	private String meeting = "";

//	@Column(name = "meeting_date", nullable = false, columnDefinition = "DATETIME DEFAULT '1900-01-01 00:00:00'")
	// @Temporal(TemporalType.TIMESTAMP)
//	private Date meetingDateTime;

//	@Column(name = "meeting_date", /* nullable = false, */ columnDefinition = "DATE DEFAULT '1900-01-01 00:00'")
//	// @DateTimeFormat(pattern = "dd/MM/yyyy")
//	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
//	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
//	Date meetingDate;

//	@Column(name = "meeting_end_date", /* nullable = false, */ columnDefinition = "DATE DEFAULT '1900-01-01 00:00'")
//	// @DateTimeFormat(pattern = "dd/MM/yyyy")
//	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
//	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
//	Date meetingEndDate;

//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//	@JoinColumn(name = "SEFK", referencedColumnName = "id")
//	public List<SubEnt> subEnts;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER , mappedBy = "ent")
//	@JoinColumn(name = "sefk")
	public List<SubEnt> subEnts;
	
}