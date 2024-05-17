package com.example.Serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Dto.AccountDto;
import com.example.Entity.Account;
import com.example.Repository.AccountRepository;
import com.example.Service.AccountService;
import com.example.mapper.AccountMapper;

@Service
public class AccountServiceimpl implements AccountService
{
	@Autowired
	private AccountRepository accountRepository;

	@Override
	public AccountDto createAccount(AccountDto accountDto) {
		Account account = AccountMapper.mapToAccount(accountDto);
		Account save = accountRepository.save(account);
		return AccountMapper.maptoAccountDto(save);
	}

	@Override
	public AccountDto getAccountById(Long id)
	{
		Account account = accountRepository
				.findById(id)
				.orElseThrow(()-> new RuntimeException("Account does not exits"));
		
		return AccountMapper.maptoAccountDto(account);
	}

	@Override
	public AccountDto deposite(Long id, double amount) {
		Account account = accountRepository
				.findById(id)
				.orElseThrow(()-> new RuntimeException("Account does not exits"));
		double total=account.getBalance() + amount;
		account.setBalance(total);
		Account saveAccount = accountRepository.save(account);
		return AccountMapper.maptoAccountDto(saveAccount);
	}

	@Override
	public AccountDto withdraw(Long id, double amount)
	{
		Account account = accountRepository
				.findById(id)
				.orElseThrow(()-> new RuntimeException("Account does not exits"));
		if(account.getBalance()<amount)
		{
			throw new RuntimeException("insufficient Balance..!!");
		}
		else
		{
		double total=account.getBalance()-amount;
		account.setBalance(total);
		Account saveaccount = accountRepository.save(account);
		
		return AccountMapper.maptoAccountDto(saveaccount);
		}
	}

	@Override
	public List<AccountDto> getallAccount() {
		List<Account> accounts = accountRepository.findAll();
		return accounts.stream().map((account)-> AccountMapper.maptoAccountDto(account))
		.collect(Collectors.toList());
		
	}

	@Override
	public void deleteAccount(Long id)
	{
		Account account = accountRepository
				.findById(id)
				.orElseThrow(()-> new RuntimeException("Account does not exits"));
		accountRepository.deleteById(id);
	}

}
