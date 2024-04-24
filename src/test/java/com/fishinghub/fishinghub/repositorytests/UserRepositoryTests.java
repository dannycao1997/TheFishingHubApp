package com.fishinghub.fishinghub.repositorytests;

import com.fishinghub.fishinghub.entity.User;
import com.fishinghub.fishinghub.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class UserRepositoryTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void findByUsername() {

        //setup
        User user1 = new User();
        user1.setUsername("Danny");
        user1.setPassword("Ilovefishing");
        userRepository.save(user1);

        //test findbyusername
        User found = userRepository.findByUsername("Danny").orElse(null);
        Assertions.assertNotNull(found);
        Assertions.assertEquals("Danny", found.getUsername());

    }

    




}
