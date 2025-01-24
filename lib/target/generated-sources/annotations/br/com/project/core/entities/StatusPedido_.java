package br.com.project.core.entities;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import javax.annotation.processing.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(StatusPedido.class)
public abstract class StatusPedido_ {

	public static volatile SingularAttribute<StatusPedido, String> nome;
	public static volatile SingularAttribute<StatusPedido, Long> id;
	public static volatile SingularAttribute<StatusPedido, String> descricao;

	public static final String NOME = "nome";
	public static final String ID = "id";
	public static final String DESCRICAO = "descricao";

}

