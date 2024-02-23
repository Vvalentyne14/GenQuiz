package com.example.BackendApplication2.Registration;

import com.example.BackendApplication2.User.Role;

import java.util.Collection;
import java.util.List;

public class RegistrationRequest {
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private Collection<Role> roles;

    public RegistrationRequest() {
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }
}
