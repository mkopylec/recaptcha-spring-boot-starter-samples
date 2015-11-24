package com.github.mkopylec.recaptcha;

import com.github.mkopylec.recaptcha.security.RecaptchaAuthenticationFilter;
import com.github.mkopylec.recaptcha.security.login.CredentialLoginFailuresCountingHandler;
import com.github.mkopylec.recaptcha.security.login.LoginFailuresClearingHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private RecaptchaAuthenticationFilter authenticationFilter;
    @Autowired
    private CredentialLoginFailuresCountingHandler failureHandler;
    @Autowired
    private LoginFailuresClearingHandler successHandler;

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .formLogin().loginPage("/login").failureHandler(failureHandler).successHandler(successHandler)
                .and()
                .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests().antMatchers("/").authenticated();
    }
}
