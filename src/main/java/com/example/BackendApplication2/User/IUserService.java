package com.example.BackendApplication2.User;

import com.example.BackendApplication2.Registration.RegistrationRequest;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    List<Users> getAllUsers();
    Users registerUser(RegistrationRequest registrationRequest);
    Optional<Users> findByEmail(String email);

    Optional<Users> findById(Long id);

    void updateUser(Long id, String firstname, String lastname, String email);

    void deleteUser(Long id);
}
