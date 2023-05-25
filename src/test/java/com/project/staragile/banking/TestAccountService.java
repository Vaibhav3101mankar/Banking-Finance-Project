package com.project.staragile.banking;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class TestAccountService {

	@Autowired
	AccountService accountService;

	@MockBean
	AccountRepository accountRepository;

	
	
	@Test
	public void testAccountRegistraiton() {
		Account account = new Account(1010101010,"Vaibhav","Saving Account",20000.0);
		assertEquals(account.getAccountNumber(),accountService.registerDummyAccount().getAccountNumber());
		assertEquals(account.getAccountName(),accountService.registerDummyAccount().getAccountName());
	}
	@Test
	void testSearchAccount() {
		AccountService pService = new AccountService();
		assertEquals(null,pService.searchPolicy());
	} 
	 
	 @Test
		void testdeleteAccount() {
		 Account account = new Account(1010101010,"Vaibhav","Saving Account",20000.0);
		 accountRepository.delete(account);
			Account account1 = null;
	        Optional<Account> optionalP = accountRepository.findById(1);
	        if(optionalP.isPresent()){
	        	account1 = optionalP.get();
	        }
	        assertThat(account1).isNull();
		}	 
}

