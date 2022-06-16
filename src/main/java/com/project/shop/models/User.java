package com.project.shop.models;

import com.project.shop.utils.validators.AvailableUsername;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @AvailableUsername
    @Size(min = 4, max = 30)
    @Column(name = "username")
    private String username;

    @Column(name = "password")
    @Size(min = 5)
    private String password;
        
    @Transient
    private String passwordConfirm;

    @Email
    @Column(name = "email")
    private String email;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id")
    private Address address;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @Column(name = "expired")
    private Boolean expired = false;

    @Column(name = "locked")
    private Boolean locked = false;

    @Column(name = "credentials")
    private Boolean credentials = false;

    @Column(name = "enabled")
    private Boolean enabled = false;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(e -> new SimpleGrantedAuthority(e.getUserType().name()))
                .collect(Collectors.toSet());
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !expired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !credentials;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @AssertTrue
    private boolean isPasswordsEquals() {
        return password == null
                || passwordConfirm == null
                || password.equals(passwordConfirm);
    }
}
