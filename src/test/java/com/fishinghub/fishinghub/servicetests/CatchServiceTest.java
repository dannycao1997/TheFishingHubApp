package com.fishinghub.fishinghub.servicetests;
import com.fishinghub.fishinghub.entity.Catch;
import com.fishinghub.fishinghub.repository.CatchRepository;
import com.fishinghub.fishinghub.service.CatchService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class CatchServiceTest {

    @Mock
    private CatchRepository catchRepository;

    @InjectMocks
    private CatchService catchService;

    private Catch testCatch;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        testCatch = new Catch();
        testCatch.setId(1L);
        testCatch.setQuantity(5);
    }

    @Test
    public void testLogCatch() {
        when(catchRepository.save(any(Catch.class))).thenReturn(testCatch);
        Catch savedCatch = catchService.logCatch(testCatch);
        assertNotNull(savedCatch);
        assertEquals(Long.valueOf(1), savedCatch.getId());
        verify(catchRepository).save(testCatch);
    }

    @Test
    public void testGetAllCatches() {
        when(catchRepository.findAll()).thenReturn(Arrays.asList(testCatch));
        List<Catch> catches = catchService.getAllCatches();
        assertFalse(catches.isEmpty());
        assertEquals(1, catches.size());
        verify(catchRepository).findAll();
    }

    @Test
    public void testGetCatchById() {
        when(catchRepository.findById(1L)).thenReturn(Optional.of(testCatch));
        Catch foundCatch = catchService.getCatchById(1L);
        assertNotNull(foundCatch);
        assertEquals(Long.valueOf(1), foundCatch.getId());
        verify(catchRepository).findById(1L);
    }

    @Test(expected = RuntimeException.class)
    public void testGetCatchByIdNotFound() {
        when(catchRepository.findById(1L)).thenReturn(Optional.empty());
        catchService.getCatchById(1L);
    }

    @Test
    public void testUpdateCatch() {
        when(catchRepository.save(testCatch)).thenReturn(testCatch);
        testCatch.setQuantity(10);
        Catch updatedCatch = catchService.updateCatch(testCatch);
        assertNotNull(updatedCatch);
        assertEquals(10, updatedCatch.getQuantity());
        verify(catchRepository).save(testCatch);
    }

    @Test
    public void testDeleteCatch() {
        doNothing().when(catchRepository).deleteById(1L);
        catchService.deleteCatch(1L);
        verify(catchRepository).deleteById(1L);
    }
}

