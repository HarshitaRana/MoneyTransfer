package com.moneytransfer.api;

import java.math.BigDecimal;

import javax.ws.rs.PathParam;

import org.json.JSONObject;

import com.moneytransfer.beans.Account;
import com.moneytransfer.exception.TransactionException;
import com.moneytransfer.impl.TransactionImpl;

/*
 * Takes care of Transactions like
 * fund transfer, deposit,withdraw
 * */
public class TransactionApi {

	public String transfer(JSONObject details) throws TransactionException {
		TransactionImpl transact = new TransactionImpl();
		double amnt = (Double) details.get("amount");
		final BigDecimal amount=BigDecimal.valueOf(amnt);
		final long from= (Integer) details.get("fromAccountId");
		final long to= (Integer) details.get("toAccountId");
		final String currency= (String) details.get("payeeCurrency");
		
		return transact.transferAccountBalance(from,to,amount,currency);
	}
	
	
	
	 public Account deposit(long accountId, BigDecimal amount) throws TransactionException {
			// Logic to deposit amount to given bank account
			// Currently not implemented as we are focusing on Transfer feature
			return null;
		}
	
	 public Account withdraw(long accountId, BigDecimal amount) throws TransactionException {
	    	// Logic to withdraw amount from given bank account
	    	// Currently not implemented as we are focusing on Transfer feature
	    	return null;
	    }

}
