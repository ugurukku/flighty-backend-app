package com.tutorials.msflight.config;


import static com.tutorials.msflight.util.Constants.ADMIN;
import static com.tutorials.msflight.util.Constants.FLIGHTS_URL;
import static com.tutorials.msflight.util.Constants.PATH_ID;

import com.tutorials.msflight.security.AuthorizationFilter;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final AuthorizationFilter authorizationFilter;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, FLIGHTS_URL).hasRole(ADMIN)
                .antMatchers(HttpMethod.PUT, FLIGHTS_URL + PATH_ID).hasRole(ADMIN)
                .antMatchers(HttpMethod.GET, FLIGHTS_URL).authenticated()
                .antMatchers(HttpMethod.GET, FLIGHTS_URL + PATH_ID).authenticated()
                .antMatchers(HttpMethod.DELETE, FLIGHTS_URL + PATH_ID).hasRole(ADMIN)
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(authorizationFilter, UsernamePasswordAuthenticationFilter.class);
    }

}
