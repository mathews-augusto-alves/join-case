package br.com.app.app_control.application.service.usuario;

import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.project.core.entities.Usuario;
import br.com.project.core.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

    private final UsuarioRepository repository;

    public UsuarioServiceImpl(final UsuarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Usuario> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public Usuario saveUsuario(Usuario usuario) {
        return repository.save(usuario);
    }

}
