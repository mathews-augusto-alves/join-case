package br.com.app.app_control.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import br.com.app.app_control.application.service.produto.ProdutoServiceImpl;
import br.com.project.core.entities.Produto;
import br.com.project.core.repository.ProdutoRepository;

import java.util.Arrays;

@ExtendWith(MockitoExtension.class)
public class ProdutoServiceTest {

    @Mock
    private ProdutoRepository produtoRepository;

    @InjectMocks
    private ProdutoServiceImpl produtoService;

    private Produto produto;
    private Pageable pageable;

    @BeforeEach
    void setUp() {
        produto = new Produto();
        produto.setId(1L);
        produto.setNome("Produto Teste");
        produto.setPreco(new BigDecimal("10.00"));

        pageable = PageRequest.of(0, 10);
    }

    @Test
    void findAll_DeveRetornarPaginaDeProdutos() {
        Page<Produto> page = new PageImpl<>(Arrays.asList(produto));
        when(produtoRepository.findAll(pageable)).thenReturn(page);

        Page<Produto> result = produtoService.findAll(pageable);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals(produto.getId(), result.getContent().get(0).getId());
        verify(produtoRepository).findAll(pageable);
    }

    @Test
    void findById_QuandoProdutoExiste_DeveRetornarProduto() {
        when(produtoRepository.findById(1L)).thenReturn(Optional.of(produto));

        Optional<Produto> result = produtoService.findById(1L);

        assertTrue(result.isPresent());
        assertEquals(produto.getId(), result.get().getId());
        verify(produtoRepository).findById(1L);
    }

    @Test
    void findById_QuandoProdutoNaoExiste_DeveRetornarEmpty() {
        when(produtoRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<Produto> result = produtoService.findById(1L);

        assertFalse(result.isPresent());
        verify(produtoRepository).findById(1L);
    }

    @Test
    void findAllByCategoria_DeveRetornarProdutosDaCategoria() {
        Page<Produto> page = new PageImpl<>(Arrays.asList(produto));
        when(produtoRepository.findByCategoriaId(1L, pageable)).thenReturn(page);

        Page<Produto> result = produtoService.findAllByCategoria(1L, pageable);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals(produto.getId(), result.getContent().get(0).getId());
        verify(produtoRepository).findByCategoriaId(1L, pageable);
    }
} 