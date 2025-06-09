package ar.com.up.theater.manager.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorDto {
    @JsonProperty("codigo")
    private String code;
    @JsonProperty("mensaje")
    private String message;

    public ErrorDto() {
    }

    public ErrorDto(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
