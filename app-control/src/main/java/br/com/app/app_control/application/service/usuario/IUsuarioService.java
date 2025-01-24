package br.com.app.app_control.application.service.usuario;

import java.util.Optional;

import br.com.project.core.entities.Usuario;

public interface IUsuarioService {
    Optional<Usuario> findByEmail(String email);
    Usuario saveUsuario(Usuario usuario);
}
