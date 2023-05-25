package com.project.staragile.banking;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class AccountController {
	
	@Autowired
	AccountService accountService;
	
	@Autowired
	ObjectMapper objectMapper;
	
	@GetMapping("/sayHello")
	public String sayHello() {
		return "Hello from Central Bank";
	}
	
	
	@GetMapping("/createAccount")
	public Account createAccount(){
		return accountService.createAccount();
	}
	
	@PostMapping("/registerAccount")
	public Account registerAccount(@RequestBody Account account) {
		if(account != null) {
			return accountService.registerAccount(account);
		}
		System.out.println("post called");
		return account;
	}
	
	@GetMapping("/getAccount/{accountNumber}")
	public Account getAccountDetails(@PathVariable(value="accountNumber") int accountNumber) {
		System.out.println(accountNumber);
		Account account = accountService.getAccountDetails(accountNumber);
		return account;
	}
	
	@PutMapping("/updatePolicy/{accountNumber}")
	public Account updatePolicy(@PathVariable(value="accountNumber")int accountNumber, @RequestBody Account accountDetails) {
		return accountService.updateAccount(accountNumber, accountDetails);
	}
	
	@GetMapping("/viewPolicy/{accountNumber}")
	public List<Account> viewPolicy(@PathVariable(value="accountNumber")int accountNumber) {
		return accountService.viewAccount(accountNumber);
	}
	
	@DeleteMapping("/deletePolicy/{accountNumber}")
	public void  deletePolicy(@PathVariable(value="accountNumber")int accountNumber) {
		accountService.deleteAccount(accountNumber);
	}	

}
