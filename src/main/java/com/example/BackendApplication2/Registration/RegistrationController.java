package com.example.BackendApplication2.Registration;


import com.example.BackendApplication2.Email.RegistrationComplete;
import com.example.BackendApplication2.Email.SendEmail.RegistrationCompleteEmailSender;
import com.example.BackendApplication2.Registration.Password.IPasswordResetTokenService;
import com.example.BackendApplication2.Registration.Token.VerificationToken;
import com.example.BackendApplication2.Registration.Token.VerificationTokenService;
import com.example.BackendApplication2.User.IUserService;
import com.example.BackendApplication2.User.Users;
import com.example.BackendApplication2.Utility.UrlUtility;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.io.UnsupportedEncodingException;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/registration")

public class RegistrationController {

     private final ApplicationEventPublisher publisher;
     private final HttpServletRequest request;
     private final VerificationTokenService tokenService;
     private final IPasswordResetTokenService passwordResetTokenService;
     private final RegistrationCompleteEmailSender emailSender;
     private final IUserService userService;



    public RegistrationController(ApplicationEventPublisher publisher, HttpServletRequest request, VerificationTokenService tokenService, IPasswordResetTokenService passwordResetTokenService, RegistrationCompleteEmailSender emailSender, IUserService userService) {

        this.publisher = publisher;
        this.request = request;
        this.tokenService = tokenService;
        this.passwordResetTokenService = passwordResetTokenService;
        this.emailSender = emailSender;
        this.userService = userService;

    }


    @GetMapping("/registration-form")
    public String showRegistrationForm(Model model) {
       model.addAttribute("Users",
               new RegistrationRequest());
       return "registration";
    }


    @PostMapping("/register")
    public String registerUser(@ModelAttribute("Users")
                                   RegistrationRequest registration,
                               RedirectAttributes redirectAttributes) {


        try {
            Users users = userService.registerUser(registration);
            publisher.publishEvent(new RegistrationComplete(users,
                    UrlUtility.getApplicationUrl(request)));
            redirectAttributes.addAttribute("success", true);
            return "redirect:/registration/registration-form?success";
        } catch (Exception e) {
            redirectAttributes.addAttribute("error", true);
            return "redirect:/registration/registration-form?error";
        }

    }


    @GetMapping("/verifyEmail")
    public String verifyEmail(@RequestParam("token") String token) {
        Optional<VerificationToken> theToken = tokenService.findByToken(token);

        if (theToken.isPresent() && theToken.get().getUser().isEnabled()) {
            return "redirect:/login?verified";
        }
        String verificationResult = tokenService.validateToken(token);

        switch (verificationResult.toLowerCase()) {
            case "expired":
                return "redirect:/error?expired";
            case "valid":
                return "redirect:/login?valid";
            default:
                return "redirect:/error?invalid";
        }

    }

    @GetMapping("/forget-password-request")
    public String forgetPassword() {
         return "forget-password-form";
    }

    @PostMapping("/forget-password")
    public String resetPassword(HttpServletRequest request, RedirectAttributes redirectAttributes) {
         String email = request.getParameter("email");
         Optional<Users> user = userService.findByEmail(email);

        try {
            if (user.isEmpty()) {
                redirectAttributes.addAttribute("not_found",true);
                return "redirect:/registration/forget-password-request?not_found";
            }


            String passwordReset = UUID.randomUUID().toString();
            passwordResetTokenService.createPasswordResetForUser(user.get(), passwordReset);
            // send password reset verification email to user

            String url = "http://localhost:8080/registration/reset-password-form?token=" + passwordReset;

            try {
                emailSender.sendPasswordResetVerificationEmail(url);
            } catch (MessagingException | UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }

            return "redirect:/registration/forget-password-request?success";
        } catch (RuntimeException e) {
            redirectAttributes.addAttribute("error",true);
            return "redirect:/registration/forget-password-request?error";
        }
    }

    @GetMapping("/reset-password-form")
    public String passwordReset(@RequestParam("token") String token, Model model) {
         model.addAttribute("token", token);
         return "password-reset-form";
    }

    @PostMapping("/reset-password")
    public String resetPassword(HttpServletRequest request) {
         String theToken = request.getParameter("token");
         String password = request.getParameter("password");
         String tokenVerificationResult = passwordResetTokenService.validatePasswordResetToken(theToken);

         if (!tokenVerificationResult.equalsIgnoreCase("valid")) {
             return "redirect:/error?invalid_token";
         }
         Optional<Users> theUser = passwordResetTokenService.findUserByPasswordResetToken(theToken);
         if (theUser.isPresent()) {
             passwordResetTokenService.resetPassword(theUser.get(), password);
             return "redirect:/login?reset_success";
         }
         return "redirect:/error?not_found";
    }

}
