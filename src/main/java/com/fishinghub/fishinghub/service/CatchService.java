package com.fishinghub.fishinghub.service;
import com.fishinghub.fishinghub.entity.Catch;
import com.fishinghub.fishinghub.repository.CatchRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatchService {

    @Autowired
    private CatchRepository catchRepository;

    public Catch logCatch(Catch catchRecord) {
        return catchRepository.save(catchRecord);
    }

    public List<Catch> getAllCatches() {
        return catchRepository.findAll();
    }

    public Catch getCatchById(Long id) {
        return catchRepository.findById(id).orElse(null);
    }

    public Catch updateCatch(Long id, Catch newCatch) {
        return catchRepository.findById(id)
                .map(catchRecord -> {
                    catchRecord.setFishSpecies(newCatch.getFishSpecies());
                    catchRecord.setLocation(newCatch.getLocation());
                    catchRecord.setQuantity(newCatch.getQuantity());
                    return catchRepository.save(catchRecord);
                }).orElseGet(() -> {
                    newCatch.setId(id);
                    return catchRepository.save(newCatch);
                });
    }

    public void deleteCatch(Long id) {
        catchRepository.deleteById(id);
    }
}