package com.youdesign.YouDesign;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class controller {
    @GetMapping("/index")
    public String MostrarInicio(){
        return "index";
    }
    @GetMapping("/login")
    public String MostrarLogin(){
        return "login";
    }
}
