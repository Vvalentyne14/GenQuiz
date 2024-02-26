package com.example.BackendApplication2.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByEmail(String email);

    boolean existsByEmail(String email);

    @Modifying
    @Query(value = "UPDATE Users u set u.firstname = :firstname," + "u.lastname =:lastname, u.email =:email where u.id =:id")
    void update(String firstname, String lastname, String email,Long id);
}
