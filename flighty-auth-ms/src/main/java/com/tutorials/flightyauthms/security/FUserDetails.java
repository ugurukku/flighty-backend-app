package com.tutorials.flightyauthms.security;

import com.tutorials.flightyauthms.model.RoleAuthModel;
import java.util.Collection;
import java.util.Set;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Getter
public class FUserDetails implements UserDetails {
    private final long id;
    private final String username;
    private final String password;
    private final Collection<? extends GrantedAuthority> roles;

    public FUserDetails(long id, String username, String password, Set<RoleAuthModel> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.roles = roles.stream().map(r -> (GrantedAuthority) r::getName).toList();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
