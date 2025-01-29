# Lib - Biblioteca Core do Sistema

Este projeto é uma biblioteca compartilhada (core) que contém as entidades, DTOs, repositórios e constantes comuns utilizadas por outros serviços do sistema de e-commerce.

## 📋 Estrutura do Projeto

### Entidades (entities)
- **Usuario**
- **Pedido**
- **PedidoProduto**
- **Produto**
- **Categoria**
- **Estoque**
- **StatusPedido**

### DTOs (Data Transfer Objects)
- **PedidoDTO**: Transferência de dados de pedidos
- **ProdutoDTO**: Transferência de dados de produtos
- **CategoriaDTO**: Transferência de dados de categorias
- **EstoqueDTO**: Transferência de dados de estoque
- **EnderecoDTO**: Transferência de dados de endereços
- **EnviarEmail**: DTO para envio de emails

### Repositórios
- **PedidoRepository**: Operações de banco para pedidos
- **ProdutoRepository**: Operações de banco para produtos
- **CategoriaRepository**: Operações de banco para categorias
- **UsuarioRepository**: Operações de banco para usuários
- **StatusPedidoRepository**: Operações de banco para status de pedidos

### Constantes
Define tópicos Kafka para processamento assíncrono:
- `EMAIL_TOPIC`: "processar-email"
- `PROCESSAR_PEDIDO_TOPIC`: "processar-pedido"

## 🛠️ Tecnologias Utilizadas

- **Spring Boot**: Framework base
- **Spring Data JPA**: Persistência e acesso a dados
- **Lombok**: Redução de código boilerplate
- **Hibernate**: ORM para mapeamento objeto-relacional

## 📦 Dependências

Para utilizar esta biblioteca, adicione a seguinte dependência ao seu `pom.xml`:

```xml
<dependency>
    <groupId>br.com.project</groupId>
    <artifactId>lib</artifactId>
    <version>${project.version}</version>
</dependency>
```

## 🚀 Como Usar

1. Execute o comando `mvn clean install`
2. Importe a biblioteca no seu projeto
3. Configure as propriedades necessárias no `application.yml`
4. Utilize as entidades e repositórios conforme necessário

## 📝 Observações

- Esta biblioteca é parte de um sistema maior de e-commerce
- Segue princípios de Clean Architecture
- Utiliza padrões de projeto para melhor manutenibilidade