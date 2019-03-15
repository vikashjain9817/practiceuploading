package com.capgemini.service;

import org.junit.Before;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.capgemini.beans.Account;
import com.capgemini.exception.InsufficientBalanceException;
import com.capgemini.exception.InsufficientOpeningBalanceException;
import com.capgemini.exception.InvalidAccountNumberException;
import com.capgemini.repo.AccountRepo;

public class AccountServiceImp {
	
	
	
	AccountRepo accountRepository;    // this is for calling the repository save method.
	
	
	public AccountServiceImp(AccountRepo accountRepository) {     
		super();
		this.accountRepository = accountRepository;     // assign the reference of accountRepository into accountRepository object of interface.
	}
	
	
	public Account createAccount(int accountNumber, int amount) throws InsufficientOpeningBalanceException  // to create the new account.
	{
		if(amount < 500) {
			throw new InsufficientOpeningBalanceException();   // throw checked exception if opening balance is not sufficient.
		}
		Account account = new Account(accountNumber, amount);
		
		if(accountRepository.save(account))
		{
			return account;   //return the reference of account object.
		}
		
		return null;
	}
	
	public String fundtransfer(int accountNumberFrom , int amountOfTransferingAccount , int accountNumberTo, int amountOfTransferredAccount, int amountToBeTransferred ) throws InsufficientBalanceException  {

		Account accountFrom = new Account(accountNumberFrom, amountOfTransferingAccount);
		Account accountTo = new Account(accountNumberTo, amountOfTransferredAccount);
		if(accountFrom.getAmount() < amountToBeTransferred)
		{
			throw new InsufficientBalanceException();
		}
		accountFrom.setAmount(accountFrom.getAmount()-amountToBeTransferred);
		accountTo.setAmount(accountTo.getAmount()+amountToBeTransferred);
		return "The amount of Transferring Account is : " + accountFrom.getAmount() + " ," + " The amount of transferred amount is : " + accountTo.getAmount();
	}
	
	
	public int deposit(int accountNumber, int amount) throws InvalidAccountNumberException
	{
		
		Account account = accountRepository.searchAccount(accountNumber);
		if(account == null)
		{
			System.out.println("account is not find");
			throw new InvalidAccountNumberException();
		}
		account.setAmount(account.getAmount()+amount);
		accountRepository.save(account);
		System.out.println("account deposited successfully");
		return account.getAmount();
		
	}
	
	public int withdrawAmount(int accountNumber, int amount) throws InvalidAccountNumberException, InsufficientBalanceException
	{
		
		Account account = accountRepository.searchAccount(accountNumber);
		if(account == null)
		{
			System.out.println("account not found");
			throw new InvalidAccountNumberException();
		}
		if(account.getAmount() < amount)
		{
			System.out.println("insufficient balance");
			throw new InsufficientBalanceException();
		}
		
		System.out.println("withdraw successfully");
		account.setAmount(account.getAmount()-amount);
		accountRepository.save(account);
		return account.getAmount();
		
	}
	
}