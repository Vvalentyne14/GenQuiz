package com.example.BackendApplication2.OauthUserDetails;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OauthUserRepository extends JpaRepository<OAuth2User, Long> {

    Optional<OAuth2User> findByEmail(String email);
}
