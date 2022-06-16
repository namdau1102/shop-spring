package com.project.shop.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.persistence.ManyToMany;
import java.util.Set;


@Entity
@Table(name = "animal_categories")
@Data
@NoArgsConstructor
public class Type {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "category")
    private String category;

    public Type(String category) {
        this.category = category;
    }

}
