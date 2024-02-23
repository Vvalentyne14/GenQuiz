package com.example.BackendApplication2.Registration.Password;

import com.example.BackendApplication2.User.Users;

import java.util.Optional;

public interface IPasswordResetTokenService {
    String validatePasswordResetToken(String token);

    Optional<Users> findUserByPasswordResetToken(String token);

    void resetPassword(Users theUser, String password);

    void createPasswordResetForUser(Users user, String passwordReset);
}
