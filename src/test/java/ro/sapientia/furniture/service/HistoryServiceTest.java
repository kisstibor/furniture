package ro.sapientia.furniture.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ro.sapientia.furniture.model.HistoryBody;
import ro.sapientia.furniture.repository.HistoryRepository;
import ro.sapientia.furniture.service.HistoryService;

public class HistoryServiceTest {

    @InjectMocks
    private HistoryService historyService;

    @Mock
    private HistoryRepository historyRepository;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindAllHistoryBodies() {
        List<HistoryBody> expectedHistoryBodies = Arrays.asList(
                new HistoryBody(1L, "History 1"),
                new HistoryBody(2L, "History 2")
        );
        when(historyRepository.findAll()).thenReturn(expectedHistoryBodies);

        List<HistoryBody> actualHistoryBodies = historyService.findAllHistoryBodies();

        verify(historyRepository).findAll();
        assertEquals(expectedHistoryBodies, actualHistoryBodies);
    }

    @Test
    public void testFindHistoryBodyById() {
        HistoryBody expectedHistoryBody = new HistoryBody(1L, "History 1");
        when(historyRepository.findHistoryById(1L)).thenReturn(expectedHistoryBody);

        HistoryBody actualHistoryBody = historyService.findHistoryBodyById(1L);

        verify(historyRepository).findHistoryById(1L);
        assertEquals(expectedHistoryBody, actualHistoryBody);
    }

    @Test
    public void testFindHistoryBodyById_NotFound() {
        when(historyRepository.findHistoryById(1L)).thenReturn(null);

        HistoryBody actualHistoryBody = historyService.findHistoryBodyById(1L);

        verify(historyRepository).findHistoryById(1L);
        assertNull(actualHistoryBody);
    }

    @Test
    public void testCreate() {
        HistoryBody expectedHistoryBody = new HistoryBody(1L, "History 1");
        when(historyRepository.saveAndFlush(expectedHistoryBody)).thenReturn(expectedHistoryBody);

        HistoryBody actualHistoryBody = historyService.create(expectedHistoryBody);

        verify(historyRepository).saveAndFlush(expectedHistoryBody);
        assertEquals(expectedHistoryBody, actualHistoryBody);
    }

    public void testUpdate() {
    	HistoryBody expectedHistoryBody = new HistoryBody(1L, "History 1");
    	when(historyRepository.saveAndFlush(expectedHistoryBody)).thenReturn(expectedHistoryBody);
    	    HistoryBody actualHistoryBody = historyService.update(expectedHistoryBody);

    	    verify(historyRepository).saveAndFlush(expectedHistoryBody);
    	    assertEquals(expectedHistoryBody, actualHistoryBody);
    }
    
    @Test
    public void testDelete() {
        historyService.delete(1L);

        verify(historyRepository).deleteById(1L);
    }
}
