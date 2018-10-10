package com.online.seva.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(Exception.class)
    public String handleException(Model model, Exception e) {
        model.addAttribute("message", e.getMessage());
        return "app_error";
    }
}

