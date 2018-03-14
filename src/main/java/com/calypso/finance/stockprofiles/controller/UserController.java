package com.calypso.finance.stockprofiles.controller;


import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.calypso.finance.stockprofiles.model.PersonPersistence;
import com.calypso.finance.stockprofiles.model.StockPersistence;
import com.calypso.finance.stockprofiles.model.UserPersistence;
import com.calypso.finance.stockprofiles.service.UserService;


import yahoofinance.YahooFinance;

@RestController
@RequestMapping("/stock")
public class UserController {
	Logger log = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private UserService userService;
	@RequestMapping(value = "/person", method = RequestMethod.POST)
	public PersonPersistence createPerson( PersonPersistence personobj) throws Exception {
		Set<UserPersistence> users = new HashSet<UserPersistence>();
		Set<StockPersistence> stocks = new HashSet<StockPersistence>();
		for (UserPersistence user : personobj.getUsers()) {
			user.setPerson(personobj);
			users.add(user);
			userService.createUser(user);
			for(StockPersistence stock : user.getStocks()) {
				//stock.setPrice(YahooFinance.get(stock.getSymble()).getQuote().getPrice().toString());
				//String price = YahooFinance.get(stock.getSymble()).getQuote().getPrice().toString();
				//stock.setPrice(price);
				stock.setUser(user);
				stock.setPrice(YahooFinance.get(stock.getSymble()).getQuote().getOpen().toString());
				stocks.add(stock);
				user.setStocks(stocks);	
			}
			personobj.setUsers(users);
		}
		return userService.createPerson(personobj);
	}
	@RequestMapping(value = "/price", method = RequestMethod.POST)
	public StockPersistence getPrice(@RequestBody StockPersistence stockobj) throws Exception {
		return userService.getStockPrice(stockobj);
	}
	
	
	@RequestMapping(value = "/item",method = RequestMethod.POST)
	public StockPersistence createStocks(@RequestBody StockPersistence stockobj) throws Exception {
		return userService.createStock(stockobj);
	}
	
	@RequestMapping(value = "/v1")
	public String getYahooStock(@RequestParam(value = "name") String name) throws Exception {
		return userService.getYahooStock(name);
		
	}
	
	@RequestMapping(value = "/p")
	public ModelAndView createPersonAndUser(PersonPersistence personobj,UserPersistence userobj) {
		userService.createPerson(personobj);
		userobj.setPerson(personobj);
		userService.createUser(userobj);
		return new ModelAndView("/login");
	}
	
	
	@RequestMapping(value = "/v",method = RequestMethod.POST)
	public UserPersistence createUsers(UserPersistence user) {
		return userService.createUser(user);
	}
	
	@RequestMapping(value = "/login",method = RequestMethod.GET)
	public ModelAndView login(ModelAndView model) {
			return model;
	}
	@RequestMapping(value = "/registration",method = RequestMethod.GET)
	public Model registration(Model model,PersonPersistence personobj) {
		model.addAttribute("Username",personobj.getUsers());
		return model;
	}
	
	
	@RequestMapping(value = "/stock",method = RequestMethod.GET)
	public ModelAndView loginStock(ModelAndView model,UserPersistence userobj) throws Exception {
		if (userobj.getUsername()!=null&&userService.findByName(userobj.getUsername())!=null) {
			if (userService.findByName(userobj.getUsername()).getPassword().equals(userobj.getPassword())) {
				return model;
			}
		}
		if (userobj.getUsername()==null) {
			return new ModelAndView("/login");
		}
		return new ModelAndView("/registration");
	}


	@RequestMapping(value = "/s",method = RequestMethod.POST)
	public String createUserStock(StockPersistence stockobj,String username) throws Exception {
		UserPersistence userobj = new UserPersistence();
		stockobj.setUser(userService.findByName(username));
		userService.createStock(stockobj);
		return "Your Stock profile has been updated "+ stockobj.getSymble()+" "+"price is "
					+userService.getYahooStock(stockobj.getSymble());	
	}	
	
}


