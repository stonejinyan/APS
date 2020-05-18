package com.aps.controller;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestAPS {
    @RequestMapping("/")
    String home() {
        return "Hello APS!";
    }
}
