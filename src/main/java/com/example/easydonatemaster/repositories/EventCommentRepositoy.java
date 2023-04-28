package com.example.easydonatemaster.repositories;

import com.example.easydonatemaster.entites.EventComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventCommentRepositoy extends JpaRepository<EventComment,Integer> {
}
