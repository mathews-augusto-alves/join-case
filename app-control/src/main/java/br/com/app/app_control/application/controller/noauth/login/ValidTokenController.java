package br.com.app.app_control.application.controller.noauth.login;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.app.app_control.infrastructure.security.JwtTokenProvider;
@RestController
public class ValidTokenController {
    private final JwtTokenProvider jwtTokenProvider;
    public ValidTokenController(final JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/public/valid-token")
    public ResponseEntity<Boolean> handleRoute(@RequestBody ValidTokenRecord validTokenRecord) {
        return ResponseEntity.ok().body(jwtTokenProvider.validateToken(validTokenRecord.token()));
    }
}

record ValidTokenRecord(String token){}
