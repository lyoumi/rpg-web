package com.web.rpg.service.accounts;

import com.web.rpg.model.accounts.Account;

import java.util.List;
import java.util.UUID;

public interface AccountManagerService {
    Account getAccountById(UUID id);

    Account getAccountByLogin(String login);

    List<Account> findAllAccounts();
}
