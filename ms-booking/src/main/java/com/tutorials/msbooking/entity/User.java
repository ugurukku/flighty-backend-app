package com.tutorials.msbooking.entity;

import com.tutorials.msbooking.enums.UserStatus;
import java.util.List;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "app_user")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Generated(value = GenerationTime.INSERT)
    @Column(name = "external_id")
    UUID externalId;

    @Column(name = "email")
    String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    UserStatus status;

    @Column(name = "full_name")
    String fullName;

    @ToString.Exclude
    @OneToMany(mappedBy = "user")
    List<Booking> bookings;
}
