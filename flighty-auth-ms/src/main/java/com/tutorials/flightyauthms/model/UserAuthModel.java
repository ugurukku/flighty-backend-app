package com.tutorials.flightyauthms.model;

import com.tutorials.flightyauthms.entity.User;
import java.util.HashSet;
import java.util.Set;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserAuthModel {

    long id;
    String username;
    String password;
    Set<RoleAuthModel> roles = new HashSet<>();

    public UserAuthModel(User user) {
        this.id = user.getId();
        this.username = user.getEmail();
        this.password = user.getPassword();
        user.getRoles().forEach(role -> roles.add(new RoleAuthModel(role)));
    }
}
