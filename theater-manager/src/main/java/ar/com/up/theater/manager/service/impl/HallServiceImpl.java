package ar.com.up.theater.manager.service.impl;

import ar.com.up.theater.manager.dto.ApiResponse;
import ar.com.up.theater.manager.dto.ErrorDto;
import ar.com.up.theater.manager.dto.HallDto;
import ar.com.up.theater.manager.mapper.TheaterMapper;
import ar.com.up.theater.manager.model.Hall;
import ar.com.up.theater.manager.repository.HallRepository;
import ar.com.up.theater.manager.service.HallService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HallServiceImpl implements HallService {
    private final HallRepository hallRepository;
    private final TheaterMapper theaterMapper;
    Logger logger = LoggerFactory.getLogger(HallServiceImpl.class);

    public HallServiceImpl(HallRepository hallRepository, TheaterMapper theaterMapper) {
        this.hallRepository = hallRepository;
        this.theaterMapper = theaterMapper;
    }

    @Override
    public ApiResponse<List<HallDto>> getAllHalls() {

        try{
            List<HallDto> halls = hallRepository.findAll().stream()
                    .map(theaterMapper::hallToDto)
                    .toList();
            return ApiResponse.success(halls);
        }catch (Exception e){
            logger.error(e.getMessage());
            ErrorDto errorDto = new ErrorDto();
            errorDto.setCode("HLL01");
            errorDto.setMessage(e.getMessage());
            return ApiResponse.failure(List.of(errorDto));
        }
    }

    @Override
    public Hall getHallByType(String hallType) {
        return hallRepository.findByTypeIgnoreCase(hallType).orElse(null);
    }
}
