package com.example.Service;

import java.util.List;

import com.example.Dto.AccountDto;

public interface AccountService
{
		AccountDto createAccount(AccountDto account);
		
		AccountDto getAccountById(Long id);
		
		AccountDto deposite(Long id,double amount);
		
		AccountDto withdraw(Long id,double amount);
		
		List<AccountDto> getallAccount();
		
		void deleteAccount(Long id);
		
		
}
