package ar.com.up.theater.manager.controller;

import ar.com.up.theater.manager.dto.ApiResponse;
import ar.com.up.theater.manager.dto.HallDto;
import ar.com.up.theater.manager.service.HallService;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestController
@RequestMapping("theater/hall")
public class HallController {

    private final HallService hallService;

    public HallController(HallService hallService) {
        this.hallService = hallService;
    }
    @GetMapping
    public ResponseEntity<ApiResponse<List<HallDto>>> getAll() {

        ApiResponse<List<HallDto>> apiResponse = hallService.getAllHalls();

        if (!CollectionUtils.isEmpty(apiResponse.getErrors()))
            return new ResponseEntity<>(apiResponse,BAD_REQUEST);

        return ResponseEntity.ok(apiResponse);
    }
}
