package com.tests.moneytransfer.api;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.Assert.assertTrue;

public class TestAccountService extends TestService {

	//TC: Test our main API; Check if all accounts information is retrieved
    @Test
    public void testGetAllAccounts() throws IOException, URISyntaxException {
        URI uri = builder.setPath("/account/getAll").build();
        HttpGet request = new HttpGet(uri);
        HttpResponse response = client.execute(request);
        int statusCode = response.getStatusLine().getStatusCode();
        assertTrue(statusCode == 200);
    }
    
  //TC: Test fund transfer API with sufficient funds
    @Test
    public void testTransactionEnoughFund() throws IOException, URISyntaxException {
        URI uri = builder.setPath("/account/transfer").build();
        String jsonInString = "{\"payeeCurrency\":\"USD\",\"amount\":10.00,\"fromAccountId\":1,\"toAccountId\":2}";
        StringEntity entity = new StringEntity(jsonInString);
        HttpPut request = new HttpPut(uri);
        request.setHeader("Content-type", "application/json");
        request.setEntity(entity);
        HttpResponse response = client.execute(request);
        int statusCode = response.getStatusLine().getStatusCode();
        assertTrue(statusCode == 200);
    }

   //Negative TC: Test fund transfer API with insufficient funds
    @Test
    public void testTransactionInsuffucientFunds() throws IOException, URISyntaxException {
        URI uri = builder.setPath("/account/transfer").build();
        String jsonInString = "{\"payeeCurrency\":\"USD\",\"amount\":10000.00,\"fromAccountId\":1,\"toAccountId\":2}";
        StringEntity entity = new StringEntity(jsonInString);
        HttpPut request = new HttpPut(uri);
        request.setHeader("Content-type", "application/json");
        request.setEntity(entity);
        HttpResponse response = client.execute(request);
        HttpEntity entityRes = response.getEntity();
        String responseString = EntityUtils.toString(entityRes, "UTF-8");
        assertTrue(responseString.equals("No sufficient funds available for transfer"));
    }

}
