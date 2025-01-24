package br.com.project.core.entities;

import jakarta.persistence.metamodel.ListAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.annotation.processing.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Pedido.class)
public abstract class Pedido_ {

	public static volatile SingularAttribute<Pedido, BigDecimal> total;
	public static volatile ListAttribute<Pedido, PedidoProduto> pedidoProduto;
	public static volatile SingularAttribute<Pedido, LocalDateTime> criadoEm;
	public static volatile SingularAttribute<Pedido, LocalDateTime> atualizadoEm;
	public static volatile SingularAttribute<Pedido, Usuario> usuario;
	public static volatile SingularAttribute<Pedido, Long> id;
	public static volatile SingularAttribute<Pedido, StatusPedido> status;

	public static final String TOTAL = "total";
	public static final String PEDIDO_PRODUTO = "pedidoProduto";
	public static final String CRIADO_EM = "criadoEm";
	public static final String ATUALIZADO_EM = "atualizadoEm";
	public static final String USUARIO = "usuario";
	public static final String ID = "id";
	public static final String STATUS = "status";

}

