package com.gorillaz.core.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "address")
public class Address {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotBlank
	@NotNull
	@Column(name = "street")
	private String street ;
	@NotBlank
	@NotNull
	@Column(name = "colony")
	private String colony;
	@NotBlank
	@NotNull
	@Column(name = "num_ext")
	private String numExt ;
	@NotBlank
	@NotNull
	@Column(name = "num_int")
	private String numInt ;
	@NotBlank
	@NotNull
	@Column(name = "postal_code")
	private String cp;
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "client_id")
	private Client client;
	
//	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//	private List<Invoice>invoice;
//	

}