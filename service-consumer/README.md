# Service Consumer

## 📝 Descrição
O Service Consumer é um microserviço Spring Boot responsável pelo processamento assíncrono de pedidos e envio de notificações por email. Ele atua como consumidor de mensagens Kafka, processando pedidos e gerenciando o envio de emails para diferentes provedores.

## 🚀 Funcionalidades Principais

### Tópico de Processamento de Pedidos
- Consumo de mensagens do tópico "processar-pedido"
- Atualização automática do status do pedido para "APROVADO"
- Disparo de notificações por email após aprovação

### Tópicos de envio de Email
- Consumo de mensagens do tópico "processar-email"
- Suporte a múltiplos provedores de email:
  - Gmail
  - Outlook
  - Yahoo
  - ProtonMail
  - iCloud
  - Zoho
- Detecção automática do provedor de email
- Sistema robusto de envio de notificações

## 🛠️ Tecnologias Utilizadas

- Java
- Spring Boot
- Apache Kafka
- PostgreSQL
- Spring Data JPA
- Spring Mail
- Lombok
- Docker

## 📋 Pré-requisitos

- Java 17+
- Maven
- Docker e Docker Compose
- PostgreSQL
- Apache Kafka

## ⚙️ Configuração

#### Build da Aplicação
Antes de iniciar os containers, é necessário gerar o arquivo JAR da aplicação:

```bash
mvn clean package -DskipTests
```

#### Gerenciamento dos Containers

1. **Build da imagem**:
```bash
docker build -t service-consumer .
```

2. **Execução da imagem**:
```bash
docker run -p 9093:9093 service-consumer
```

Isso irá iniciar:
- Service Consumer (porta 9093)

Atenção!
1. Certifique-se que o PostgreSQL está rodando
2. Inicie o servidor Kafka

### Configurações da Aplicação

#### Banco de Dados
```yaml
spring:
  datasource:
    url: jdbc:postgresql://postgres:5432/db_teste_vendas
    username: usuario
    password: senha
```

#### Kafka
```yaml
spring:
  kafka:
    bootstrap-servers: kafka:9092
    consumer:
      group-id: kafka-consumer-group
```

#### Email
```yaml
spring:
  mail:
    username: seu_email@gmail.com
    password: sua_senha
```

### Sem Docker
1. Certifique-se que o PostgreSQL está rodando
2. Inicie o servidor Kafka
3. Execute o comando:
```bash
mvn spring-boot:run
```

## 📦 Estrutura do Projeto

```
service-consumer/
├── src/main/java/
│   └── br/com/app/consumer/
│       ├── app/
│       │   ├── adapter/
│       │   ├── constants/
│       │   ├── consumer/
│       │   ├── enums/
│       │   ├── service/
│       │   └── utils/
│       └── config/
└── src/main/resources/
    ├── application.yml
    └── application-local.yml
```

## 🔄 Fluxo de Processamento

1. **Processamento de Pedidos**:
   - Recebimento da mensagem no tópico "processar-pedido"
   - Atualização do status do pedido
   - Envio de notificação por email

2. **Processamento de Emails**:
   - Recebimento da mensagem no tópico "processar-email"
   - Identificação do provedor de email
   - Configuração automática do provedor
   - Envio do email

## 🔍 Monitoramento

O serviço possui logs detalhados para:
- Processamento de pedidos
- Envio de emails
- Integração com Kafka

## 🔐 Portas

- Aplicação: 9093
- Kafka: 9092
- Zookeeper: 2181
- PostgreSQL: 5432 