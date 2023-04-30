package com.example.easydonatemaster.repositories;

import com.example.easydonatemaster.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface IUserRepository extends JpaRepository<User, Integer> {

    @Query("SELECT u FROM User u WHERE (:search IS NULL OR u.firstName LIKE %:search% "
            + " OR u.lastName LIKE %:search% OR u.cinUser LIKE %:search% OR u.phoneNumber LIKE %:search% )")
    List<User> rechercheDynamique(@Param("search") String search);


    User findByEmail(String email);
    User findByCode(String code);
    Optional<User> findByUsername(String username);

    List<User> findByStatusIsNullAndCreatedAtIsBefore( LocalDateTime b);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
    User findUserByUsername(String username);

    <T> Optional<T> findById(Long Id);
}
