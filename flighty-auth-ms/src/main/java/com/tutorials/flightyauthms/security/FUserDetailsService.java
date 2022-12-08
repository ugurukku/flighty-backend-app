package com.tutorials.flightyauthms.security;

import com.tutorials.flightyauthms.model.UserAuthModel;
import com.tutorials.flightyauthms.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Configuration
@AllArgsConstructor
@Log4j2
public class FUserDetailsService implements UserDetailsService {
    private final UserService userService;

    public static UserDetails map(UserAuthModel userAuthModel) {
        return new FUserDetails(userAuthModel.getId(), userAuthModel.getUsername(), userAuthModel.getPassword(),
                userAuthModel.getRoles());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userService.findAuthModelByUsername(username)
                .map(FUserDetailsService::map)
                .orElseThrow(() -> {
                    String message = String.format("User %s is not found in the database", username);
                    log.warn(message);
                    return new UsernameNotFoundException(message);
                });

    }

}
