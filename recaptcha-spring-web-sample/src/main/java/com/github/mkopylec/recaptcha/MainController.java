package com.github.mkopylec.recaptcha;

import javax.servlet.http.HttpServletRequest;

import com.github.mkopylec.recaptcha.validation.RecaptchaValidator;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {

    private final RecaptchaValidator recaptchaValidator;

    public MainController(RecaptchaValidator recaptchaValidator) {
        this.recaptchaValidator = recaptchaValidator;
    }

    @GetMapping("/")
    public String showIndexView() {
        return "index";
    }

    @PostMapping("/")
    public String validateCaptcha(HttpServletRequest request) {
        if (recaptchaValidator.validate(request).isSuccess()) {
            return "redirect:/?recaptchaSuccess";
        }
        return "redirect:/?recaptchaError";
    }
}
