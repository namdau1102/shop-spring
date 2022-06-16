package com.project.shop.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "accessories")
@Data
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "id")
public class Accessory extends Item {
    @Column(name = "brand")
    @NotBlank
    @Size(max = 250)
    private String brand;
}
