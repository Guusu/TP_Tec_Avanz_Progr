package ar.com.up.theater.manager.service.impl;

import ar.com.up.theater.manager.dto.ApiResponse;
import ar.com.up.theater.manager.dto.ErrorDto;
import ar.com.up.theater.manager.dto.ShowDto;
import ar.com.up.theater.manager.dto.ShowRegistrationRequest;
import ar.com.up.theater.manager.exception.InvalidArtistException;
import ar.com.up.theater.manager.exception.InvalidShowTypeException;
import ar.com.up.theater.manager.exception.InvalidHallException;
import ar.com.up.theater.manager.exception.TheaterException;
import ar.com.up.theater.manager.mapper.TheaterMapper;
import ar.com.up.theater.manager.model.Artist;
import ar.com.up.theater.manager.model.Hall;
import ar.com.up.theater.manager.model.Show;
import ar.com.up.theater.manager.model.ShowType;
import ar.com.up.theater.manager.repository.ShowRepository;
import ar.com.up.theater.manager.service.ArtistService;
import ar.com.up.theater.manager.service.HallService;
import ar.com.up.theater.manager.service.ShowService;
import ar.com.up.theater.manager.service.ShowTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static ar.com.up.theater.manager.constant.errorCodes.*;

@Service
public class ShowServiceImpl implements ShowService {
    private final ShowRepository showRepository;
    private final HallService hallService;
    private final ShowTypeService showTypeService;
    private final ArtistService artistService;
    private final TheaterMapper theaterMapper;
    Logger logger = LoggerFactory.getLogger(ShowServiceImpl.class);
    public ShowServiceImpl(ShowRepository showRepository, HallService hallService, ShowTypeService showTypeService, ArtistService artistService, TheaterMapper theaterMapper) {
        this.showRepository = showRepository;
        this.hallService = hallService;
        this.showTypeService = showTypeService;
        this.artistService = artistService;
        this.theaterMapper = theaterMapper;
    }

    @Override
    public ApiResponse<List<ShowDto>> getAllShows() {
        try{
            List<ShowDto> shows = showRepository.findAll().stream()
                    .map(theaterMapper::showToDto)
                    .toList();
            return ApiResponse.success(shows);
        }catch (Exception e){
            logger.error(e.getMessage());
            ErrorDto errorDto = new ErrorDto();
            errorDto.setCode("SHW01");
            errorDto.setMessage(e.getMessage());
            return ApiResponse.failure(List.of(errorDto));
        }
    }

    @Override
    public ApiResponse<List<ShowDto>> createShow(ShowRegistrationRequest registrationRequest) {
        ApiResponse<List<ShowDto>> apiResponse = new ApiResponse<>();
        try {
            Show existingShow = showRepository.findByNameIgnoreCase(registrationRequest.getName()).orElse(null);
            if (existingShow != null) {
                throw new TheaterException(INVALID_SHOW_NAME.getCode(), INVALID_SHOW_NAME.getMessage());
            }

            Show newShow = new Show();

            Hall hall = hallService.getHallByType(registrationRequest.getHall());
            if (hall == null) {
                throw new InvalidHallException(INVALID_HALL_ERROR.getCode(), INVALID_HALL_ERROR.getMessage());
            }
            LocalDateTime dateTime = validateDates(hall, registrationRequest);
            newShow.setDate(dateTime);
            newShow.setHall(hall);

            ShowType showType = showTypeService.getShowTypeByName(registrationRequest.getType());
            if (showType == null) {
                throw new InvalidShowTypeException(INVALID_SHOW_TYPE_ERROR.getCode(), INVALID_SHOW_TYPE_ERROR.getMessage());
            }
            newShow.setShowType(showType);
            Set<Artist> newArtists = new HashSet<>();
            registrationRequest.getArtists().forEach(artist -> {
                Artist artistFound = artistService.getArtistByName(artist);
                if (artistFound == null) {
                    throw new InvalidArtistException(INVALID_ARTIST_ERROR.getCode(), INVALID_ARTIST_ERROR.getMessage());
                }
                newArtists.add(artistFound);
            });
            newShow.setArtistsSet(newArtists);
            newShow.setName(registrationRequest.getName());
            newShow.setMinutes(registrationRequest.getDuration());
            newShow.setTicketDefaultPrice(registrationRequest.getPrice());

            showRepository.save(newShow);

            apiResponse = ApiResponse.success(List.of(theaterMapper.showToDto(newShow)));
        }catch (TheaterException e){
            logger.error(e.getMessage());
            ErrorDto errorDto = new ErrorDto();
            errorDto.setCode(e.getCode());
            errorDto.setMessage(e.getMessage());
            return ApiResponse.failure(List.of(errorDto));
        }catch (Exception e){
            logger.error(e.getMessage());
            ErrorDto errorDto = new ErrorDto();
            errorDto.setCode(SHOW_CREATE_ERROR.getCode());
            errorDto.setMessage(e.getMessage());
            return ApiResponse.failure(List.of(errorDto));

        }
        return apiResponse;
    }

    private LocalDateTime validateDates(Hall hall, ShowRegistrationRequest registrationRequest) {
        LocalDateTime newShowDate;
        try {
            DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            DateTimeFormatter tf = DateTimeFormatter.ofPattern("HH:mm:ss");

            LocalDate localDate = LocalDate.parse(registrationRequest.getDate(), df);
            LocalTime localTime = LocalTime.parse(registrationRequest.getHour(), tf);

            newShowDate = LocalDateTime.of(localDate, localTime);
        }catch (Exception e){
            throw  new TheaterException(INVALID_DATE_ERROR.getCode(),"Fecha Invalida.");
        }
        boolean isConflictedDate = hall.getShows().stream().anyMatch(existingShows -> {
            logger.info("espectaculo : {}",existingShows.getName());
            LocalDateTime minorRange = existingShows.getDate().minusMinutes(60);
            LocalDateTime majorRange = existingShows.getDate().plusMinutes(existingShows.getMinutes()).plusMinutes(60);
            logger.info("Fecha Nueva :{}", newShowDate.toString());
            logger.info("Fecha Minor :{}", minorRange.toString());
            logger.info("Fecha Major :{}", majorRange.toString());
            logger.info("conflcito? : {}",newShowDate.isBefore(majorRange) &&
                    newShowDate.plusMinutes(registrationRequest.getDuration()).isAfter(minorRange));
            return newShowDate.isBefore(majorRange) &&
                     newShowDate.plusMinutes(registrationRequest.getDuration()).isAfter(minorRange);
        });

        if (isConflictedDate) {
            throw new TheaterException(INVALID_DATE_ERROR.getCode(),INVALID_DATE_ERROR.getMessage());
        }
        return newShowDate;
    }

    @Override
    public Show getShowByName(String name) {
        return showRepository.findByNameIgnoreCase(name).orElse(null);
    }
}
