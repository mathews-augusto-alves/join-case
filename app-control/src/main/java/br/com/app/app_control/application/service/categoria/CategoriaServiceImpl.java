package br.com.app.app_control.application.service.categoria;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.project.core.entities.Categoria;
import br.com.project.core.repository.CategoriaRepository;

@Service
public class CategoriaServiceImpl implements ICategoriaService {

    private final CategoriaRepository repository;

    public CategoriaServiceImpl(final CategoriaRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Categoria> findAll() {
        return repository.findAll();
    }

}
