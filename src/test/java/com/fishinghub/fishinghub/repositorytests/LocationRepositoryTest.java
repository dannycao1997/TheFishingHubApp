package com.fishinghub.fishinghub.repositorytests;
import com.fishinghub.fishinghub.FishinghubApplication;
import com.fishinghub.fishinghub.entity.Location;
import com.fishinghub.fishinghub.repository.LocationRepository;
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
public class LocationRepositoryTest {

    @Autowired
    private LocationRepository locationRepository;

    @Before
    public void setUp() {
    }

    @Test
    public void testSaveLocation() {
        Location location = new Location();
        location.setName("Lake Tahoe");
        location.setDescription("Lake in California Nevada");
        location.setLatitude(39.0968);
        location.setLongitude(-120.0324);
        Location savedLocation = locationRepository.save(location);

        assertNotNull(savedLocation);
        assertNotNull(savedLocation.getId());
        assertEquals("Lake Tahoe", savedLocation.getName());
    }

    @Test
    public void testFindLocationById() {
        Location location = new Location();
        location.setName("Yellowstone Lake");
        location.setDescription("Lake located in Yellowstone National Park");
        location.setLatitude(44.3963);
        location.setLongitude(-110.3666);
        location = locationRepository.save(location);

        Optional<Location> foundLocation = locationRepository.findById(location.getId());

        assertTrue(foundLocation.isPresent());
        assertEquals("Yellowstone Lake", foundLocation.get().getName());
    }

    @Test
    public void testUpdateLocation() {
        Location location = new Location();
        location.setName("Crater Lake");
        location.setDescription("Lake in Oregon");
        location.setLatitude(42.9446);
        location.setLongitude(-122.1090);
        location = locationRepository.save(location);

        Location toUpdate = locationRepository.findById(location.getId()).orElse(null);
        assertNotNull(toUpdate);

        toUpdate.setDescription("Deepest lake in the United States");
        Location updatedLocation = locationRepository.save(toUpdate);

        assertEquals("Deepest lake in the United States", updatedLocation.getDescription());
    }

    @Test
    public void testDeleteLocation() {
        Location location = new Location();
        location.setName("Mono Lake");
        location.setDescription("Salty lake located in Cali");
        location.setLatitude(38.0165);
        location.setLongitude(-119.0093);
        location = locationRepository.save(location);

        assertNotNull(locationRepository.findById(location.getId()));

        locationRepository.delete(location);

        assertFalse(locationRepository.findById(location.getId()).isPresent());
    }

    @Test
    public void testFindAllLocations() {
        Location loc1 = new Location();
        loc1.setName("Mono Lake");
        loc1.setDescription("Salty lake located in Cali");
        loc1.setLatitude(38.0165);
        loc1.setLongitude(-119.0093);

        Location loc2 = new Location();
        loc2.setName("Lake Erie");
        loc2.setDescription("4th largest of the Great Lakes in the States");
        loc2.setLatitude(42.2);
        loc2.setLongitude(-81.2);

        locationRepository.save(loc1);
        locationRepository.save(loc2);

        List<Location> locations = locationRepository.findAll();
        assertNotNull(locations);
        assertTrue(locations.size() >= 2);
    }
}
