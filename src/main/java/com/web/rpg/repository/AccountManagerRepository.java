package com.web.rpg.repository;

import com.web.rpg.converters.AccountConverter;
import com.web.rpg.dao.AccountManagerDao;
import com.web.rpg.entity.AccountEntity;
import com.web.rpg.model.accounts.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class AccountManagerRepository {

    private final AccountManagerDao accountManagerDao;
    private final AccountConverter converter;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AccountManagerRepository(AccountManagerDao accountManagerDao, AccountConverter converter, PasswordEncoder passwordEncoder) {
        this.accountManagerDao = accountManagerDao;
        this.converter = converter;
        this.passwordEncoder = passwordEncoder;
    }

    public Account save(Account account) {
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        account.setRole(account.getUsername().equalsIgnoreCase("admin") ? "ROLE_ADMIN" : "ROLE_USER");
        return converter.convertFromEntity(accountManagerDao.save(converter.convertToEntity(account)));
    }

    public Account findById(UUID id) {
        return converter.convertFromEntity(accountManagerDao.findOne(id));
    }

    public void delete(UUID id) {
        accountManagerDao.delete(id);
    }

    public void delete(Account account) {
        accountManagerDao.delete(converter.convertToEntity(account));
    }

    public List<Account> findAll() {
        List<AccountEntity> all = (List<AccountEntity>) accountManagerDao.findAll();
        return all
                .stream()
                .map(converter::convertFromEntity)
                .collect(Collectors.toList());
    }

    public Account findByUsername(String username) {
        return converter.convertFromEntity(accountManagerDao.findAccountEntityByUsername(username));
    }
}
