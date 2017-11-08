package com.github.mkopylec.recaptcha;

import com.github.mkopylec.recaptcha.security.login.FormLoginConfigurerEnhancer;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final FormLoginConfigurerEnhancer enhancer;

    public SecurityConfiguration(FormLoginConfigurerEnhancer enhancer) {
        this.enhancer = enhancer;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        enhancer.addRecaptchaSupport(http.formLogin()).loginPage("/login")
                .and()
                .csrf().disable()
                .authorizeRequests().antMatchers("/").authenticated();
    }
}
