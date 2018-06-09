package com.web.rpg.security;

import com.web.rpg.model.accounts.Account;
import com.web.rpg.repository.AccountManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AccountManagerRepository accountManagerRepository;

    @Autowired
    public UserDetailsServiceImpl(AccountManagerRepository accountManagerRepository) {
        this.accountManagerRepository = accountManagerRepository;
    }

    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Account user = accountManagerRepository.findByUsername(login);

        Set<GrantedAuthority> grantedAuthorities = new HashSet();
        grantedAuthorities.add(new SimpleGrantedAuthority(user.getRole()));

        return new User(user.getUsername(), user.getPassword(), grantedAuthorities);
    }
}