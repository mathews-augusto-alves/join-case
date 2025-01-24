package br.com.app.app_control.application.service.produto;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.project.core.entities.Produto;

public interface IProdutoService {
    Page<Produto> findAll(Pageable pageable);
    Page<Produto> findAllByCategoria(Long id, Pageable pageable);
    Optional<Produto> findById(Long id);
}
