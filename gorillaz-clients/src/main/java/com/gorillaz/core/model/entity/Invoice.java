package com.gorillaz.core.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
@Table(name = "invoices")
public class Invoice {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotNull
	@Column(name = "name")
	private String name;
	@NotNull
	@Column(name = "fiscal_taxes_registry")
	private String fTR;
	@NotNull
	@Column(name = "fical_regimen")
	private String fR;
	@NotNull
	@Column(name = "description")
	private String desc;
	@NotNull
	@Column(name = "create_at")
	@Temporal(TemporalType.DATE)
	private Date createAt;
	@Column(name = "client_id")
	private Long clientId;

	
//	
//	@NotNull
//	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//	@JoinTable(name = "invoices_address" , 
//			   joinColumns = @JoinColumn(name="invoice_id"), inverseJoinColumns = @JoinColumn(name="address_id"),
//			   uniqueConstraints = {@UniqueConstraint(columnNames = {"invoice_id","address_id"})})
//	private Address address;
//	
//	@NotNull
//	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//	private Client client;
}
