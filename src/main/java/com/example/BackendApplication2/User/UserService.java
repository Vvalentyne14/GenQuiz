package com.example.BackendApplication2.User;

import com.example.BackendApplication2.Registration.RegistrationRequest;
import com.example.BackendApplication2.Security.EmailAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Service
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Users registerUser(RegistrationRequest registration) {
        String encoderPassword = passwordEncoder.encode(registration.getPassword());
        List<Role> roles = Arrays.asList(new Role("ROLE USER"));
        Users user = new Users(
                registration.getFirstname(),
                registration.getLastname(),
                registration.getEmail(),
                encoderPassword,
                roles
        );

        // Check if the email already exists
        if (userRepository.existsByEmail(registration.getEmail())) {
            throw new EmailAlreadyExistsException("Email already exists");
        }

        return userRepository.save(user);

    }

    @Override
    public Optional<Users> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
