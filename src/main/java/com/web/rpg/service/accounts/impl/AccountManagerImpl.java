package com.web.rpg.service.accounts.impl;

import com.web.rpg.model.accounts.Account;
import com.web.rpg.repository.AccountManagerRepository;
import com.web.rpg.service.accounts.AccountManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AccountManagerImpl implements AccountManagerService {

    private final AccountManagerRepository accountManagerRepository;

    @Autowired
    public AccountManagerImpl(AccountManagerRepository accountManagerRepository) {
        this.accountManagerRepository = accountManagerRepository;
    }

    @Override
    public Account getAccountById(UUID id) {
        return accountManagerRepository.findById(id);
    }

    @Override
    public Account getAccountByLogin(String login) {
        return accountManagerRepository.findByLogin(login);
    }

    @Override
    public List<Account> findAllAccounts() {
        return accountManagerRepository.findAll();
    }
}
