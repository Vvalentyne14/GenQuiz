package com.example.BackendApplication2.Security;

public class EmailNotVerifiedException extends RuntimeException {
    public EmailNotVerifiedException(String emailNotVerified) {
        super(emailNotVerified);
    }
}
