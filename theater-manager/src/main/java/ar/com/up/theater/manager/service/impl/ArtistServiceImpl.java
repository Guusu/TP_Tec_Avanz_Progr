package ar.com.up.theater.manager.service.impl;

import ar.com.up.theater.manager.dto.ApiResponse;
import ar.com.up.theater.manager.dto.ArtistDto;
import ar.com.up.theater.manager.dto.ErrorDto;
import ar.com.up.theater.manager.dto.HallDto;
import ar.com.up.theater.manager.mapper.TheaterMapper;
import ar.com.up.theater.manager.model.Artist;
import ar.com.up.theater.manager.repository.ArtistRepository;
import ar.com.up.theater.manager.service.ArtistService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ArtistServiceImpl implements ArtistService {
    private final ArtistRepository artistRepository;
    private final TheaterMapper theaterMapper;
    Logger logger = LoggerFactory.getLogger(ArtistServiceImpl.class);
    public ArtistServiceImpl(ArtistRepository artistRepository, TheaterMapper theaterMapper) {
        this.artistRepository = artistRepository;
        this.theaterMapper = theaterMapper;
    }

    @Override
    public ApiResponse<List<ArtistDto>> getAllArtists() {
        try{
            List<ArtistDto> artists = artistRepository.findAll().stream()
                    .map(theaterMapper::artistToDto)
                    .toList();
            return ApiResponse.success(artists);
        }catch (Exception e){
            logger.error(e.getMessage());
            ErrorDto errorDto = new ErrorDto();
            errorDto.setCode("ART01");
            errorDto.setMessage(e.getMessage());
            return ApiResponse.failure(List.of(errorDto));
        }
    }

    @Override
    public Artist getArtistByName(String name) {
        return artistRepository.findByNameIgnoreCase(name).orElse(null);
    }
}
