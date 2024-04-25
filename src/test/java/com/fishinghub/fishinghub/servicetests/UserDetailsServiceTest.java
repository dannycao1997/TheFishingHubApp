package com.fishinghub.fishinghub.servicetests;
import com.fishinghub.fishinghub.entity.User;
import com.fishinghub.fishinghub.repository.UserRepository;
import com.fishinghub.fishinghub.service.UserDetailsService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import java.util.Optional;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class UserDetailsServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserDetailsService userDetailsService;

    private User user;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        user = new User();
        user.setId(1L);
        user.setUsername("FlyFisherMan");
        user.setPassword("trout");
    }

    @Test
    public void testLoadUserByUsername() {
        when(userRepository.findByUsername("FlyFisherMan")).thenReturn(Optional.of(user));

        UserDetails userDetails = userDetailsService.loadUserByUsername("FlyFisherMan");

        assertNotNull(userDetails);
        assertEquals("FlyFisherMan", userDetails.getUsername());
        assertEquals("trout", userDetails.getPassword());

        verify(userRepository).findByUsername("FlyFisherMan");
    }

    @Test(expected = UsernameNotFoundException.class)
    public void testLoadUserByUsernameNotFound() {
        when(userRepository.findByUsername("User does not exist")).thenReturn(Optional.empty());

        userDetailsService.loadUserByUsername("User does not exist");
    }
}
