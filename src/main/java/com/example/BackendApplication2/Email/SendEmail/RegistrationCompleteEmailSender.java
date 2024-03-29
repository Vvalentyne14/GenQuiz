package com.example.BackendApplication2.Email.SendEmail;

import com.example.BackendApplication2.Email.RegistrationComplete;

import com.example.BackendApplication2.Registration.Token.VerificationTokenService;
import com.example.BackendApplication2.User.Users;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.UUID;


@Service
public class RegistrationCompleteEmailSender implements ApplicationListener<RegistrationComplete> {
    private final VerificationTokenService tokenService;
    private final JavaMailSender mailSender; // Injected JavaMailSender bean
    private Users user;


    public RegistrationCompleteEmailSender(VerificationTokenService tokenService, JavaMailSender mailSender) {
        this.tokenService = tokenService;
        this.mailSender = mailSender;
    }

    @Override
    public void onApplicationEvent(RegistrationComplete event) {
        user = event.getUser();
        String vToken = UUID.randomUUID().toString();
        tokenService.saveVerificationForUser(user, vToken);


        String url = "http://localhost:8080/registration/verifyEmail?token="+vToken;

        try {
            sendVerificationEmail(url);
        } catch (MessagingException | UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }



    public void sendVerificationEmail(String url) throws MessagingException, UnsupportedEncodingException {
        String subject = "Email Verification";
        String senderName = "User Verification information";
        String mailContent = "<p> Hi, " + user.getFirstname() + ", </p>" +
                "<p> Thank you for registering with us, please follow the link below to complete your application.</p>" +
                "<a href=\"" + url + "\">Verify your email to activate your account</a>" +  "<br>" + "<h4> Note that the link will expire in the next 10 minutes</h4>" +
                "<p> Thank you <br> Users Registration Message</p> <br> <a href=\""  + "\"> Unsubscribe if you did not request for this email..</a> ";

        emailMessage(subject, senderName, mailContent, mailSender, user);
    }

    public void sendPasswordResetVerificationEmail(String url) throws MessagingException, UnsupportedEncodingException {
        String subject = "Password Reset Verification";
        String senderName = "User Password Reset Verification information";
        String mailContent = "<p> Hi, " + user.getFirstname() + ", </p>" +
                "<p> You recently requested to reset your password</b>" + "" +
                "Please fellow the link below to complete the action.</p>" +
                "<a href=\"" + url + "\">Reset Password</a>" + " <br>" + "<h4> Note that the link will expire in the next 10 minutes</h4>" +
                "<p> Thank you <br> Users Message</p> ";

        emailMessage(subject, senderName, mailContent, mailSender, user);
    }

    private void emailMessage(String subject,
                              String senderName,
                              String mailContent,
                              JavaMailSender mailSender,
                              Users user) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = this.mailSender.createMimeMessage();
        var messageHelper = new MimeMessageHelper(message);
        messageHelper.setFrom("nelsoncode573@gmail.com", senderName);
        messageHelper.setTo(user.getEmail());
        messageHelper.setSubject(subject);
        messageHelper.setText(mailContent, true);

        this.mailSender.send(message);
    }

}
