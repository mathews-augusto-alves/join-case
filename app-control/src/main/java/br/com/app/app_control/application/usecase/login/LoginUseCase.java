package br.com.app.app_control.application.usecase.login;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.app.app_control.application.service.usuario.IUsuarioService;
import br.com.app.app_control.domain.dto.LoginDTO;
import br.com.app.app_control.infrastructure.contract.IExecution;
import br.com.app.app_control.infrastructure.dto.AuthenticationDTO;
import br.com.app.app_control.infrastructure.exception.BusinessException;
import br.com.app.app_control.infrastructure.record.ResponseRecord;
import br.com.app.app_control.infrastructure.security.JwtTokenProvider;
import br.com.project.core.entities.Usuario;

@Service
public class LoginUseCase implements IExecution<LoginDTO> {

    private final IUsuarioService usuarioService;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public LoginUseCase(final IUsuarioService usuarioService,
            final PasswordEncoder passwordEncoder,
            final JwtTokenProvider jwtTokenProvider) {
        this.usuarioService = usuarioService;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public ResponseRecord execute(LoginDTO request) {
        Usuario usuario = usuarioService.findByEmail(request.getEmail())
                .orElseThrow(() -> new BusinessException("Email ou senha incorretos."));

        if (!passwordEncoder.matches(request.getSenha(), usuario.getSenha())) {
            throw new BusinessException("Email ou senha incorretos.");
        }

        String token = jwtTokenProvider.createToken(usuario.getNome(), usuario.getEmail(), usuario.getRole());

        return new ResponseRecord(new AuthenticationDTO(token));
    }

}
