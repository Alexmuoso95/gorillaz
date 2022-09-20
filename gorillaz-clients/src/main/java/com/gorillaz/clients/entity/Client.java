package com.gorillaz.clients.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import jdk.jfr.Name;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
public class Client {

	@Id
	private Long id ;
	
	@Name("name")
	private String name;
}
