package com.example.BackendApplication2.User;

import jakarta.mail.internet.MimeMessage;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;


import java.io.InputStream;
import java.util.Collection;
import java.util.List;



@Entity
@Table(name = "\"Customer Users\"")
public class Users implements JavaMailSender {

    @Setter
    @Getter
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    @Setter
    @Getter
    private String firstname;
    @Setter
    @Getter
    private String lastname;
    @Setter
    @Getter
    private String email;
    @Setter
    @Getter
    private String password;
    private boolean isEnabled = false;
    @Setter
    @Getter
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user roles",
            joinColumns = @JoinColumn(name = "user id",
                    referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role id"))
    private Collection<Role> roles;

    public Users(String firstname, String lastname, String email, List<Role> roles) {
    }

    public Users(String firstname, String lastname, String email, String password, List<Role> roles) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public Users() {

    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", isEnabled=" + isEnabled +
                ", roles=" + roles +
                '}';
    }


    @Override
    public MimeMessage createMimeMessage() {
        return null;
    }

    @Override
    public MimeMessage createMimeMessage(InputStream contentStream) throws MailException {
        return null;
    }

    @Override
    public void send(MimeMessage... mimeMessages) throws MailException {

    }

    @Override
    public void send(SimpleMailMessage... simpleMessages) throws MailException {

    }

    public void setEnabled(boolean b) {
      this.isEnabled = b;
    }

    public boolean isEnabled() {
        return isEnabled;
    }
}
