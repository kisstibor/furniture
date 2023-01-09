package ro.sapientia.furniture.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import ro.sapientia.furniture.exception.FurniturePanelNotFoundException;
import ro.sapientia.furniture.model.FurniturePanel;
import ro.sapientia.furniture.repository.FurniturePanelRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static ro.sapientia.furniture.util.FurniturePanelDatabaseBuilder.buildTestFurniturePanel;

public class FurniturePanelServiceTest {

    @Mock
    private FurniturePanelRepository repositoryMock;

    @InjectMocks
    private FurniturePanelService service;

    @BeforeEach
    public void setUp() {
        repositoryMock = mock(FurniturePanelRepository.class);
        service = new FurniturePanelService(repositoryMock);
    }

    @Test
    public void itShouldCheckIfFindAllFurniturePanelsIsEmptyList() {
        //when
        when(repositoryMock.findAll()).thenReturn(Collections.emptyList());
        final List<FurniturePanel> furniturePanels = service.findAllFurniturePanels();

        //then
        assertEquals(0, furniturePanels.size());
    }

    @Test
    public void itShouldCheckIfFindAllFurniturePanelsIsNull() {
        //when
        when(repositoryMock.findAll()).thenReturn(null);
        final List<FurniturePanel> furniturePanels = service.findAllFurniturePanels();

        //then
        assertNull(furniturePanels);
    }

    @Test
    public void itShouldCheckIfCanFindAllFurniturePanels() {
        //when
        service.findAllFurniturePanels();

        //then
        verify(repositoryMock).findAll();
    }

    @Test
    public void itShouldCheckIfFindFurniturePanelByIdSucceeds() {
        when(repositoryMock.findById(anyLong())).thenReturn(Optional.empty());
    }

    @Test
    public void itShouldCheckIfFindFurniturePanelByIdDoesNotSucceed() {
        FurniturePanel furniturePanel = buildTestFurniturePanel();
        when(repositoryMock.findById(anyLong())).thenReturn(Optional.of(furniturePanel));

        FurniturePanel furniturePanel1 = service.findFurniturePanelById(1L);

        assertEquals(furniturePanel.getHeight(), furniturePanel1.getHeight());
    }


    @Test
    public void canCreateFurniturePanel() {
        /** Check whether the repository was invoked with the same furniturePanel, that we passed */
        //given
        FurniturePanel furniturePanel = new FurniturePanel();

        //when
        service.create(furniturePanel);

        //then
        ArgumentCaptor<FurniturePanel> furniturePanelArgumentCaptor = ArgumentCaptor.forClass(FurniturePanel.class);
        verify(repositoryMock).saveAndFlush(furniturePanelArgumentCaptor.capture());
        FurniturePanel capturedFurniturePanel = furniturePanelArgumentCaptor.getValue();
        assertThat(capturedFurniturePanel).isEqualTo(furniturePanel);
    }

    @Test
    public void itShouldCheckIfFindFurniturePanelUpdateDoesNotSucceeds() {
        final var furniturePanel = buildTestFurniturePanel();
        when(repositoryMock.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(
                FurniturePanelNotFoundException.class,
                () -> service.update(furniturePanel));

        verify(repositoryMock, times(0)).saveAndFlush(any(FurniturePanel.class));
    }

    @Test
    public void itShouldCheckIfFindFurniturePanelUpdateSucceeds() {
        FurniturePanel furniturePanel = FurniturePanel.builder()
                .id(1L)
                .heigth(10)
                .width(20)
                .depth(30)
                .build();
        FurniturePanel furniturePanel1 = buildTestFurniturePanel();
        when(repositoryMock.findById(anyLong())).thenReturn(Optional.of(furniturePanel1));

        service.update(furniturePanel);

        verify(repositoryMock, times(1)).saveAndFlush(any(FurniturePanel.class));
    }


    @Test
    public void itShouldCheckIfFurniturePanelDeleteFails() {
        //when
        when(repositoryMock.findById(anyLong())).thenReturn(Optional.empty());
        //then
        verify(repositoryMock, times(0)).deleteById(anyLong());
    }

    @Test
    public void itShouldCheckIfFurniturePanelDeleteSucceeds() {
        //when
        when(repositoryMock.findById(anyLong())).thenReturn(Optional.of(buildTestFurniturePanel()));
        service.delete(1L);

        //then
        verify(repositoryMock, times(1)).deleteById(anyLong());
    }
}