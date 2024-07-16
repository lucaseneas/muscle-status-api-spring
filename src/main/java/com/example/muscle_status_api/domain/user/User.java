package com.example.muscle_status_api.domain.user;

import com.example.muscle_status_api.domain.workout.Workout;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Table(name="users")
@Entity(name="users")
@EqualsAndHashCode(of = "id")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String email;
    private String password;
    private String created_at = ZonedDateTime.now(ZoneId.of("GMT")).toString();
    private UserRole role;


    public User (RequestUser requestUser) {
        ZonedDateTime gmtTime = ZonedDateTime.now(ZoneId.of("GMT"));

        this.name = requestUser.name();
        this.email = requestUser.email();
        this.password = requestUser.password();
        this.created_at = gmtTime.toString();
    }

    public User(String name, String email, String encryptedPassword, UserRole role) {
        this.name = name;
        this.email = email;
        this.password = encryptedPassword;
        this.role = role;

    }

    //Essa classe que ira capturas as roles
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //Se for admin ele tera as permissões de admin e de user
        if(this.role == UserRole.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));

        //E todos os outros serão users normais
        else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    //Indica a variavel que vai ser utilizada para login
    @Override
    public String getUsername() {
        return email;
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
