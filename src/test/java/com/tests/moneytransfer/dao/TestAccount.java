package com.tests.moneytransfer.dao;


import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import com.moneytransfer.beans.Account;
import com.moneytransfer.exception.TransactionException;
import com.moneytransfer.impl.AccountImpl;
import com.moneytransfer.misc.SampleData;

import java.util.List;

import static junit.framework.TestCase.assertTrue;

public class TestAccount {

	@BeforeClass
	public static void setup() {
		//set up test data
		SampleData data = new SampleData();
		data.loadData();
	}

	@After
	public void tearDown() {

	}

	//TC: Check if all accounts information is retrieved successfully
	@Test
	public void testGetAllAccounts() throws TransactionException {
		AccountImpl acHandler = new AccountImpl();
		List<Account> allAccounts = acHandler.getAllAccounts();
		assertTrue(allAccounts.size() > 1);
	}

	//TC: Check if given account's information is retrieved successfully
	@Test
	public void testGetAccountById() throws TransactionException {
		AccountImpl acHandler = new AccountImpl();
		Account account = acHandler.getAccountById(1L);
		assertTrue(account.getUserName().equals("Harshita"));
	}

}