package com.calypso.finance.stockprofiles.model;




import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "STOCKPROFILE")
public class StockPersistence {
	@Id
	@Column(name = "STOCKID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int stockId;
	@Column(name = "SYMBLE")
	private String symble;
	@Column(name = "PRICE")
	private String price;
	
	@ManyToOne
	@JoinColumn(name = "USERID")
	@JsonIgnore
	private UserPersistence user;

	public UserPersistence getUser() {
		return user;
	}
	public void setUser(UserPersistence user) {
		this.user = user;
	}
	public int getStockId() {
		return stockId;
	}
	public void setStockId(int stockId) {
		this.stockId = stockId;
	}
	public String getSymble() {
		return symble;
	}
	public void setSymble(String symble) {
		this.symble = symble;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	
}
