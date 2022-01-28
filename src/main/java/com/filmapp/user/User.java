package com.filmapp.user;

import com.filmapp.role.user.UserRole;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;

    @Column(unique = true)
    @NotBlank
    @Email
    @Size(max = 30)
    private String email;

    @Column
    @NotBlank
    @Size(min = 6)
    private String password;

    @OneToOne
    @JoinColumn(name = "user_role_id", referencedColumnName = "id")
    private UserRole role;

    public User(String username, String email, String password, UserRole role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }
}
