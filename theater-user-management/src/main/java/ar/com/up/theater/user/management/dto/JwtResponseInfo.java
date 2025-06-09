package ar.com.up.theater.user.management.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JwtResponseInfo {
    @JsonProperty("token")
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
