package br.com.project.core.entities;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.time.LocalDateTime;
import javax.annotation.processing.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Estoque.class)
public abstract class Estoque_ {

	public static volatile SingularAttribute<Estoque, Produto> produto;
	public static volatile SingularAttribute<Estoque, LocalDateTime> criadoEm;
	public static volatile SingularAttribute<Estoque, LocalDateTime> atualizadoEm;
	public static volatile SingularAttribute<Estoque, Long> id;
	public static volatile SingularAttribute<Estoque, Integer> quantidade;

	public static final String PRODUTO = "produto";
	public static final String CRIADO_EM = "criadoEm";
	public static final String ATUALIZADO_EM = "atualizadoEm";
	public static final String ID = "id";
	public static final String QUANTIDADE = "quantidade";

}

