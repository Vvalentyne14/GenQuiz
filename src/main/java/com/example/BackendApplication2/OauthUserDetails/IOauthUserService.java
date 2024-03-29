package com.example.BackendApplication2.OauthUserDetails;

import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;

import java.util.Optional;

public interface IOauthUserService {
    OAuth2User saveUserDetails(String userName, String email);

    Optional<OAuth2User> findByEmail(String email);;

}
