package com.encore.dao;

import java.util.ArrayList;

import com.encore.vo.Stock;
import com.encore.vo.Trade;
import com.encore.vo.User;

public interface BrokerDAOTemplete {

	void addUser(User user);

	void deleteUser(int ssn);

	void updateUser(User user);

	void buyStock(int ssn, Stock stock);

	void sellStock(int ssn, Stock stock);

	ArrayList<User> findAllUser();

	User findUser(int ssn);

	ArrayList<Stock> findAllStock();

	ArrayList<Stock> findStockByPrice();

	Stock findStock();

	ArrayList<Trade> findHavingStock(int ssn);

	ArrayList<Trade> findStockOwner(int stockNum);

}
