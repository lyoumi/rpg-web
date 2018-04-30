package com.web.rpg.dao;

import com.web.rpg.entity.AccountEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface AccountManagerDao extends CrudRepository<AccountEntity, UUID> {
    AccountEntity findAccountEntityByLogin(String login);
}
