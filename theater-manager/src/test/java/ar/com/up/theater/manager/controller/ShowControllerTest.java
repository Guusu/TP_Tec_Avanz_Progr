package ar.com.up.theater.manager.controller;

import ar.com.up.theater.manager.dto.*;
import ar.com.up.theater.manager.service.ShowService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ShowControllerTest {

    @Mock
    private ShowService showService;

    @InjectMocks
    private ShowController showController;

    @Test
    @DisplayName("getAllShows() -> Devuelve 200 con los shows")
    void GET_Shows_shouldReturn200(){
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime.now().format(formatter);
        ArtistDto artists = new ArtistDto(1,"radagast");
        HallSummaryDto hall = new HallSummaryDto( "Sala Prueba", 80, "CERRADA");
        ShowDto dto = new ShowDto(1, "chanta", LocalDateTime.now().format(formatter), 60,hall, BigDecimal.valueOf(100L), Collections.singletonList(artists));
        ApiResponse<List<ShowDto>> apiResponse = ApiResponse.success(List.of(dto));
        when(showService.getAllShows()).thenReturn(apiResponse);

        ResponseEntity<ApiResponse<List<ShowDto>>> response = showController.getAll();

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertFalse(CollectionUtils.isEmpty(response.getBody().getData()));

        verify(showService).getAllShows();
    }

    @Test
    @DisplayName("createShow() -> devuelve 201")
    void POST_createShow_success() throws Exception {
        Jwt jwt = getJWT();
        ShowRegistrationRequest request = new ShowRegistrationRequest();
        request.setDate("2025/06/09");
        request.setHour("17:00:00");
        request.setDuration(60);
        request.setPrice(BigDecimal.valueOf(500L));
        request.setHall("CERRADA");
        request.setName("chanta");
        request.setType("teatro");
        List<String> artists = Collections.singletonList("radagast");
        request.setArtists(artists);

        ShowDto showDto = new ShowDto();
        showDto.setId(1);
        showDto.setName("chante");
        ApiResponse<List<ShowDto>> ok = ApiResponse.success(Collections.singletonList(showDto));
        when(showService.createShow(any())).thenReturn(ok);

        ResponseEntity<ApiResponse<List<ShowDto>>> response = showController.createShow(request, jwt);

        assertNotNull(response);
        assertEquals(201, response.getStatusCode().value());
        assertNotNull(response.getBody());
        assertFalse(CollectionUtils.isEmpty(response.getBody().getData()));
        verify(showService).createShow(any());
    }

    @Test
    void POST_espectaculos_failure() throws Exception {
        ShowRegistrationRequest request = new ShowRegistrationRequest();
        Jwt jwt = getJWT();
        ApiResponse<List<ShowDto>> err = ApiResponse.failure(List.of(new ErrorDto("SH01","desc error")));
        when(showService.createShow(request)).thenReturn(err);

        ResponseEntity<ApiResponse<List<ShowDto>>> response = showController.createShow(request, jwt);

        assertNotNull(response);
        assertEquals(400, response.getStatusCode().value());
        assertNotNull(response.getBody());
        assertFalse(CollectionUtils.isEmpty(response.getBody().getErrors()));
        assertEquals("SH01", response.getBody().getErrors().getFirst().getCode());
        verify(showService).createShow(any());

    }

    private Jwt getJWT() {
        return Jwt.withTokenValue("dummy-token")
                .header("alg", "none")
                .subject("usuario")
                .build();
    }

}