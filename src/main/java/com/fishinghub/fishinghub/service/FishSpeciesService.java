package com.fishinghub.fishinghub.service;
import com.fishinghub.fishinghub.entity.FishSpecies;
import com.fishinghub.fishinghub.repository.FishSpeciesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class FishSpeciesService {

    private final FishSpeciesRepository fishSpeciesRepository;

    @Autowired
    public FishSpeciesService(FishSpeciesRepository fishSpeciesRepository) {
        this.fishSpeciesRepository = fishSpeciesRepository;
    }

    public List<FishSpecies> getAllFishSpecies() {
        return fishSpeciesRepository.findAll();
    }

    public Optional<FishSpecies> getFishSpeciesById(Long id) {
        return fishSpeciesRepository.findById(id);
    }

    public FishSpecies saveFishSpecies(FishSpecies fishSpecies) {
        return fishSpeciesRepository.save(fishSpecies);
    }

    public void deleteFishSpeciesById(Long id) {
        fishSpeciesRepository.deleteById(id);
    }
}
