package com.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class Security extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.inMemoryAuthentication()
                .withUser("api_tester").password("{noop}test").roles("USER");

    }

    // Secure the endpoins with HTTP Basic authentication
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                //HTTP Basic authentication
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/clearing-cost/**").hasRole("USER")
                .antMatchers(HttpMethod.POST, "/clearing-cost").hasRole("USER")
                .antMatchers(HttpMethod.PUT, "/clearing-cost").hasRole("USER")
                .antMatchers(HttpMethod.DELETE, "/clearing-cost/**").hasRole("USER")
                .antMatchers(HttpMethod.POST, "/payment-cards-cost").hasRole("USER")
                .and()
                .csrf().disable()
                .formLogin().disable();
    }

}