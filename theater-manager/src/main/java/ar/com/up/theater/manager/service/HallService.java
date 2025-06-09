package ar.com.up.theater.manager.service;

import ar.com.up.theater.manager.dto.ApiResponse;
import ar.com.up.theater.manager.dto.HallDto;
import ar.com.up.theater.manager.model.Hall;

import java.util.List;

public interface HallService {
    ApiResponse<List<HallDto>> getAllHalls ();

    Hall getHallByType(String hallType);
}
