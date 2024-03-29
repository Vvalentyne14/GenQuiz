package com.example.BackendApplication2.User;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final IUserService userService;
    @GetMapping()
    public String getUsers(Model model) {
        model.addAttribute("Users", userService.getAllUsers());
        return "users";
    }

    @GetMapping("edit/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model){
        Optional<Users> user = userService.findById(id);
        user.ifPresent(users -> model.addAttribute("user", users));
            return "update-user";
    }

    @PostMapping("update/{id}")
    public String updateUser(@PathVariable("id") Long id,Model model, Users user) {
        userService.updateUser(id, user.getFirstname(), user.getLastname(), user.getEmail());
        model.addAttribute("user", user);
        return "redirect:/users?update_success";
    }

    @GetMapping("delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/users?delete_success";
    }
}
