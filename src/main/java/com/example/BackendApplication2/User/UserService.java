package com.example.BackendApplication2.User;

import com.example.BackendApplication2.Registration.RegistrationRequest;
import com.example.BackendApplication2.Registration.Token.VerificationTokenService;
import com.example.BackendApplication2.Security.EmailAlreadyExistsException;
import jakarta.transaction.Transactional;
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
    private final VerificationTokenService verificationTokenService;

    @Autowired
    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder, VerificationTokenService verificationTokenService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.verificationTokenService = verificationTokenService;
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

    @Override
    public Optional<Users> findById(Long id) {
        return userRepository.findById(id);
    }

    @Transactional
    @Override
    public void updateUser(Long id, String firstname, String lastname, String email) {
        userRepository.update(firstname,lastname,email,id);

    }

    @Transactional
    @Override
    public void deleteUser(Long id) {
        Optional<Users> theUser = userRepository.findById(id);
        theUser.ifPresent(user -> verificationTokenService.deleteUserToken(user.getId()));
        userRepository.deleteById(id);
    }
}
