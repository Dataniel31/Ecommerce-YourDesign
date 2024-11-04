package com.youdesign.YouDesign.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin/home")
public class HomeAdminController {
    @GetMapping
    public String mostrarhomeAdmin (Model model){
        model.addAttribute("pageTitle", "Home Admin");
        return "admin/adminhome";
    }
}
