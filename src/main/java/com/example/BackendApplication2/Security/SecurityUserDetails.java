package com.example.BackendApplication2.Security;

import com.example.BackendApplication2.User.Users;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class SecurityUserDetails implements UserDetails {
    @Setter
    private String username;
    @Setter
    private String password;
    private boolean isEnabled;
    @Setter
    private List<GrantedAuthority> authorities;

    public SecurityUserDetails(String username, String password, boolean isEnabled, List<GrantedAuthority> authorities) {
        this.username = username;
        this.password = password;
        this.isEnabled = isEnabled;
        this.authorities = authorities;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    @Override
    public String toString() {
        return "SecurityUserDetails{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", isEnabled=" + isEnabled +
                ", authorities=" + authorities +
                '}';
    }

    public SecurityUserDetails(Users users) {
        this.username = users.getEmail();
        this.password = users.getPassword();
        this.isEnabled = users.isEnabled();
        this.authorities = Arrays.stream(users.getRoles().toString().split(","))
                .map(SimpleGrantedAuthority::new)
        .collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}
