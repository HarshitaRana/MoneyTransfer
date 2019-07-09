package com.moneytransfer.api;

import java.math.BigDecimal;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.moneytransfer.beans.Account;
import com.moneytransfer.exception.TransactionException;
import com.moneytransfer.impl.AccountImpl;

/*
 * This class serves as our API class to services provided
 * All account related operations like fetching all accounts, fetching account
 * by Id, create, delete, transfer, withdraw etc will flow through this Api
 * */

@Path("/account")
@Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
public class AccountApi{
	
	/**
     * Fetch all accounts
     * @return list of accounts
	 * @throws TransactionException 
     */
	@GET
	@Path("/getAll")
	public List<Account> getAllAccounts() throws TransactionException {
		AccountImpl account = new AccountImpl();
		return account.getAllAccounts();
	}

	/**
     * Fetch account by id
     * @param accountId
     * @return Account
	 * @throws TransactionException 
     */
	@GET
    @Path("/{accountId}")
    public Account getAccount(@PathParam("accountId") long accountId) throws TransactionException {
		AccountImpl account = new AccountImpl();
        return account.getAccountById(accountId);
    }
	
	/**
     * Transfer funds between accounts
     * @param Json String
     */
	@PUT
	@Path("/transfer")
	@Consumes(MediaType.APPLICATION_JSON)
	public String transferAccountBalance(String details) {
		try {
			TransactionApi transaction = new TransactionApi();
			JSONObject jsonDet = new JSONObject(details);
			return transaction.transfer(jsonDet);
		} catch (TransactionException e) {
			log.error(e);
			return e.getMessage();
		}
	}
	
	//deposit amount to an account
	@PUT
    @Path("/{accountId}/deposit/{amount}")
    public Account deposit(@PathParam("accountId") long accountId,@PathParam("amount") BigDecimal amount) throws TransactionException {
		// Logic to deposit amount to given bank account
		// Idea will be to invoke TransactionApi which will serve this transaction
		// Currently not implemented as we are focusing on Transfer feature
		return null;
	}

    /**
     * Withdraw amount by account Id
     * @param accountId
     * @param amount
     * @return
     * @throws TransactionException
     */
    @PUT
    @Path("/{accountId}/withdraw/{amount}")
    public Account withdraw(@PathParam("accountId") long accountId,@PathParam("amount") BigDecimal amount) throws TransactionException {
    	// Logic to withdraw amount from given bank account
    	// Idea will be to invoke TransactionApi which will serve this transaction
    	// Currently not implemented as we are focusing on Transfer feature
    	return null;
    }
    
    
    @POST
    @Path("/create")
	public long createAccount(Account account) {
		//Logic to create a new account
		//Currently not implemented as we are focusing on transfer feature
		return 0;
	}

    @DELETE
    @Path("/delete")
	public int deleteAccountById(long accountId) {
		//Logic to create a delete/close account
		//Currently not implemented as we are focusing on transfer feature
		return 0;
	}

    private static Logger log = Logger.getLogger(AccountApi.class);
}
