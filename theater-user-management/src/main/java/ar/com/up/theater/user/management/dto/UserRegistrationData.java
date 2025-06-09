package ar.com.up.theater.user.management.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserRegistrationData {
    @JsonProperty("usuario")
    private String username;
    @JsonProperty("nombre")
    private String name;
    @JsonProperty("email")
    private String email;
    @JsonProperty("mensaje")
    private String message;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
