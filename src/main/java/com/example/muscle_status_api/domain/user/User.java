package com.example.muscle_status_api.domain.user;

import jakarta.persistence.*;
import lombok.*;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

@Table(name="users")
@Entity(name="users")
@EqualsAndHashCode(of = "id")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;
    private String created_at;

    public User (RequestUser requestUser) {
        ZonedDateTime gmtTime = ZonedDateTime.now(ZoneId.of("GMT"));
        System.out.println(gmtTime.toString());

        this.name = requestUser.name();
        this.email = requestUser.email();
        this.password = requestUser.password();
        this.created_at = gmtTime.toString();

    }
}
