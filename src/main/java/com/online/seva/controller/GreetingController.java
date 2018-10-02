package com.online.seva.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@ResponseBody
public class GreetingController {

    @GetMapping("/greeting")
    public ResponseEntity<String> greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {
        return ResponseEntity.ok("Hello " + name);
    }
}
