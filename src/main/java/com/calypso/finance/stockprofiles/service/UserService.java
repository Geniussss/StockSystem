package com.calypso.finance.stockprofiles.service;




import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.calypso.finance.stockprofiles.model.PersonPersistence;
import com.calypso.finance.stockprofiles.model.StockPersistence;
import com.calypso.finance.stockprofiles.model.UserPersistence;
import com.calypso.finance.stockprofiles.repository.PersonRepository;
import com.calypso.finance.stockprofiles.repository.StockRepository;
import com.calypso.finance.stockprofiles.repository.UserRepository;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;



@Service
public class UserService {
	Logger log = LoggerFactory.getLogger(UserService.class);
	@Autowired
	private PersonRepository person;
	
	public PersonPersistence createPerson(PersonPersistence personobj) {
		person.save(personobj);
		return personobj;	
	}
	/*
	public StockPersistence createStockInfo(StockPersistence stock) {
		stock = YahooFinance.get
		return stock;
		
	}
	*/
	@Autowired
	private StockRepository stock;
	
	public StockPersistence createStock(StockPersistence stockobj) throws Exception {
		Stock sto = new Stock(null);
		 sto = YahooFinance.get(stockobj.getSymble().toString());
		String price = sto.getQuote().getOpen().toString();
		stockobj.setPrice(price);
		stockobj.setUser(stockobj.getUser());
		stock.save(stockobj);
		return stockobj;
	}
	
	public StockPersistence getStockPrice(StockPersistence stockobj) throws Exception {
		Stock sto = new Stock(null);
		 sto = YahooFinance.get(stockobj.getSymble().toUpperCase());
		String price = sto.getQuote().getPrice().toPlainString();
		stockobj.setPrice(price);
		return stockobj;
		
	}
	
	
	public String getYahooStock(String name) throws IOException {
		Stock sto = YahooFinance.get(name);
		String price = sto.getQuote().getOpen().toString();
		return price;
	}
	
	@Autowired
	private UserRepository user;
	
	public UserPersistence findByName(String username) {
		return user.findByUsername(username);
	}
	
	public UserPersistence createUser(UserPersistence userobj) {
		user.save(userobj);
		return userobj;
	}
	
}
