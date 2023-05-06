package com.example.easydonatemaster.repositories;

import com.example.easydonatemaster.entites.Donation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DonationRepositoy extends JpaRepository <Donation , Integer> {
}
