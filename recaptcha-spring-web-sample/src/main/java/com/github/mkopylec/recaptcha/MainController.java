package com.github.mkopylec.recaptcha;

import com.github.mkopylec.recaptcha.validation.RecaptchaValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class MainController {

    @Autowired
    private RecaptchaValidator recaptchaValidator;

    @RequestMapping("/")
    public String showIndexView() {
        return "index";
    }

    @RequestMapping(value = "/", method = POST)
    public String validateCaptcha(HttpServletRequest request) {
        if (recaptchaValidator.validate(request).isSuccess()) {
            return "redirect:/?recaptchaSuccess";
        }
        return "redirect:/?recaptchaError";
    }
}
