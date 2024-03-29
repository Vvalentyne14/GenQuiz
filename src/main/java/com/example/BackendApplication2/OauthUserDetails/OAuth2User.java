package com.example.BackendApplication2.OauthUserDetails;


import com.example.BackendApplication2.User.Users;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Map;


@RequiredArgsConstructor
@Entity
@Getter
@Setter
public class OAuth2User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private String email;
    @OneToOne
    @JoinColumn(name = "user_id")
    private Users user;

}
