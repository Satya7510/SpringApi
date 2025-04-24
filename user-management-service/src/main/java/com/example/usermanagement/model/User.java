package com.example.usermanagement.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Indexed
public class User {
    @Id
    private Long id;

    @NotBlank
    @FullTextField
    private String firstName;

    @NotBlank
    @FullTextField
    private String lastName;

    @NotBlank
    @Email
    @Column(unique = true)
    private String email;

    @NotBlank
    @FullTextField
    private String ssn;

    private String phone;
    private String address;
    private LocalDate birthDate;
    private String gender;
    private String image;

    @Column(length = 1000)
    private String description;
} 