package ar.com.up.theater.manager.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Wrapper genérico para respuestas de servicios y controladores.
 * @param <T> Tipo de dato que se devuelve en 'data'.
 */
public class ApiResponse<T> {
    private T data;
    private List<ErrorDto> errors = new ArrayList<>();


    public ApiResponse() { }

    private ApiResponse(T data, List<ErrorDto> errors) {
        this.data   = data;
        this.errors = errors;
    }

    public T getData() {
        return data;
    }

    public List<ErrorDto> getErrors() {
        return errors;
    }

    public static <T> ApiResponse<T> success(T data) {
        // data no nulo, errors vacío
        return new ApiResponse<>(data, Collections.emptyList());
    }

    public static <T> ApiResponse<T> failure(List<ErrorDto> errors) {
        // data nulo, errors con mensajes
        return new ApiResponse<>(null, errors);
    }
}
