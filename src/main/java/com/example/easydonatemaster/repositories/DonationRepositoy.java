package com.example.easydonatemaster.repositories;

import com.example.easydonatemaster.entites.Donation;
import com.example.easydonatemaster.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;

@Repository
public interface DonationRepositoy extends JpaRepository <Donation , Integer> {
    @Temporal(TemporalType.DATE)
    List<Donation> findAllByDonationDate(Date donationDate);
    List<Donation> findByUserRefOrderBySum(User user);
    @Query("SELECT f.userRef, SUM(f.sum) AS total FROM Donation f GROUP BY f.userRef ORDER BY total DESC")
    List<Object[]> findTopThreeDonators();
}
