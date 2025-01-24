package br.com.app.app_control.infrastructure.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationDTO {
    private final String jwt;

    public AuthenticationDTO(String jwt) {
        this.jwt = jwt;
    }
}
