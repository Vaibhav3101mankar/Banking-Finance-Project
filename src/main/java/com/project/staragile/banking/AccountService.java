package com.project.staragile.banking;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
	
	@Autowired
	AccountRepository accountRepository;
	
	public Account createAccount() {
		Account account = new Account(1010101010,"Vaibhav","Saving Account",20000.0);
		return accountRepository.save(account);
	}

	public Account registerAccount(Account account) {
		return accountRepository.save(account);
	}

	public Account getAccountDetails(int accountId) {
		return accountRepository.findById(accountId).get();
	}
	
	public Account registerDummyAccount() {
		Account account = new Account(1010101010,"Vaibhav","Saving Account",20000.0);
		return account;
	}

	public Account updateAccount(int accountNumber, Account accountDetails) {
		// TODO Auto-generated method stub
		Account account = accountRepository.findById(accountNumber).get();
		account.setAccountNumber(accountDetails.getAccountNumber());
		account.setAccountName(accountDetails.getAccountName());
		account.setAccountType(accountDetails.getAccountType());
		account.setAccountBalance(accountDetails.getAccountBalance());
		
		return accountRepository.save(account);
	}

	public List<Account> viewAccount(int accountNumber) {
		// TODO Auto-generated method stub
		return accountRepository.findAll();
	}

	public void deleteAccount(int accountNumber) {
		// TODO Auto-generated method stub
		accountRepository.deleteById(accountNumber);
	}


	public Short searchPolicy() {
		// TODO Auto-generated method stub
		return null;
	}
}
