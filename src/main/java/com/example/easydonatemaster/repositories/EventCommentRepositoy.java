package com.example.easydonatemaster.repositories;

import com.example.easydonatemaster.entites.EventComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventCommentRepositoy extends JpaRepository<EventComment,Integer> {

}
