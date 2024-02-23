package com.example.BackendApplication2.Email;

import com.example.BackendApplication2.User.Users;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;



@Setter
@Getter
public class RegistrationComplete extends ApplicationEvent {
    private Users user;
    private String confirmationUrl;
    public RegistrationComplete(Users user, String confirmationUrl) {
        super(user);
        this.user = user;
        this.confirmationUrl = confirmationUrl;
    }

}
