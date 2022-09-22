package com.gorillaz.clients.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class Client {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id ;
	@Column(name="name")
	private String name;
	@Column(name="middle_name")
	private String middleName;
	@Column(name="last_name")
	private String lastName;

}
