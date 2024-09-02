package com.youdesign.YouDesign;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class controller {
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("pageTitle", "Inicio");
        return "index";
    }
    @GetMapping("/login")
    public String login( ) {
        return "login";
    }
}
