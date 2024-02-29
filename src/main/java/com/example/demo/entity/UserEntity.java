package com.example.demo.entity;

import com.example.demo.enums.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@Table()
@Entity(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class UserEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "login", nullable = false, unique = true)
    @NotEmpty
    private  String login;
    @Column(name = "password", nullable = false)
    @Size(min = 8, message = "Min size of password is 8")
    @NotEmpty(message = "Password cannot be empty")
    private String password;
    @Enumerated(EnumType.STRING)
    private UserRole role;
}
