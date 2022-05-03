package com.project.casualstore.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserLogoutController {

    @PostMapping("/users/logout")
    public String logout() {
        return "redirect:/";
    }
}