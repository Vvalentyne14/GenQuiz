package com.example.BackendApplication2.Registration.Token;


import com.example.BackendApplication2.User.Users;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@Entity
public class VerificationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String token;
    private Date expirationTime;
    @OneToOne
    @JoinColumn(name = "user_id")
    private Users user;

    public VerificationToken() {
    }

    public VerificationToken(String token, Users user) {
        this.token = token;
        this.user = user;
        this.expirationTime = VerificationTokenExpirationTime.getExpirationTime();

    }


}
