package com.tutorials.flightyauthms.model;

import com.tutorials.flightyauthms.entity.Role;
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
public class RoleAuthModel {

    long id;
    String name;

    public RoleAuthModel(Role role) {
        this.id = role.getId();
        this.name = role.getName();
    }
}
