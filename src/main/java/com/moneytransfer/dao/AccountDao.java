package com.moneytransfer.dao;

import java.util.List;

import com.moneytransfer.beans.Account;
import com.moneytransfer.exception.TransactionException;

public interface AccountDao {

		List<Account> getAllAccounts() throws TransactionException;
		Account getAccountById(long accountId) throws TransactionException;
	    long createAccount(Account account);
	    int deleteAccountById(long accountId);
}
