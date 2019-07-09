package com.moneytransfer.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.dbutils.DbUtils;
import org.apache.log4j.Logger;

import com.moneytransfer.beans.Account;
import com.moneytransfer.dao.TransactionDao;
import com.moneytransfer.exception.TransactionException;
import com.moneytransfer.util.Constants;
import com.moneytransfer.util.DBConnection;
import com.moneytransfer.util.Helper;

public class TransactionImpl implements TransactionDao {

	@Override
	public synchronized String transferAccountBalance(long fromAccntId, long toAccountId, BigDecimal amnt, String currencyCode) throws TransactionException{
		String msg="";
		//Check if valid currency code is passed by user
		if(Helper.validateCurrencyCode(currencyCode)){
			if(Helper.validatePayeeCurrency(currencyCode, toAccountId)){

				boolean exchangeFlag=false;
				int result = -1;
				Connection conn = null;
				PreparedStatement lockStmt = null;
				PreparedStatement updateStmt = null;
				ResultSet rs = null;
				Account fromAccount = null;
				Account toAccount = null;
				DBConnection db = new DBConnection();

				try {
					conn = db.getConnection();
					conn.setAutoCommit(false);
					
					// Synchronize : lock the account from which amount needs to be transfered
					lockStmt = conn.prepareStatement(Constants.SQL_LOCK_ACC_BY_ID);
					lockStmt.setLong(1, fromAccntId);
					rs = lockStmt.executeQuery();
					if (rs.next()) {
						fromAccount = new Account(rs.getLong("AccountId"), rs.getString("UserName"),
								rs.getBigDecimal("Balance"), rs.getString("Currency"));
					}
					
					// Synchronize : lock the account to which amount needs to be transfered
					lockStmt = conn.prepareStatement(Constants.SQL_LOCK_ACC_BY_ID);
					lockStmt.setLong(1, toAccountId);
					rs = lockStmt.executeQuery();
					if (rs.next()) {
						toAccount = new Account(rs.getLong("AccountId"), rs.getString("UserName"), rs.getBigDecimal("Balance"),
								rs.getString("Currency"));

					}

					// verify the locking status
					if (fromAccount == null || toAccount == null) {
						throw new TransactionException("Fail to lock both accounts for write");
					}

					BigDecimal toAmnt=amnt;
					// check transaction currency
					if (!fromAccount.getCurrencyCode().equals(currencyCode)) {
						//apply exchange rate and covert the amount to be deposited
						toAmnt = applyExchangeRate(amnt,fromAccount.getCurrencyCode(),currencyCode);
						exchangeFlag=true;

					}

					// check enough fund in source account
					BigDecimal fromAccountLeftOver = fromAccount.getBalance().subtract(amnt);
					if (fromAccountLeftOver.compareTo(BigDecimal.ZERO) < 0) {
						throw new TransactionException("No sufficient funds available for transfer");
					}
					// update if everything is good
					updateStmt = conn.prepareStatement(Constants.SQL_UPDATE_ACC_BALANCE);
					updateStmt.setBigDecimal(1, fromAccountLeftOver);
					updateStmt.setLong(2, fromAccntId);
					updateStmt.addBatch();
					updateStmt.setBigDecimal(1, toAccount.getBalance().add(toAmnt));
					updateStmt.setLong(2, toAccountId);
					updateStmt.addBatch();
					int[] rowsUpdated = updateStmt.executeBatch();
					result = rowsUpdated[0] + rowsUpdated[1];

					// commit the transaction
					conn.commit();
				} catch (SQLException se) {
					// rollback transaction if exception occurs
					log.error("TransactionImpl: User Transaction Failed, rollback initiated for: ");
					try {
						if (conn != null)
							conn.rollback();
					} catch (SQLException re) {
						throw new TransactionException("Failed to rollback transaction");
					}
				} finally {
					DbUtils.closeQuietly(conn);
					DbUtils.closeQuietly(rs);
					DbUtils.closeQuietly(lockStmt);
					DbUtils.closeQuietly(updateStmt);
				}

				if(result == 2){
					AccountImpl ac= new AccountImpl();
					msg = "Amount transfered successfully!";
					if(exchangeFlag)
						msg +=" Currency exchange rate applied.";
					msg += "\n"+ac.getAccountById(fromAccntId)+"\n"+ac.getAccountById(toAccountId);	
				}
			}else{
				throw new TransactionException("Transaction failed : Payee's account is not in the currency mentioned.");
			}
		}else{
			throw new TransactionException("Transaction failed : Invalid currency code provided!");
		}
		return msg;
	}

	private BigDecimal applyExchangeRate(BigDecimal amnt, String fromCurr, String toCurr) {
		Double rate = (Double)Constants.exchange.get(fromCurr+"_to_"+toCurr);
		BigDecimal covertedAmnt = amnt.multiply(BigDecimal.valueOf(rate));
		return covertedAmnt;
	}

	@Override
	public int withdraw(int amount, long accountId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deposit(int amount, long accountId) {
		// TODO Auto-generated method stub
		return 0;
	}

	private static Logger log = Logger.getLogger(TransactionImpl.class);
}
