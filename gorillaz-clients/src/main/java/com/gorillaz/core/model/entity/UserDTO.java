package com.gorillaz.core.model.entity;

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
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class UserDTO {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name = "name" , unique = true, length = 10)
	private String name;
	@Column(name = "password")
	private String password;
	@NotNull
	@Column(name = "status")
	private Boolean status;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "users_roles" , 
			   joinColumns = @JoinColumn(name="user_id"), inverseJoinColumns = @JoinColumn(name="role_id"),
			   uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id","role_id"})})
	private Set<Role> roles ;
}
