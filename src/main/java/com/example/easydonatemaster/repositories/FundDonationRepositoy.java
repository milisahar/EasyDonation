package com.example.easydonatemaster.repositories;

import com.example.easydonatemaster.entites.FundDonation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FundDonationRepositoy extends JpaRepository<FundDonation , Integer> {
}
