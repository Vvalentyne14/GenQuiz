package com.example.BackendApplication2.Security;

import com.example.BackendApplication2.User.UserRepository;
import com.example.BackendApplication2.User.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class SecurityUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
     @Autowired
     public SecurityUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
     }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Users> userOptional = userRepository.findByEmail(email);

        return userOptional
                .map(users -> new SecurityUserDetails(users))  // Convert to SecurityUserDetails using map
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
    }

}
