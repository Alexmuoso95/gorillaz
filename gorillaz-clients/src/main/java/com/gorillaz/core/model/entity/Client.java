package com.gorillaz.core.model.entity;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
@Table(name = "clients")
public class Client {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotBlank
	@NotNull
	@Column(name = "name")
	private String name;
	@NotBlank
	@NotNull
	@Column(name = "last_name")
	private String lastName;
	@NotBlank
	@NotNull
	@Email
	@Column(name = "email")
	private String email;
	@NotBlank
	@NotNull
	@Column(name = "phone_number")
	private String phoneNumber;
	@Column(name = "create_at")
	@Temporal(TemporalType.DATE)
	private Date createAt;
		@PrePersist
		public void prePersist() {
			createAt = new Date();
		}
	@Column(name = "photo")
	private String photo;
	
//	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//	@JoinTable(name = "clients_cars" , 
//			   joinColumns = @JoinColumn(name="client_id"), inverseJoinColumns = @JoinColumn(name="car_id"),
//			   uniqueConstraints = {@UniqueConstraint(columnNames = {"client_id","car_id"})})
//	private Set<Car> cars ;
//	
//	
//	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//	@JoinTable(name = "clients_invoices" , 
//			   joinColumns = @JoinColumn(name="client_id"), inverseJoinColumns = @JoinColumn(name="invoice_id"),
//			   uniqueConstraints = {@UniqueConstraint(columnNames = {"client_id","invoice_id"})})
//	private List<Invoice> invoices;
//	
//	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//	@JoinTable(name = "clients_addresses" , 
//			   joinColumns = @JoinColumn(name="client_id"), inverseJoinColumns = @JoinColumn(name="address_id"),
//			   uniqueConstraints = {@UniqueConstraint(columnNames = {"client_id","address_id"})})
//	private List<Address> addresses;
}