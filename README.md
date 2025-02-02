# App Control

## Sobre o Repositório
Este repositório armazena os projetos listados abaixo.

## Projetos
- #### app-control - Backend da aplicação responável pelo CRUD, autenticação, autorização e validações.
- #### lib - projeto que serve como biblioteca de utilizatários pelas demais aplicações java.
- #### project - aplicação front end.
- #### service-consumer - aplicação responsável pela leitura das mensagens enviadas aos tópicos Kafka.

## ⚙️ Configuração

### Instruções para Execução

Existem duas maneiras de executar os projetos. Siga as opções abaixo conforme sua preferência:

#### **Opção 1: Execução Manual**
1. Navegue até o diretório de cada projeto e execute os seguintes comandos no terminal, na ordem:

    - **Para o projeto `lib`**:
      ```bash
      cd ./lib && mvn clean install
      ```

    - **Para o projeto `app-control`**:
      ```bash
      cd ./app-control && mvn clean install
      ```

    - **Para o projeto `service-consumer`**:
      ```bash
      cd ./service-consumer && mvn clean install
      ```

    - **Para o projeto `project`**:
      ```bash
      cd ./project && npm ci
      ```

    Esses comandos são necessários para instalar todas as dependências dos projetos.

2. Após instalar as dependências, execute o seguinte comando na raiz do repositório para iniciar os containers:

    ```bash
    docker-compose up --build -d
    ```

#### **Opção 2: Execução Automática**
- Se preferir automatizar o processo, execute o script `build.sh` localizado na raiz do repositório. Ele irá realizar todas as etapas descritas na Opção 1 de forma automática.
- Caso você esteja executando no codespace do git, certifique-se que a versão do java instalada seja a 17 ou superior. Caso seja inferior execute o seguinte comando:

  ```bash
    sdk install java 17.0.9-amzn
  ```
- Caso você esteja executando no codespace do git e ocorra erro ao acessar o front, provavelmente você precisará expor as portas das principais aplicações, execute os comandos:

  ```bash
    gh codespace ports visibility 8085:public -c $CODESPACE_NAME
  ```

  ```bash
    gh codespace ports visibility 3000:public -c $CODESPACE_NAME
  ```

## Contact

Para mais informações ou dúvidas, você pode entrar em contato comigo:

- **LinkedIn**: [Mathews Alves](https://www.linkedin.com/in/mathews-augusto-alves/)
- **Email**: [mathews.alves.job@outlook.com](mailto:mathews.alves.job@outlook.com)
