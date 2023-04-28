package com.example.easydonatemaster.repositories;

import com.example.easydonatemaster.entites.Donation;
import com.example.easydonatemaster.entites.Fundraiser;
import com.example.easydonatemaster.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;

@Repository
public interface FundraiserRepositoy extends JpaRepository<Fundraiser , Integer> {
    List<Fundraiser> findAllByArchived(boolean archived);
    @Temporal(TemporalType.DATE)
    List<Fundraiser> findAllByDeadline(Date deadline);
@Query("SELECT f FROM Fundraiser f WHERE f.progressStatus = (SELECT MAX(f2.progressStatus) FROM Fundraiser f2)")
    public Fundraiser BestFundraiser();
    @Query("SELECT f FROM Fundraiser f WHERE f.deadline < CURRENT_DATE AND f.target> f.progressStatus")
    public List<Fundraiser> listFundraisersExipredNotSucceded();
    List<Fundraiser> findByArchivedFalse();
}
