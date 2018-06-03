package com.web.rpg.converters;

import com.web.rpg.entity.AccountEntity;
import com.web.rpg.model.accounts.Account;
import com.web.rpg.model.accounts.UserInfo;
import org.springframework.stereotype.Component;

@Component
public class AccountConverter extends BaseConverter {

    public AccountEntity convertToEntity(Account account) {
        if (account == null) {
            return null;
        } else {
            return new AccountEntity(
                    account.getId(),
                    account.getLogin(),
                    account.getRole(),
                    serializeObjectToByteArray(account.getPassword()),
                    serializeObjectToByteArray(account.getUserInfo())
            );
        }
    }

    public Account convertFromEntity(AccountEntity accountEntity) {
        if (accountEntity == null) {
            return null;
        } else {
            return new Account(
                    accountEntity.getId(),
                    accountEntity.getLogin(),
                    accountEntity.getRole(),
                    (String) deserializeObjectFromByte(accountEntity.getPassword()),
                    (UserInfo) deserializeObjectFromByte(accountEntity.getUserInfo())
            );
        }
    }
}
