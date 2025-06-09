package ar.com.up.theater.manager.service;

import ar.com.up.theater.manager.dto.ApiResponse;
import ar.com.up.theater.manager.dto.HallDto;
import ar.com.up.theater.manager.dto.ShowDto;
import ar.com.up.theater.manager.dto.ShowRegistrationRequest;
import ar.com.up.theater.manager.mapper.TheaterMapper;
import ar.com.up.theater.manager.model.*;
import ar.com.up.theater.manager.repository.HallRepository;
import ar.com.up.theater.manager.repository.ShowRepository;
import ar.com.up.theater.manager.service.impl.HallServiceImpl;
import ar.com.up.theater.manager.service.impl.ShowServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static ar.com.up.theater.manager.constant.errorCodes.INVALID_DATE_ERROR;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ShowServiceImplTest {

    @Mock
    private ShowRepository showRepository;
    @Spy
    private TheaterMapper theaterMapper;
    @Mock
    private HallService hallService;
    @Mock
    private ArtistService artistService;
    @Mock
    private ShowTypeService showTypeService;
    @InjectMocks
    private ShowServiceImpl showService;



    @Test
    @DisplayName("getAllShows --> Devolver los shows")
    void getAllShows() {
        ClosedHall hall1 = new ClosedHall(1, "Sala A", 100, "CERRADA");
        Artist artist1 = new Artist(1,"radagast","argentina","comedia");
        Set<Artist> artists = new HashSet<>();
        artists.add(artist1);
        ShowType showType = new ShowType(1,"obra de teatro","unipersonal");
        Show show = new Show(1,"chanta",LocalDateTime.now(),60, BigDecimal.valueOf(100L),hall1,showType,artists);
        when(showRepository.findAll()).thenReturn(List.of(show));

        ApiResponse<List<ShowDto>> allShows = showService.getAllShows();
        assertNotNull(allShows);
        assertFalse(CollectionUtils.isEmpty(allShows.getData()));
        assertEquals(1, allShows.getData().size());

        verify(showRepository).findAll();

    }
    @Test
    @DisplayName("createShow () -> crear correrctamente el espetaculo")
    void createShow() {
        ShowRegistrationRequest showRegistrationRequest = getShowRegistrationRequest();
        when(showRepository.findByNameIgnoreCase(anyString())).thenReturn(Optional.empty());
        ShowType showType = new ShowType(1,"teatro","obra de teatro");
        when(showTypeService.getShowTypeByName(anyString())).thenReturn(showType);
        ClosedHall hall1 = new ClosedHall(1, "Sala A", 100, "CERRADA");
        when(hallService.getHallByType(anyString())).thenReturn(hall1);
        Artist artist1 = new Artist(1,"radagast","argentina","comedia");
        when(artistService.getArtistByName(anyString())).thenReturn(artist1);
        when(showRepository.save(any())).thenReturn(getNewShow());
        ShowDto showDto = new ShowDto();
        showDto.setId(1);
        showDto.setName("chante");
        when(theaterMapper.showToDto(any())).thenReturn(showDto);

        ApiResponse<List<ShowDto>> newShow = showService.createShow(showRegistrationRequest);

        ArgumentCaptor<Show> cap = ArgumentCaptor.forClass(Show.class);
        verify(showRepository).save(cap.capture());
        Show saved = cap.getValue();
        assertEquals(showRegistrationRequest.getName(), saved.getName());

        verify(showTypeService).getShowTypeByName(anyString());
        verify(hallService).getHallByType(anyString());
        verify(artistService).getArtistByName(anyString());
    }
    @Test
    @DisplayName("createShow () -> Error de superposicion de fechas")
    void createShow_dateConflict() {

        ShowRegistrationRequest showRegistrationRequest = getShowRegistrationRequest();
        when(showRepository.findByNameIgnoreCase(anyString())).thenReturn(Optional.empty());
        ClosedHall hall1 = new ClosedHall(1, "Sala A", 100, "CERRADA");
        Show existingShow = new Show();
        existingShow.setName("Existing Show");
        existingShow.setMinutes(60);
        existingShow.setDate(LocalDateTime.of(2025,6,9,18,0,0));
        hall1.getShows().add(existingShow);
        when(hallService.getHallByType(anyString())).thenReturn(hall1);

        ApiResponse<List<ShowDto>> newShow = showService.createShow(showRegistrationRequest);
        assertNotNull(newShow);
        assertFalse(CollectionUtils.isEmpty(newShow.getErrors()));
        assertEquals(INVALID_DATE_ERROR.getCode(),newShow.getErrors().getFirst().getCode());
        verify(showTypeService, never()).getShowTypeByName(any());
        verify(artistService, never()).getArtistByName(any());
        verify(showRepository, never()).save(any());
    }


    private ShowRegistrationRequest getShowRegistrationRequest(){
        ShowRegistrationRequest request = new ShowRegistrationRequest();
        request.setDate("2025-06-09");
        request.setHour("17:00:00");
        request.setDuration(60);
        request.setPrice(BigDecimal.valueOf(500L));
        request.setHall("CERRADA");
        request.setName("chanta");
        request.setType("teatro");
        List<String> artists = Collections.singletonList("radagast");
        request.setArtists(artists);
        return request;
    }

    private Show getNewShow(){
        Show show = new Show();
        show.setShowId(1);
        show.setName("chanta");
        return show;
    }
}