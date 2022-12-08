package com.tutorials.msbooking.entity;

import com.tutorials.msbooking.enums.FlightStatus;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import javax.persistence.*;
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
@ToString
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(schema = "public", name = "flight")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @Generated(value = GenerationTime.INSERT)
    @Column(name = "external_id")
    UUID externalId;

    @Column(name = "code")
    String code;

    @Column(name = "arrival_time")
    LocalDateTime arrivalTime;

    @Column(name = "departure_time")
    LocalDateTime departureTime;

    @Column(name = "price")
    BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "arrival_location_id", referencedColumnName = "id")
    Location arrivalLocation;

    @ManyToOne
    @JoinColumn(name = "departure_location_id", referencedColumnName = "id")
    Location departureLocation;

    @Column(name = "active")
    Boolean active;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status")
    FlightStatus status;

    @ToString.Exclude
    @OneToMany(mappedBy = "flight")
    List<Booking> bookings;
}
