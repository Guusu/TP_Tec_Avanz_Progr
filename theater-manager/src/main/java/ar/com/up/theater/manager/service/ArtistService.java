package ar.com.up.theater.manager.service;

import ar.com.up.theater.manager.dto.ApiResponse;
import ar.com.up.theater.manager.dto.ArtistDto;
import ar.com.up.theater.manager.model.Artist;

import java.util.List;

public interface ArtistService {
    ApiResponse<List<ArtistDto>> getAllArtists();

    Artist getArtistByName(String name);
}
