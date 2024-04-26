package com.fishinghub.fishinghub.controller;

import com.fishinghub.fishinghub.entity.FishSpecies;
import com.fishinghub.fishinghub.service.FishSpeciesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fishspecies")
public class FishSpeciesController {

    @Autowired
    private FishSpeciesService fishSpeciesService;

    @GetMapping
    public ResponseEntity<List<FishSpecies>> getAllFishSpecies() {
        List<FishSpecies> species = fishSpeciesService.getAllFishSpecies();
        return ResponseEntity.ok(species);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FishSpecies> getFishSpeciesById(@PathVariable Long id) {
        FishSpecies fishSpecies = fishSpeciesService.getFishSpeciesById(id);
        return fishSpecies != null ? ResponseEntity.ok(fishSpecies) : ResponseEntity.notFound().build();
    }
}
