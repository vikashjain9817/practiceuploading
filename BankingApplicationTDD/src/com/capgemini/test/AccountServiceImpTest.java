package com.capgemini.test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.capgemini.beans.Account;
import com.capgemini.exception.InsufficientBalanceException;
import com.capgemini.exception.InsufficientOpeningBalanceException;
import com.capgemini.exception.InvalidAccountNumberException;
import com.capgemini.repo.AccountRepo;
import com.capgemini.service.AccountServiceImp;

public class AccountServiceImpTest {
	
	AccountServiceImp account;
	
	@Mock
	AccountRepo accountRepository;
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		account = new AccountServiceImp(accountRepository);
	}
	
	@Test(expected = com.capgemini.exception.InsufficientOpeningBalanceException.class)
	public void TestPassWhenTheAmountIsLessThan500Rupees() throws InsufficientOpeningBalanceException{
		account.createAccount(2028, 400);
	}
	
	
	@Test(expected = com.capgemini.exception.InvalidAccountNumberException.class)
	public void whenTheValidInfoNotPassedThenThrowAnError() throws InvalidAccountNumberException
	{
		System.out.println("invalid account number testing");
		account.deposit(101, 5000);
		
	}
	
	@Test
	public void whenTheValidinfoPassedThenCreateAccountSuccessfully() throws InsufficientOpeningBalanceException
	{
		account.createAccount(101, 500);
	}
	
	@Test
	public void whenTheValidInfoIsPassedAmountShouldBeDepositedSuccessfully() throws InvalidAccountNumberException
	{
		Account account1 = new Account(101,5000);
		when(accountRepository.searchAccount(101)).thenReturn(account1);
		assertEquals(account1.getAmount()+500, account.deposit(101, 500));
	}
	
	@Test
	public void whenTheValidInfoIsPassedAmountShouldBeWithDrawSuccessfully() throws InvalidAccountNumberException, InsufficientBalanceException
	{
		Account account1 = new Account(101, 5000);
		when(accountRepository.searchAccount(101)).thenReturn(account1);
		account.withdrawAmount(101, 4000);
		
	}
	
}