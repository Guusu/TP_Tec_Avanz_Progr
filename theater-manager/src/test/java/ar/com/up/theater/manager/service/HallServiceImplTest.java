package ar.com.up.theater.manager.service;

import ar.com.up.theater.manager.dto.ApiResponse;
import ar.com.up.theater.manager.dto.ArtistDto;
import ar.com.up.theater.manager.dto.HallDto;
import ar.com.up.theater.manager.mapper.TheaterMapper;
import ar.com.up.theater.manager.model.Amphitheater;
import ar.com.up.theater.manager.model.Artist;
import ar.com.up.theater.manager.model.ClosedHall;
import ar.com.up.theater.manager.model.Hall;
import ar.com.up.theater.manager.repository.HallRepository;
import ar.com.up.theater.manager.service.impl.HallServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HallServiceImplTest {

    @Mock
    private HallRepository hallRepository;
    @Spy
    private TheaterMapper theaterMapper;
    @InjectMocks
    private HallServiceImpl hallService;



    @Test
    @DisplayName("getAllSalas --> Devolver las salas")
    void getAllHalls() {
        ClosedHall hall1 = new ClosedHall(1, "Sala A", 100, "CERRADA");
        Amphitheater hall2 = new Amphitheater(2, "Sala B", 50, "ANFITEATRO");
        when(hallRepository.findAll()).thenReturn(List.of(hall1, hall2));

        ApiResponse<List<HallDto>> allHalls = hallService.getAllHalls();
        assertNotNull(allHalls);
        assertFalse(CollectionUtils.isEmpty(allHalls.getData()));
        assertEquals(2, allHalls.getData().size());

        verify(hallRepository).findAll();

    }
    @Test
    @DisplayName("getallHalls --> Error Inesperado")
    void getAllHalls_TechnicalError() {
        when(hallRepository.findAll()).thenThrow(new RuntimeException("TechError"));

        ApiResponse<List<HallDto>> allHalls = hallService.getAllHalls();
        assertNotNull(allHalls);
        assertFalse(CollectionUtils.isEmpty(allHalls.getErrors()));

        verify(hallRepository).findAll();

    }
    @Test
    @DisplayName("getHallByType --> Ok")
    void getHallByType() {
        Amphitheater hall = new Amphitheater();

        when(hallRepository.findByTypeIgnoreCase(anyString())).thenReturn(Optional.of(hall));

        Hall hall1 = hallService.getHallByType("ANFITEATRO");

        assertNotNull(hall1);
        assertEquals(hall, hall1);

    }
}