package com.calypso.finance.stockprofiles.model;


import java.util.HashSet;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "USER")

public class UserPersistence {
	@Id
	@Column(name = "USERID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int userId;
	@Column(name = "USERNAME")
	//@NotEmpty(message = "*Please provide a username")
	private String username;
	@Column(name = "PASSWORD")
	//@NotEmpty(message = "*Please provide a password")
	private String password;
	
	
	@ManyToOne
	@JoinColumn(name = "PERSONID")
	@JsonIgnore
	private PersonPersistence person;// Foreign Key
	
	
	
	public PersonPersistence getPerson() {
		return person;
	}
	public void setPerson(PersonPersistence person) {
		this.person = person;
	}
	
	
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<StockPersistence> stocks = new HashSet<StockPersistence>(); 
	
	
	public Set<StockPersistence> getStocks() {
		return stocks;
	}
	public void setStocks(Set<StockPersistence> stocks) {
		this.stocks = stocks;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
