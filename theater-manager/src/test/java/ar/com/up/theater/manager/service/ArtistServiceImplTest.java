package ar.com.up.theater.manager.service;

import ar.com.up.theater.manager.dto.ApiResponse;
import ar.com.up.theater.manager.dto.ArtistDto;
import ar.com.up.theater.manager.dto.HallDto;
import ar.com.up.theater.manager.mapper.TheaterMapper;
import ar.com.up.theater.manager.model.Artist;
import ar.com.up.theater.manager.model.Hall;
import ar.com.up.theater.manager.repository.ArtistRepository;
import ar.com.up.theater.manager.service.impl.ArtistServiceImpl;
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
class ArtistServiceImplTest {

    @Mock
    private ArtistRepository artistRepository;

    @Spy
    private TheaterMapper theaterMapper;
    @InjectMocks
    private ArtistServiceImpl artistService;

    @Test
    @DisplayName("getAllArtist --> Devolver los artistas")
    void getAllArtists() {
        Artist artist1 = new Artist(1,"radagast","argentina","comedia");
        Artist artist2 = new Artist(2,"moldasky","argentina","comedia");
        when(artistRepository.findAll()).thenReturn(List.of(artist1, artist2));

        ApiResponse<List<ArtistDto>> allArtists = artistService.getAllArtists();
        assertNotNull(allArtists);
        assertFalse(CollectionUtils.isEmpty(allArtists.getData()));
        assertEquals(2, allArtists.getData().size());

        verify(artistRepository).findAll();

    }
    @Test
    @DisplayName("getAllArtist --> Error Inesperado")
    void getAllArtists_TechnicalError() {
        when(artistRepository.findAll()).thenThrow(new RuntimeException("TechError"));

        ApiResponse<List<ArtistDto>> allArtists = artistService.getAllArtists();
        assertNotNull(allArtists);
        assertFalse(CollectionUtils.isEmpty(allArtists.getErrors()));

        verify(artistRepository).findAll();

    }
    @Test
    @DisplayName("getArtistByName --> Ok")
    void getArtistByName() {
        Artist artist1 = new Artist(1,"radagast","argentina","comedia");
        when(artistRepository.findByNameIgnoreCase(anyString())).thenReturn(Optional.of(artist1));

        Artist artistByName = artistService.getArtistByName(artist1.getName());

        assertNotNull(artistByName);
        assertEquals(artist1, artistByName);

    }
}