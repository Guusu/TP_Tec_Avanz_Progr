package ar.com.up.theater.manager.controller;

import ar.com.up.theater.manager.dto.ApiResponse;
import ar.com.up.theater.manager.dto.ShowDto;
import ar.com.up.theater.manager.dto.ShowRegistrationRequest;
import ar.com.up.theater.manager.service.ShowService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("theater/show")
public class ShowController {
    private final ShowService showService;

    public ShowController(ShowService showService) {
        this.showService = showService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ShowDto>>> getAll() {


        ApiResponse<List<ShowDto>> apiResponse = showService.getAllShows();

        if (!CollectionUtils.isEmpty(apiResponse.getErrors()))
            return new ResponseEntity<>(apiResponse,BAD_REQUEST);

        return ResponseEntity.ok(apiResponse);
    }
    @PostMapping
    public ResponseEntity<ApiResponse<List<ShowDto>>> createShow(@RequestBody ShowRegistrationRequest showRegistrationRequest,
                                                                 @AuthenticationPrincipal Jwt jwt) {
        ApiResponse<List<ShowDto>> apiResponse = showService.createShow(showRegistrationRequest);
        if (!CollectionUtils.isEmpty(apiResponse.getErrors()))
            return new ResponseEntity<>(apiResponse,BAD_REQUEST);

        return new ResponseEntity<>(apiResponse,CREATED);

    }
}
