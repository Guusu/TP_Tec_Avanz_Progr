package ar.com.up.theater.manager.controller;

import ar.com.up.theater.manager.dto.ApiResponse;
import ar.com.up.theater.manager.dto.ArtistDto;
import ar.com.up.theater.manager.dto.HallDto;
import ar.com.up.theater.manager.service.ArtistService;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestController
@RequestMapping("theater/artists")
public class ArtistsController {
    private final ArtistService artistService;

    public ArtistsController(ArtistService artistService) {
        this.artistService = artistService;
    }


    @GetMapping
    public ResponseEntity<ApiResponse<List<ArtistDto>>> getAll() {

        ApiResponse<List<ArtistDto>> apiResponse = artistService.getAllArtists();

        if (!CollectionUtils.isEmpty(apiResponse.getErrors()))
            return new ResponseEntity<>(apiResponse, BAD_REQUEST);

        return ResponseEntity.ok(apiResponse);
    }
}
