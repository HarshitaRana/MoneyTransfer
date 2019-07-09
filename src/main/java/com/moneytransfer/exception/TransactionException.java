package com.moneytransfer.exception;

@SuppressWarnings("serial")
public class TransactionException extends Exception{

	String message = "";
	Integer errorCode = 0;
	
	public TransactionException(){
	}
	
	public TransactionException(String message){
		this.message = message;
	}
	
	public TransactionException(String message, Integer code){
		errorCode = code;
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}

		


}
