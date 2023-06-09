package kz.beka.shopapp.models;

import jakarta.persistence.*;
import kz.beka.shopapp.enums.Role;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

@Entity
@Table(name = "users")
@Data
public class User implements UserDetails {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "name")
    private String name;
    @Column(name = "active")
    private boolean active;
    @OneToOne(cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    @JoinColumn(name = "image_id")
    private Image avatar;
    @Column(name = "password", length = 1000)
    private String password;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable( name ="user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role>roles = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,  mappedBy = "user")
    private List<Product> products = new ArrayList<>();
    private LocalDateTime dateOfCreated;
    private void init(){
        dateOfCreated = LocalDateTime.now();
    }

    public boolean isAdmin(){
        return roles.contains(Role.ROLE_ADMIN);
    }

    //security

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

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
        return active;
    }
}
