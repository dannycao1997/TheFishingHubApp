package com.fishinghub.fishinghub.repositorytests;
import com.fishinghub.fishinghub.FishinghubApplication;
import com.fishinghub.fishinghub.entity.Catch;
import com.fishinghub.fishinghub.entity.FishSpecies;
import com.fishinghub.fishinghub.entity.Location;
import com.fishinghub.fishinghub.entity.User;
import com.fishinghub.fishinghub.repository.CatchRepository;
import com.fishinghub.fishinghub.repository.FishSpeciesRepository;
import com.fishinghub.fishinghub.repository.LocationRepository;
import com.fishinghub.fishinghub.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;
import java.util.Optional;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = FishinghubApplication.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class CatchRepositoryTest {

    @Autowired
    private CatchRepository catchRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FishSpeciesRepository fishSpeciesRepository;

    @Autowired
    private LocationRepository locationRepository;

    private User testUser;
    private FishSpecies testFishSpecies;
    private Location testLocation;

    @Before
    public void setUp() {
        catchRepository.deleteAll();
        userRepository.deleteAll();
        fishSpeciesRepository.deleteAll();
        locationRepository.deleteAll();

        testUser = new User("FishermanDan", "pw", "FishermanDan@example.com");
        testFishSpecies = new FishSpecies();
        testLocation = new Location();

        testFishSpecies.setName("Trout");
        testFishSpecies.setDescription("A popular freshwater fish.");
        testLocation.setName("Lake Mystery");
        testLocation.setLatitude(45.1234);
        testLocation.setLongitude(-123.4567);

        userRepository.save(testUser);
        fishSpeciesRepository.save(testFishSpecies);
        locationRepository.save(testLocation);
    }

    @Test
    public void testSaveCatch() {
        Catch catchRecord = new Catch();
        catchRecord.setUser(testUser);
        catchRecord.setFishSpecies(testFishSpecies);
        catchRecord.setLocation(testLocation);
        catchRecord.setQuantity(3);
        Catch savedCatch = catchRepository.save(catchRecord);

        assertNotNull(savedCatch);
        assertNotNull(savedCatch.getId());
        Assertions.assertEquals(3, savedCatch.getQuantity());
    }

    @Test
    public void testFindCatchById() {
        Catch catchRecord = new Catch();
        catchRecord.setUser(testUser);
        catchRecord.setFishSpecies(testFishSpecies);
        catchRecord.setLocation(testLocation);
        catchRecord.setQuantity(2);
        catchRecord = catchRepository.save(catchRecord);

        Optional<Catch> foundCatch = catchRepository.findById(catchRecord.getId());

        assertTrue(foundCatch.isPresent());
        Assertions.assertEquals(2, foundCatch.get().getQuantity());
    }

    @Test
    public void testUpdateCatch() {
        Catch catchRecord = new Catch();
        catchRecord.setUser(testUser);
        catchRecord.setFishSpecies(testFishSpecies);
        catchRecord.setLocation(testLocation);
        catchRecord.setQuantity(5);
        catchRecord = catchRepository.save(catchRecord);

        Catch toUpdate = catchRepository.findById(catchRecord.getId()).orElse(null);
        assertNotNull(toUpdate);

        toUpdate.setQuantity(7);
        Catch updatedCatch = catchRepository.save(toUpdate);

        Assertions.assertEquals(7, updatedCatch.getQuantity());
    }

    @Test
    public void testDeleteCatch() {
        Catch catchRecord = new Catch();
        catchRecord.setUser(testUser);
        catchRecord.setFishSpecies(testFishSpecies);
        catchRecord.setLocation(testLocation);
        catchRecord.setQuantity(1);
        catchRecord = catchRepository.save(catchRecord);

        assertNotNull(catchRepository.findById(catchRecord.getId()));

        catchRepository.delete(catchRecord);

        assertFalse(catchRepository.findById(catchRecord.getId()).isPresent());
    }

    @Test
    public void testFindAllCatches() {
        Catch catch1 = new Catch();
        catch1.setUser(testUser);
        catch1.setFishSpecies(testFishSpecies);
        catch1.setLocation(testLocation);
        catch1.setQuantity(4);

        Catch catch2 = new Catch();
        catch2.setUser(testUser);
        catch2.setFishSpecies(testFishSpecies);
        catch2.setLocation(testLocation);
        catch2.setQuantity(2);

        catchRepository.save(catch1);
        catchRepository.save(catch2);

        List<Catch> catches = catchRepository.findAll();
        assertNotNull(catches);
        assertTrue(catches.size() >= 2);
    }
}

