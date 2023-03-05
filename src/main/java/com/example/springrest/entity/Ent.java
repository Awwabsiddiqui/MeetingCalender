package com.example.springrest.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "meetingcalender")
public class Ent {

@Id 
@GeneratedValue(strategy = GenerationType.AUTO)
public long id;
private String name;
private String meeting;
}