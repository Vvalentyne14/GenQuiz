package com.example.BackendApplication2.User;

import com.example.BackendApplication2.Registration.RegistrationRequest;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    List<Users> getAllUsers();
    Users registerUser(RegistrationRequest request);
    Optional<Users> findByEmail(String email);
}
