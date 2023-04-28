package com.example.easydonatemaster.repositories;

import com.example.easydonatemaster.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepositoy extends JpaRepository<User, Integer> {
}
