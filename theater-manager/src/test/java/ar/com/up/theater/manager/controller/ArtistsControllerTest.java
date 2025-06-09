package ar.com.up.theater.manager.controller;

import ar.com.up.theater.manager.dto.ApiResponse;
import ar.com.up.theater.manager.dto.ArtistDto;
import ar.com.up.theater.manager.dto.HallDto;
import ar.com.up.theater.manager.service.ArtistService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ArtistsControllerTest {

    @Mock
    ArtistService artistService;
    @InjectMocks
    ArtistsController artistsController;

    @Test
    @DisplayName("getAllArtists() -> Devuelve 200 con los artistas")
    void GET_Artists_shouldReturn200(){

        ArtistDto dto = new ArtistDto(1,"radagast");
        ApiResponse<List<ArtistDto>> apiResponse = ApiResponse.success(List.of(dto));
        when(artistService.getAllArtists()).thenReturn(apiResponse);

        ResponseEntity<ApiResponse<List<ArtistDto>>> response = artistsController.getAll();

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertFalse(CollectionUtils.isEmpty(response.getBody().getData()));

        verify(artistService).getAllArtists();
    }
}