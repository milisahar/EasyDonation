package com.example.easydonatemaster.repositories;

import com.example.easydonatemaster.entites.Fundraiser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FundraiserRepositoy extends JpaRepository<Fundraiser , Integer> {
}
