package com.fishinghub.fishinghub.repositorytests;

import com.fishinghub.fishinghub.entity.User;
import com.fishinghub.fishinghub.repository.UserRepository;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.runner.RunWith;
import org.junit.Test;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindByUsername() {
        User user = new User();
        user.setUsername("cao");
        user.setPassword("Ildsdsdovefishing");
        user.setEmail("danssddny@gmail.com");
        userRepository.save(user);

        User found = userRepository.findByUsername("cao").orElse(null);

        assertNotNull(found);
        assertEquals("cao", found.getUsername());
    }

    @Test
    public void testSaveUser() {
        User user = new User();
        user.setUsername("hesdsdllo");
        user.setPassword("123dsd456");
        user.setEmail("fishermdsassdsdandan@example.com");
        User savedUser = userRepository.save(user);

        assertNotNull(savedUser);
        assertNotNull(savedUser.getId());
    }
}
