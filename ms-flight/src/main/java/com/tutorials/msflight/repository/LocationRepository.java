package com.tutorials.msflight.repository;

import com.tutorials.msflight.entity.Location;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
    Optional<Location> findByExternalId(UUID locationId);
}
