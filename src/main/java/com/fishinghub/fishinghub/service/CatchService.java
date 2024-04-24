package com.fishinghub.fishinghub.service;
import com.fishinghub.fishinghub.entity.Catch;
import com.fishinghub.fishinghub.repository.CatchRepository;
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
        return catchRepository.findById(id).orElseThrow(() -> new RuntimeException("Yo Catch? can't find it"));
    }

    public Catch updateCatch(Catch catchRecord) {
        return catchRepository.save(catchRecord);
    }

    public void deleteCatch(Long id) {
        catchRepository.deleteById(id);
    }
}
