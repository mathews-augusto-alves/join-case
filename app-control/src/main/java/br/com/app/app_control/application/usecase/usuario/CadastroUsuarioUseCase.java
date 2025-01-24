package br.com.app.app_control.application.usecase.usuario;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.app.app_control.application.service.usuario.IUsuarioService;
import br.com.app.app_control.infrastructure.contract.IExecution;
import br.com.app.app_control.infrastructure.exception.BusinessException;
import br.com.app.app_control.infrastructure.record.ResponseRecord;
import br.com.project.core.dto.UsuarioDTO;
import br.com.project.core.entities.Usuario;

@Service
public class CadastroUsuarioUseCase implements IExecution<UsuarioDTO> {

    private static final Logger logger = LoggerFactory.getLogger(CadastroUsuarioUseCase.class);

    private final IUsuarioService usuarioService;
    private final PasswordEncoder passwordEncoder;

    public CadastroUsuarioUseCase(IUsuarioService usuarioService,
            PasswordEncoder passwordEncoder
            ) {
        this.usuarioService = usuarioService;
        this.passwordEncoder = passwordEncoder;;
    }

    @Override
    public ResponseRecord execute(UsuarioDTO request) {
        if (usuarioService.findByEmail(request.getEmail()).isPresent()) {
            logger.warn("Attempt to register with already taken username: {}", request.getEmail());
            throw new BusinessException("Email já cadastrado.");
        }

        Usuario usuario = createUsuarioFromRequest(request);
        usuarioService.saveUsuario(usuario);

        logger.info("User registered successfully: {}", request.getEmail());

        return new ResponseRecord("Cadastro concluído.", "Você receberá um email com a confirmação");
    }

    private Usuario createUsuarioFromRequest(UsuarioDTO request) {
        Usuario user = Usuario.builder()
        .nome(request.getNome())
        .email(request.getEmail())
        .senha(passwordEncoder.encode(request.getSenha()))
        .role("CLIENTE")
        .criadoEm(LocalDateTime.now())
        .atualizadoEm(LocalDateTime.now())
        .build();

        return user;
    }
}
