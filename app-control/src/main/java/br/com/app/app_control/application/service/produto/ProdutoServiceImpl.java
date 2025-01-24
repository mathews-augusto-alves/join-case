package br.com.app.app_control.application.service.produto;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.project.core.entities.Produto;
import br.com.project.core.repository.ProdutoRepository;

@Service
public class ProdutoServiceImpl implements IProdutoService {
    private final ProdutoRepository produtoRepository;

    public ProdutoServiceImpl(final ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Override
    public Page<Produto> findAll(Pageable pageable) {
        return produtoRepository.findAll(pageable);
    }

    @Override
    public Page<Produto> findAllByCategoria(Long id, Pageable pageable) {
        return produtoRepository.findByCategoriaId(id, pageable);
    }

    @Override
    public Optional<Produto> findById(Long id) {
        return produtoRepository.findById(id);
    }
}
