package com.tutorials.msuser.repository;

import com.tutorials.msuser.entity.Role;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    default Optional<Role> findByName(String name) {
        return findByNameIgnoreCase(name);
    }

    Optional<Role> findByNameIgnoreCase(String name);
}
