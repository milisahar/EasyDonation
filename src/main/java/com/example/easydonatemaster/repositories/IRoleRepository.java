package com.example.easydonatemaster.repositories;

import com.example.easydonatemaster.entites.ERole;
import com.example.easydonatemaster.entites.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IRoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(ERole name);
}
