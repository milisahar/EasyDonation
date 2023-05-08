package com.example.easydonatemaster.services;

import com.example.easydonatemaster.entites.Reclamation;
import com.example.easydonatemaster.repositories.ReclamationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReclamationService {
    @Autowired
    private ReclamationRepository reclamationRepository;

    public Reclamation createReclamation(Reclamation reclamation) {
        return reclamationRepository.save(reclamation);
    }

    public List<Reclamation> getAllReclamations() {
        return reclamationRepository.findAll();
    }

    public Reclamation getReclamationById(Long id) {
        Optional<Reclamation> optionalReclamation = reclamationRepository.findById(id);
        return optionalReclamation.orElse(null);
    }



    public boolean deleteReclamation(Long id) {
        Optional<Reclamation> optionalReclamation = reclamationRepository.findById(id);
        if (optionalReclamation.isPresent()) {
            reclamationRepository.delete(optionalReclamation.get());
            return true;
        } else {
            return false;
        }
    }
}

