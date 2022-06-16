package com.project.shop.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private UserTypes userType;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    public Role(UserTypes type) {
        this.userType = type;
    }

    public static enum UserTypes {
        ROLE_ADMIN,
        ROLE_USER
    }
}
