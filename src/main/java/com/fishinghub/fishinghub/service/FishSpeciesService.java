package com.fishinghub.fishinghub.service;
import com.fishinghub.fishinghub.entity.FishSpecies;
import com.fishinghub.fishinghub.repository.FishSpeciesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
public class FishSpeciesService {

    @Autowired
    private FishSpeciesRepository fishSpeciesRepository;

    public List<FishSpecies> getAllFishSpecies() {
        return fishSpeciesRepository.findAll();
    }

    public FishSpecies getFishSpeciesById(Long id) {
        return fishSpeciesRepository.findById(id).orElse(null);
    }
}
