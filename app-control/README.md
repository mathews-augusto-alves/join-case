# App Control

## Sobre o Projeto
App Control é uma aplicação para gerenciamento e controle de aplicações, permitindo monitoramento e administração centralizada.

## Funcionalidades Principais

- Gerenciamento de usuários
- Controle de pedidos
- Gestão de produtos e categorias
- Controle de estoque
- Processamento assíncrono via Kafka
- Sistema de notificações por email

## Tecnologias Utilizadas
- Java 17
- Spring Boot 3.x
- Spring Security
- Spring Data JPA
- PostgreSQL
- Docker
- Docker Compose
- Maven
- Swagger/OpenAPI
- JWT para autenticação
- Apache Kafka (Producer)
- Lombok
- ModelMapper
- Hibernate
- Validation
- JUnit

## Padrões de Projeto e Arquitetura
- #### Clean Architecture
- #### Arquitetura em Camadas - Estrutura do projeto segue a divisão em camadas (Controllers, Services, Repositories)
- #### DTO Pattern - Utilizado em br.com.project.core.dto.PedidoDTO e outros DTOs
- #### Adapter Pattern - Implementado em br.com.app.app_control.infrastructure.adapter.UseCaseHandler para adaptar casos de uso
- #### Factory Pattern - Utilizado na criação de objetos e instâncias de serviços
- #### Dependency Injection - Utilizado em toda aplicação através das anotações do Spring como @Autowired e construtor
- #### SOLID Principles - Aplicado principalmente no princípio de Responsabilidade Única (SRP) nos controllers e use cases

## Pré-requisitos
- Java 17+
- Maven
- Docker e Docker Compose
- PostgreSQL 15+

## Como Executar

### Usando Docker Compose
1. Clone o repositório:
```bash
git clone https://github.com/mathews-augusto-alves/join-case
```
2. Navegue até o diretório do projeto:
```bash
cd app-control
```
4. Execute o Maven para construir o projeto:
```bash
mvn clean install
```
5. Construa a imagem Docker:
```bash
docker build -t app-control . 
```
6. Execute o container Docker:
```bash
docker run -p 8085:8085 app-control
```
7. Acesse a documentação da API através do Swagger:
```bash
http://localhost:8085/app-control-api/public/swagger-ui
```
## Contact

Para mais informações, você pode entrar em contato comigo:

- **LinkedIn**: [Mathews Alves](https://www.linkedin.com/in/mathews-augusto-alves/)
- **Email**: [mathews.alves.job@outlook.com](mailto:mathews.alves.job@outlook.com)
