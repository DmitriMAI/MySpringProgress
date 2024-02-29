package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table(name = "Person")
public class PersonEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name", nullable = false)
    @NotEmpty(message = "Name cannot be empty")
    @Size(min = 3, max = 30, message = "Name need be 3 to 30 length")
    private String name;
    @NotEmpty(message = "Email cannot be empty")
    @Email
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    public PersonEntity(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
