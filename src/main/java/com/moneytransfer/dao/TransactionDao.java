package com.moneytransfer.dao;

import java.math.BigDecimal;

import com.moneytransfer.exception.TransactionException;

public interface TransactionDao {

	String transferAccountBalance(long fromAccntId, long toAccountId, BigDecimal amnt, String currencyCode) throws TransactionException;
	int withdraw(int amount, long accountId) throws TransactionException;
	int deposit(int amount, long accountId) throws TransactionException;
	
}
