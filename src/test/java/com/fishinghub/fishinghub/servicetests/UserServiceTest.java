package com.fishinghub.fishinghub.servicetests;
import com.fishinghub.fishinghub.entity.User;
import com.fishinghub.fishinghub.repository.UserRepository;
import com.fishinghub.fishinghub.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @InjectMocks
    private UserService userService;

    private User user;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        user = new User();
        user.setId(1L);
        user.setUsername("bot");
        user.setPassword("password123");
        user.setEmail("test@example.com");
    }

    @Test
    public void testRegisterUser() {
        when(bCryptPasswordEncoder.encode(user.getPassword())).thenReturn("encryptedPassword");
        when(userRepository.save(any(User.class))).thenReturn(user);

        User registeredUser = userService.registerUser(user);

        assertNotNull(registeredUser);
        assertEquals("encryptedPassword", registeredUser.getPassword());
        verify(userRepository).save(user);
        verify(bCryptPasswordEncoder).encode("password123");
    }

    @Test
    public void testValidateUser() {
        when(userRepository.findByUsername("bot")).thenReturn(Optional.of(user));
        when(bCryptPasswordEncoder.matches("password123", user.getPassword())).thenReturn(true);

        User validatedUser = userService.validateUser("bot", "password123");

        assertNotNull(validatedUser);
        assertEquals("bot", validatedUser.getUsername());
        verify(userRepository).findByUsername("bot");
        verify(bCryptPasswordEncoder).matches("password123", user.getPassword());
    }

    @Test
    public void testValidateUserFailure() {
        when(userRepository.findByUsername("bot")).thenReturn(Optional.of(user));
        when(bCryptPasswordEncoder.matches("wrongPassword", user.getPassword())).thenReturn(false);

        User validatedUser = userService.validateUser("bot", "wrongPassword");

        assertNull(validatedUser);
    }

    @Test
    public void testGetAllUsers() {
        when(userRepository.findAll()).thenReturn(Arrays.asList(user));

        List<User> users = userService.getAllUsers();

        assertNotNull(users);
        assertFalse(users.isEmpty());
        assertEquals(1, users.size());
        verify(userRepository).findAll();
    }

    @Test
    public void testGetUserById() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        User foundUser = userService.getUserById(1L);

        assertNotNull(foundUser);
        assertEquals(Long.valueOf(1), foundUser.getId());
        verify(userRepository).findById(1L);
    }

    @Test(expected = RuntimeException.class)
    public void testGetUserByIdNotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        userService.getUserById(1L);
    }

    @Test
    public void testUpdateUser() {
        when(userRepository.save(user)).thenReturn(user);

        User updatedUser = userService.updateUser(user);

        assertNotNull(updatedUser);
        assertEquals("bot", updatedUser.getUsername());
        verify(userRepository).save(user);
    }

    @Test
    public void testDeleteUser() {
        doNothing().when(userRepository).deleteById(1L);

        userService.deleteUser(1L);

        verify(userRepository).deleteById(1L);
    }
}

