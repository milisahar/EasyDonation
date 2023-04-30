package com.example.easydonatemaster.repositories;

import com.example.easydonatemaster.entites.EventComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventCommentRepositoy extends JpaRepository<EventComment,Integer> {

}
