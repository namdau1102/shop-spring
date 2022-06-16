package com.project.shop.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Data
@Table(name = "items")
public abstract class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    @NotBlank
    private String name;

    @Column(name = "description")
    @NotBlank
    @Size(max = 250)
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "type_id", nullable = false)
    private Type type;

    @Column(name = "price")
    @NotNull
    @Min(1)
    @Max(1000000)
    private Float price;

    @Column(name = "amount")
    @NotNull
    @Min(0)
    @Max(10000)
    private Integer amount;

    @Column(name = "images")
    @ElementCollection
    @CollectionTable(name = "Images", joinColumns = @JoinColumn(name = "item_id"))
    private List<String> images;

    @Column(name = "deleted")
    private Boolean deleted;
}
