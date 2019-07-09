package com.moneytransfer.util;

import java.util.HashMap;

public class Constants {

	public final static String SQL_GET_ACC_BY_ID = "SELECT * FROM Account WHERE AccountId = ? ";
	public final static String SQL_LOCK_ACC_BY_ID = "SELECT * FROM Account WHERE AccountId = ? FOR UPDATE";
	public final static String SQL_CREATE_ACC = "INSERT INTO Account (UserName, Balance, CurrencyCode) VALUES (?, ?, ?)";
	public final static String SQL_UPDATE_ACC_BALANCE = "UPDATE Account SET Balance = ? WHERE AccountId = ? ";
	public final static String SQL_GET_ALL_ACC = "SELECT * FROM Account";
	public final static String SQL_DELETE_ACC_BY_ID = "DELETE FROM Account WHERE AccountId = ?";
	public final static HashMap<String,Double> exchange = new HashMap<String,Double>();
	static{
		exchange.put("USD_to_GBP",0.80);
		exchange.put("GBP_to_USD", 1.25);
		exchange.put("INR_to_USD", 0.01);
		exchange.put("USD_to_INR", 68.00);
		exchange.put("EUR_to_GBP", 0.90);
		exchange.put("GBP_to_EUR", 1.12);
		exchange.put("EUR_to_USD", 1.12);
		exchange.put("USD_to_EUR", 0.89);
	}
}
