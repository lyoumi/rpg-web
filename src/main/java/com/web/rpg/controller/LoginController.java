package com.web.rpg.controller;

import com.web.rpg.model.accounts.Account;
import com.web.rpg.service.accounts.AccountManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@Scope("session")
public class LoginController {

    @Autowired
    private AccountManagerService accountManagerService;

    @GetMapping(path = "login")
    public String loadLogin(Model model) {
        model.addAttribute("userData", new Account());
        return "sign-in-page";
    }

    @GetMapping(value = "signup")
    public String loadSighUpPage(Model model){
        model.addAttribute("user", new Account());
        return "sign-up-page";
    }

    @PostMapping(path = "signup")
    public String signUpHandling(@Valid Account account,
                                 @RequestParam(name = "confirmationPassword") String confirmationPassword,
                                 BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "signup";
        }else {
            if (account.getPassword().equals(confirmationPassword)){
                account.setLogin(account.getLogin().replaceAll(" ", ""));
                account.setPassword(account.getPassword().replaceAll(" ", ""));
                if(accountManagerService.createAccount(account)) return "redirect:/login";
            }
            return "redirect:/signup";
        }

    }
}
