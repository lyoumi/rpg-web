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



@Controller
@Scope("session")
public class LoginController {

    private final AccountManagerService userService;

    @Autowired
    public LoginController(AccountManagerService userService) {
        this.userService = userService;
    }

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
    public String signUpHandling(Account user, @RequestParam(name = "confirmationPassword") String confirmationPassword, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "sign-up-page";
        }else {
            if (user.getPassword().equals(confirmationPassword)){
                user.setUsername(user.getUsername().replaceAll(" ", ""));
                user.setPassword(user.getPassword().replaceAll(" ", ""));
                if(userService.createAccount(user)) return "redirect:/login";
            }
            return "redirect:/signup";
        }

    }
}