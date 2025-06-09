package ar.com.up.theater.user.management.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class RegistrationResponse {
    @JsonProperty("data")
    List<UserRegistrationData> Data;
    @JsonProperty("errors")
    List<ErrorDto> errors;

    public List<UserRegistrationData> getData() {
        return Data;
    }

    public void setData(List<UserRegistrationData> data) {
        Data = data;
    }

    public List<ErrorDto> getErrors() {
        return errors;
    }

    public void setErrors(List<ErrorDto> errors) {
        this.errors = errors;
    }
}
