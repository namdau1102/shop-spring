package com.project.shop.models;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "pets")
@PrimaryKeyJoinColumn(name = "id")
@Getter
@Setter
public class Pet extends Item {
    @Column(name = "age")
    @NotNull
    private Float age;

    @Column(name = "sold")
    private Boolean sold = false;
}
