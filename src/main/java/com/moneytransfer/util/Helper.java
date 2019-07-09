package com.moneytransfer.util;

import java.util.Currency;

import com.moneytransfer.beans.Account;
import com.moneytransfer.exception.TransactionException;
import com.moneytransfer.impl.AccountImpl;

public class Helper {
	
	public static boolean validateCurrencyCode(String currencyCode) throws TransactionException{
		try {
            Currency instance = Currency.getInstance(currencyCode);
            return instance.getCurrencyCode().equals(currencyCode);
        } catch (Exception e) {
            throw new TransactionException("Validation Failed: Invalid currecy code");
        }
	}
	
	public static boolean validatePayeeCurrency(String currencyCode,long toAccntId) throws TransactionException{
		AccountImpl ac = new AccountImpl();
		Account account = ac.getAccountById(toAccntId);
		if(account.getCurrencyCode().equals(currencyCode)){
			return true;
		}
		return false;
	}

}
