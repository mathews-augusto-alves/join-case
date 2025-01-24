package br.com.app.app_control.application.service.categoria;

import java.util.List;

import br.com.project.core.entities.Categoria;

public interface ICategoriaService {
    List<Categoria> findAll();
}
