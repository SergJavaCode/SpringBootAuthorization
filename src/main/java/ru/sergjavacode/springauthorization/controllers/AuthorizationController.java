package ru.sergjavacode.springauthorization.controllers;

import org.springframework.web.bind.annotation.*;
import ru.sergjavacode.springauthorization.model.Authorities;
import ru.sergjavacode.springauthorization.service.AuthorizationService;

import java.util.List;

@RestController
public class AuthorizationController {
    AuthorizationService service;

    public AuthorizationController(AuthorizationService service) {
        this.service = service;
    }

    @GetMapping("/authorize")
    public List<Authorities> getAuthorities(@RequestParam("user") String user, @RequestParam("password") String password) {
        return service.getAuthorities(user, password);
    }

    @PostMapping
    @ResponseBody
    public List<Authorities> setAuthorities(@RequestParam("user") String user, @RequestParam("password") String password) {
        return service.getAuthorities(user, password);
    }
}