package com.fishinghub.fishinghub.service;
import com.fishinghub.fishinghub.entity.Catch;
import com.fishinghub.fishinghub.repository.CatchRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatchService {

    private final CatchRepository catchRepository;

    @Autowired
    public CatchService(CatchRepository catchRepository) {
        this.catchRepository = catchRepository;
    }

    public Catch logCatch(Catch catchRecord) {
        return catchRepository.save(catchRecord);
    }

    public List<Catch> getAllCatches() {
        return catchRepository.findAll();
    }

    public Catch getCatchById(Long id) {
        return catchRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Catch not found with id: " + id));
    }

    public Catch updateCatch(Long id, Catch updatedCatch) {
        Catch existingCatch = catchRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Catch not found with id: " + id));

        // Update the existing catch with the new values
        existingCatch.setQuantity(updatedCatch.getQuantity());
        // Update any other fields as needed

        // Save and return the updated catch
        return catchRepository.save(existingCatch);
    }


    public void deleteCatch(Long id) {
        catchRepository.deleteById(id);
    }
}
