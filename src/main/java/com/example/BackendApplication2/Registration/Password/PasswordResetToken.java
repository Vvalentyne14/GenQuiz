package com.example.BackendApplication2.Registration.Password;

import com.example.BackendApplication2.Registration.Token.VerificationTokenExpirationTime;
import com.example.BackendApplication2.User.Users;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@Entity
public class PasswordResetToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String token;
    private Date expirationTime;
    @OneToOne
    @JoinColumn(name = "user_id")
    private Users user;

    public PasswordResetToken() {
    }

    public PasswordResetToken(String token, Users user) {
        this.token = token;
        this.expirationTime = VerificationTokenExpirationTime.getExpirationTime();
        this.user = user;
    }

}
