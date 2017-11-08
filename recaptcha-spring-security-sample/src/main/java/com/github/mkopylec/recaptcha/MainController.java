package com.github.mkopylec.recaptcha;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String showIndexView() {
        return "index";
    }

    @GetMapping("/login")
    public String showLoginView() {
        return "login";
    }
}
