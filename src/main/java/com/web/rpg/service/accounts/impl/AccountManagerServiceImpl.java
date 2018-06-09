package com.web.rpg.service.accounts.impl;

import com.web.rpg.model.accounts.Account;
import com.web.rpg.repository.AccountManagerRepository;
import com.web.rpg.service.accounts.AccountManagerService;
import com.web.rpg.service.character.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AccountManagerServiceImpl implements AccountManagerService {

    private final AccountManagerRepository accountManagerRepository;
    private final CharacterService characterService;

    @Autowired
    public AccountManagerServiceImpl(AccountManagerRepository accountManagerRepository,
                                     CharacterService characterService) {
        this.accountManagerRepository = accountManagerRepository;
        this.characterService = characterService;
    }

    @Override
    public Account getAccountById(UUID id) {
        return accountManagerRepository.findById(id);
    }

    @Override
    public Account getAccountByLogin(String login) {
        return accountManagerRepository.findByUsername(login);
    }

    @Override
    public List<Account> findAllAccounts() {
        return accountManagerRepository.findAll();
    }

    @Override
    public boolean createAccount(Account account) {
        Account existingAccount = getAccountByLogin(account.getUsername());
        if (existingAccount != null) {
            return false;
        } else {
            account.getUserInfo().setCharacterId(characterService.prepeareCharacter());
            accountManagerRepository.save(account);
            return true;
        }
    }

    @Override
    public boolean verify(Account account) {
        Account actualAccount = getAccountByLogin(account.getUsername());
        if (account != null && account.getUsername().equals(actualAccount.getUsername()) && account.getPassword().equals(actualAccount.getPassword())) {
            return true;
        } else {
            return false;
        }
    }
}
