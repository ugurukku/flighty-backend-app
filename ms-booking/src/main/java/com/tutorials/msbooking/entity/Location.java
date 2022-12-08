package com.tutorials.msbooking.entity;

import java.util.List;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
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
@Table(schema = "public", name = "location")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @Generated(value = GenerationTime.INSERT)
    @Column(name = "external_id")
    UUID externalId;

    @Column(name = "city")
    String city;

    @Column(name = "airport")
    String airport;

    @ToString.Exclude
    @OneToMany(mappedBy = "arrivalLocation")
    List<Flight> arrivalFlights;

    @ToString.Exclude
    @OneToMany(mappedBy = "departureLocation")
    List<Flight> departureFlights;
}
