package com.example.springrest.entity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Repository extends JpaRepository<Ent, Integer>{
public List<Ent> findByName(String name);
}
