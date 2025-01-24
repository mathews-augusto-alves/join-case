package br.com.project.core.entities;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.annotation.processing.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PedidoProduto.class)
public abstract class PedidoProduto_ {

	public static volatile SingularAttribute<PedidoProduto, BigDecimal> preco;
	public static volatile SingularAttribute<PedidoProduto, Produto> produto;
	public static volatile SingularAttribute<PedidoProduto, LocalDateTime> criadoEm;
	public static volatile SingularAttribute<PedidoProduto, LocalDateTime> atualizadoEm;
	public static volatile SingularAttribute<PedidoProduto, Pedido> pedido;
	public static volatile SingularAttribute<PedidoProduto, Long> id;
	public static volatile SingularAttribute<PedidoProduto, Integer> quantidade;

	public static final String PRECO = "preco";
	public static final String PRODUTO = "produto";
	public static final String CRIADO_EM = "criadoEm";
	public static final String ATUALIZADO_EM = "atualizadoEm";
	public static final String PEDIDO = "pedido";
	public static final String ID = "id";
	public static final String QUANTIDADE = "quantidade";

}

