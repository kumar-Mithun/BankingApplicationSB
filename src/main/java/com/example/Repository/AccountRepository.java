package com.example.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long>
{

}
