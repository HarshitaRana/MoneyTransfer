package com.moneytransfer.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;
import org.apache.log4j.Logger;

import com.moneytransfer.beans.Account;
import com.moneytransfer.dao.AccountDao;
import com.moneytransfer.exception.TransactionException;
import com.moneytransfer.util.Constants;
import com.moneytransfer.util.DBConnection;

public class AccountImpl implements AccountDao{

	//Gets DB connection and fetches all accounts from H2 DB
	@Override
	public List<Account> getAllAccounts() throws TransactionException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Account> allAccounts = new ArrayList<Account>();
		DBConnection db = new DBConnection();
		try {
			conn = db.getConnection();
			stmt = conn.prepareStatement(Constants.SQL_GET_ALL_ACC);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Account account = new Account(rs.getLong("AccountId"), rs.getString("UserName"),
						rs.getBigDecimal("Balance"), rs.getString("Currency"));
		
				allAccounts.add(account);
			}
		} catch (SQLException e) {
			String error="AccountImp: Error reading all accounts information";
			log.error(error);
			throw new TransactionException(error);			
		} finally {
			DbUtils.closeQuietly(conn, stmt, rs);
		}
		return allAccounts;
	}

	//Gets DB connection and fetches given account's information from H2 DB
	@Override
	public Account getAccountById(long accountId) throws TransactionException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Account account = null;
		DBConnection db = new DBConnection();
		try {
			conn = db.getConnection();
			stmt = conn.prepareStatement(Constants.SQL_GET_ACC_BY_ID);
			stmt.setLong(1, accountId);
			rs = stmt.executeQuery();
			if (rs.next()) {
				account = new Account(rs.getLong("AccountId"), rs.getString("UserName"), rs.getBigDecimal("Balance"),
						rs.getString("Currency"));
			}
		
		} catch (SQLException e) {
			String error="AccountImp: Error reading given account's information";
			log.error(error);
			throw new TransactionException(error);
			
		} finally {
			DbUtils.closeQuietly(conn, stmt, rs);
		}
		return account;
	}
	
	@Override
	public long createAccount(Account account) {
	
		return 0;
	}

	@Override
	public int deleteAccountById(long accountId) {
		
		return 0;
	}

	
	private static Logger log = Logger.getLogger(AccountImpl.class);


	
}
