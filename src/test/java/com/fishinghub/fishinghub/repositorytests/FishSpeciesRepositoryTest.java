package com.fishinghub.fishinghub.repositorytests;
import com.fishinghub.fishinghub.FishinghubApplication;
import com.fishinghub.fishinghub.entity.FishSpecies;
import com.fishinghub.fishinghub.repository.FishSpeciesRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = FishinghubApplication.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)  // Specify NONE if not testing the web environment
public class FishSpeciesRepositoryTest {

    @Autowired
    private FishSpeciesRepository fishSpeciesRepository;

    @Before
    public void setUp() {
    }

    @Test
    public void testSaveFishSpecies() {
        FishSpecies fishSpecies = new FishSpecies();
        fishSpecies.setName("Striped Bass");
        fishSpecies.setDescription("The striped bass, also called the Atlantic striped bass, striper, linesider, rock, or rockfish");
        FishSpecies savedFishSpecies = fishSpeciesRepository.save(fishSpecies);

        assertNotNull(savedFishSpecies);
        assertNotNull(savedFishSpecies.getId());
        assertEquals("Striped Bass", savedFishSpecies.getName());
    }

    @Test
    public void testFindFishSpeciesById() {
        FishSpecies fishSpecies = new FishSpecies();
        fishSpecies.setName("Salmon");
        fishSpecies.setDescription("Popular fish for fishing known for its migrations upstream.");
        fishSpecies = fishSpeciesRepository.save(fishSpecies);

        Optional<FishSpecies> foundFishSpecies = fishSpeciesRepository.findById(fishSpecies.getId());

        assertTrue(foundFishSpecies.isPresent());
        assertEquals("Salmon", foundFishSpecies.get().getName());
    }

    @Test
    public void testUpdateFishSpecies() {
        FishSpecies fishSpecies = new FishSpecies();
        fishSpecies.setName("Largemouth Bass");
        fishSpecies.setDescription("Common freshwater fish known for being a challenging catch.");
        fishSpecies = fishSpeciesRepository.save(fishSpecies);

        FishSpecies toUpdate = fishSpeciesRepository.findById(fishSpecies.getId()).orElse(null);
        assertNotNull(toUpdate);

        toUpdate.setDescription("Popular sport fish in North America known for its fight.");
        FishSpecies updatedFishSpecies = fishSpeciesRepository.save(toUpdate);

        assertEquals("Popular sport fish in North America known for its fight.", updatedFishSpecies.getDescription());
    }

    @Test
    public void testDeleteFishSpecies() {
        FishSpecies fishSpecies = new FishSpecies();
        fishSpecies.setName("Pike");
        fishSpecies.setDescription("Predatory fish known for its sharp teeth.");
        fishSpecies = fishSpeciesRepository.save(fishSpecies);

        assertNotNull(fishSpeciesRepository.findById(fishSpecies.getId()));

        fishSpeciesRepository.delete(fishSpecies);

        assertFalse(fishSpeciesRepository.findById(fishSpecies.getId()).isPresent());
    }

    @Test
    public void testFindAllFishSpecies() {
        FishSpecies sp1 = new FishSpecies();
        sp1.setName("Catfish");
        sp1.setDescription("Bottom-dwelling fish known for its whisker-like barbels.");

        FishSpecies sp2 = new FishSpecies();
        sp2.setName("Carp");
        sp2.setDescription("Freshwater fish that is considered a nuisance in some areas but prized in others.");

        fishSpeciesRepository.save(sp1);
        fishSpeciesRepository.save(sp2);

        List<FishSpecies> species = fishSpeciesRepository.findAll();
        assertNotNull(species);
        assertTrue(species.size() >= 2);
    }
}
