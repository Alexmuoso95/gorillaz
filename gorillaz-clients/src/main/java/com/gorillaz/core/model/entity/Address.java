package com.gorillaz.core.model.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
@Table(name = "addresses")
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
	@Column(name = "num_out")
	private String numOut ;
	@NotBlank
	@NotNull
	@Column(name = "num_in")
	private String numIn ;
	@NotBlank
	@NotNull
	@Column(name = "postal_code")
	private String pC;
//	
//	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//	private List<Invoice>invoice;
//	
//	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//	private List<Client> clients;
}