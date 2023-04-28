package com.example.easydonatemaster.repositories;

import com.example.easydonatemaster.entites.FundDonation;
import com.example.easydonatemaster.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FundDonationRepositoy extends JpaRepository<FundDonation , Integer> {
    @Query("SELECT f.userRef, SUM(f.sum) AS total FROM FundDonation f GROUP BY f.userRef ORDER BY total DESC")
    List<Object[]> findTopThreeDonators();


}
