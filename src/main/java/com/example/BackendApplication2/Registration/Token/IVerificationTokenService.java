package com.example.BackendApplication2.Registration.Token;

import com.example.BackendApplication2.User.Users;

import java.util.Optional;

public interface IVerificationTokenService {
    String validateToken(String token);
    void saveVerificationForUser(Users user, String token);
    Optional<VerificationToken> findByToken(String token);
}
