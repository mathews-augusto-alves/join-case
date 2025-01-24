package br.com.project.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.project.core.entities.Pedido;
import br.com.project.core.projection.PedidoProjection;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    List<PedidoProjection> findByUsuarioEmail(String id);
}
