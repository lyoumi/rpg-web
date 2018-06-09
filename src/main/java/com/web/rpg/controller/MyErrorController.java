package com.web.rpg.controller;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyErrorController implements ErrorController {

    private static final String PATH = "/error";

    @GetMapping(path = PATH)
    public String error() {
        return "error";
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }
}
