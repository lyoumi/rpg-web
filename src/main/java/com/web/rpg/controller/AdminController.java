package com.web.rpg.controller;

import com.web.rpg.model.accounts.Account;
import com.web.rpg.service.accounts.AccountManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = EndpointInfo.ADMIN_URL)
public class AdminController {

    private final AccountManagerService accountManagerService;

    @Autowired
    public AdminController(AccountManagerService accountManagerService) {
        this.accountManagerService = accountManagerService;
    }

    @GetMapping
    public String getAllAccounts(Model model) {
        model.addAttribute("accounts", accountManagerService.getAllAccounts());
        return "admin-main";
    }

    @GetMapping(path = "{username}")
    public String getAccountPage(@PathVariable("username") String username,
                                 Model model) {
        model.addAttribute("account", accountManagerService.getAccountByLogin(username));
        return "admin-account-manage";
    }

    @PostMapping(path = "{username}")
    @ResponseBody
    public ResponseEntity saveChanges(Account account) {
        accountManagerService.save(account);
        return new ResponseEntity(HttpStatus.OK);
    }
}
