package ar.com.up.theater.manager.service;

import ar.com.up.theater.manager.dto.ApiResponse;
import ar.com.up.theater.manager.dto.ShowDto;
import ar.com.up.theater.manager.dto.ShowRegistrationRequest;
import ar.com.up.theater.manager.model.Show;

import java.util.List;

public interface ShowService {
    ApiResponse<List<ShowDto>> getAllShows ();

    ApiResponse<List<ShowDto>> createShow (ShowRegistrationRequest registrationRequest);

    Show getShowByName (String name);

}
