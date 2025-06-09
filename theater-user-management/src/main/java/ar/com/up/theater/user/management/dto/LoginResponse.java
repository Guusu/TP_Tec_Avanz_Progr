package ar.com.up.theater.user.management.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class LoginResponse {
    @JsonProperty("data")
    List<JwtResponseInfo> data;
    @JsonProperty("errors")
    List<ErrorDto> error;

    public List<JwtResponseInfo> getData() {
        return data;
    }

    public void setData(List<JwtResponseInfo> data) {
        this.data = data;
    }

    public List<ErrorDto> getError() {
        return error;
    }

    public void setError(List<ErrorDto> error) {
        this.error = error;
    }
}
