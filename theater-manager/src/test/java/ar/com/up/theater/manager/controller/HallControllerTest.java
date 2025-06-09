package ar.com.up.theater.manager.controller;

import ar.com.up.theater.manager.dto.ApiResponse;
import ar.com.up.theater.manager.dto.HallDto;
import ar.com.up.theater.manager.model.Hall;
import ar.com.up.theater.manager.service.HallService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HallControllerTest {

    @Mock
    private HallService hallService;

    @InjectMocks
    private HallController hallController;

    @Test
    @DisplayName("getAllHalls() -> Devuelve 200 con las salas")
    void GET_Halls_shouldReturn200(){

        HallDto dto = new HallDto(1, "Sala Prueba", 80, "Plateas");
        ApiResponse<List<HallDto>> apiResponse = ApiResponse.success(List.of(dto));
        when(hallService.getAllHalls()).thenReturn(apiResponse);

        ResponseEntity<ApiResponse<List<HallDto>>> response = hallController.getAll();

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertFalse(CollectionUtils.isEmpty(response.getBody().getData()));

        verify(hallService).getAllHalls();
    }

}