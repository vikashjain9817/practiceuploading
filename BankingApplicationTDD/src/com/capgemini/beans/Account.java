package com.capgemini.beans;

public class Account {
	private int accountNumber;
	private int amount;
	public Account(int accountNumber, int amount) {
		this.accountNumber = accountNumber;
		this.amount = amount;
	}
	public int getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	
	
}
