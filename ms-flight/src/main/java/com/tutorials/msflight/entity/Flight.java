package com.tutorials.msflight.entity;

import com.tutorials.msflight.enums.FlightStatus;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(schema = "public", name = "flight")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @ToString.Include
    @Generated(value = GenerationTime.INSERT)
    @Column(name = "external_id")
    UUID externalId;

    @ToString.Include
    @Column(name = "code")
    String code;

    @ToString.Include
    @Column(name = "arrival_time")
    LocalDateTime arrivalTime;

    @ToString.Include
    @Column(name = "departure_time")
    LocalDateTime departureTime;

    @ToString.Include
    @Column(name = "price")
    BigDecimal price;

    @ToString.Include
    @ManyToOne
    @JoinColumn(name = "arrival_location_id", referencedColumnName = "id")
    Location arrivalLocation;

    @ToString.Include
    @ManyToOne
    @JoinColumn(name = "departure_location_id", referencedColumnName = "id")
    Location departureLocation;

    @ToString.Include
    @Enumerated(value = EnumType.STRING)
    @Column(name = "status")
    FlightStatus status;

    @Column(name = "active")
    Boolean active;

    @PrePersist
    private void setPreValues() {
        if (status == null)
            status = FlightStatus.SCHEDULED;

        if (active == null)
            active = true;
    }

}
