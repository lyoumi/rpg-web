package com.web.rpg.controller;

import com.web.rpg.model.accounts.Account;
import com.web.rpg.service.accounts.AccountManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping(path = EndpointInfo.PROFILE_URL)
public class AccountController {

    private static final String USER_URL = "id";
    private static final String USER_EDIT_URL = "id/edit";

    private final AccountManagerService accountManagerService;

    @Autowired
    public AccountController(AccountManagerService accountManagerService) {
        this.accountManagerService = accountManagerService;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public String getUserProfile(Principal principal, Model model) {
        Account account = accountManagerService.getAccountByLogin(principal.getName());
        model.addAttribute("account", account);
        return "profile";
    }
}
