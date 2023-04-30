package com.example.easydonatemaster.repositories;

import com.example.easydonatemaster.entites.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepositoy extends JpaRepository<Task, Integer> {

}
