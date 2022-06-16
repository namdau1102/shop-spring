package com.project.shop.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "feeds")
@Data
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "id")
public class Feed extends Item {
    @Column(name = "grams")
    @NotNull
    @Min(1)
    @Max(1000000)
    private Float gram;
}
