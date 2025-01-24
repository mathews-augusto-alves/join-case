package br.com.app.app_control.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.app.app_control.application.service.pedido.PedidoService;
import br.com.project.core.entities.Pedido;
import br.com.project.core.entities.Usuario;
import br.com.project.core.projection.PedidoProjection;
import br.com.project.core.repository.PedidoRepository;

@ExtendWith(MockitoExtension.class)
public class PedidoServiceTest {

    @Mock
    private PedidoRepository pedidoRepository;

    @InjectMocks
    private PedidoService pedidoService;

    private Pedido pedido;
    private Usuario usuario;

    @BeforeEach
    void setUp() {
        usuario = new Usuario();
        usuario.setId(1L);
        usuario.setEmail("teste@teste.com");

        pedido = new Pedido();
        pedido.setId(1L);
        pedido.setUsuario(usuario);
        pedido.setTotal(new BigDecimal("100.00"));
    }

    @Test
    void createOrUpdate_DeveSalvarPedido() {
        when(pedidoRepository.save(pedido)).thenReturn(pedido);

        Pedido result = pedidoService.createOrUpdate(pedido);

        assertNotNull(result);
        assertEquals(pedido.getId(), result.getId());
        assertEquals(pedido.getTotal(), result.getTotal());
        verify(pedidoRepository).save(pedido);
    }

    @Test
    void findById_QuandoPedidoExiste_DeveRetornarPedido() {
        when(pedidoRepository.findById(1L)).thenReturn(Optional.of(pedido));

        Optional<Pedido> result = pedidoService.findById(1L);

        assertTrue(result.isPresent());
        assertEquals(pedido.getId(), result.get().getId());
        verify(pedidoRepository).findById(1L);
    }

    @Test
    void findById_QuandoPedidoNaoExiste_DeveRetornarEmpty() {
        when(pedidoRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<Pedido> result = pedidoService.findById(1L);

        assertFalse(result.isPresent());
        verify(pedidoRepository).findById(1L);
    }

    @Test
    void findByUsuarioEmail_DeveRetornarPedidosDoUsuario() {
        List<PedidoProjection> pedidos = Arrays.asList(mock(PedidoProjection.class));
        when(pedidoRepository.findByUsuarioEmail("teste@teste.com")).thenReturn(pedidos);

        List<PedidoProjection> result = pedidoService.findByUsuarioEmail("teste@teste.com");

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(pedidoRepository).findByUsuarioEmail("teste@teste.com");
    }
} 