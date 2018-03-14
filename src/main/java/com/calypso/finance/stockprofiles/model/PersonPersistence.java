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
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "PERSON")

public class PersonPersistence {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "PERSONID")
	private int personId;
	
	@Column(name = "PERSONNAME")
	private String personname;
	
	@Column(name = "PERSONAGE")
	private int personage;
	
	@Column(name = "PERSONCITY")
	private String personcity;
	
	@OneToMany(mappedBy = "person", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<UserPersistence> users = new HashSet<UserPersistence>();
	
	

	public Set<UserPersistence> getUsers() {
		return users;
	}
	public void setUsers(Set<UserPersistence> users) {
		this.users = users;
	}
	public int getPersonId() {
		return personId;
	}
	public void setPersonId(int personId) {
		this.personId = personId;
	}
	public String getPersonname() {
		return personname;
	}
	public void setPersonname(String personname) {
		this.personname = personname;
	}
	public int getPersonage() {
		return personage;
	}
	public void setPersonage(int personage) {
		this.personage = personage;
	}
	public String getPersoncity() {
		return personcity;
	}
	public void setPersoncity(String personcity) {
		this.personcity = personcity;
	}
	
}
