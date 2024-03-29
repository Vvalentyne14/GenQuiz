package com.example.BackendApplication2.Registration;

import com.example.BackendApplication2.User.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.List;

@Setter
@Getter
public class RegistrationRequest {
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private Collection<Role> roles;

    public RegistrationRequest() {
    }

}
