package com.fishinghub.fishinghub.repositorytests;
import com.fishinghub.fishinghub.FishinghubApplication;
import com.fishinghub.fishinghub.entity.User;
import com.fishinghub.fishinghub.repository.UserRepository;
import jakarta.transaction.Transactional;
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
@SpringBootTest(classes = FishinghubApplication.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)  // Specify NONE if you are not testing web environment
@Transactional
public class UserRepositoryTests {

    @Autowired
    private UserRepository userRepository;

    private User user;

    @Before
    public void setUp() {

        // setting up a user for the post tests

        user = new User();
        user.setUsername("andy");
        user.setPassword("pokemonm");
        user.setEmail("andypokemon@example.com");
        userRepository.save(user);
    }

    @Test
    public void testSaveUser() {
        User savedUser = userRepository.save(user);
        assertNotNull(savedUser);
        assertNotNull(savedUser.getId());
    }

    @Test
    public void testFindByUsername() {
        // save the user to the database
        userRepository.save(user);


        // getting the user by username
        Optional<User> found = userRepository.findByUsername(user.getUsername());
        assertTrue(found.isPresent());
        assertEquals(user.getUsername(), found.get().getUsername());
    }

    @Test
    public void testUpdateUser() {
        // save the user to the database
        User savedUser = userRepository.save(user);

        // updating the user info
        savedUser.setEmail("mewytwo1@example.com");
        savedUser.setPassword("mewpyassword1");
        userRepository.save(savedUser);

        // Retrieve the updated user
        User updatedUser = userRepository.findById(savedUser.getId()).orElse(null);
        assertNotNull(updatedUser);
        assertEquals("mewytwo1@example.com", updatedUser.getEmail());
    }

    @Test
    public void testDeleteUser() {
        // save the user to the database
        User savedUser = userRepository.save(user);

        // Verify the user exists
        assertTrue(userRepository.findById(savedUser.getId()).isPresent());

        // Delete the user
        userRepository.delete(savedUser);

        // Verify the user has been deleted
        Optional<User> deletedUser = userRepository.findById(savedUser.getId());
        assertFalse(deletedUser.isPresent());
    }

    @Test
    public void testFindAllUsers() {
        // save multiple users
        userRepository.save(new User("asan", "bbyq", "asan1@example.com"));
        userRepository.save(new User("shijin", "yugiyoh", "shijin1@example.com"));

        // retrieve all users
        List<User> users = userRepository.findAll();
        assertNotNull(users);
        assertTrue(users.size() >= 2);
    }
}
