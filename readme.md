# Carteira de Investimentos

API REST para gerenciamento de uma carteira de investimentos, permitindo cadastro, consulta, atualização, exclusão e cálculo de lucro/prejuízo de ativos como ações e criptomoedas.

## Tecnologias Utilizadas

- Java 17+
- Spring Boot
- Maven
- JPA/Hibernate (SQL)
- Lombok

## Funcionalidades

- Cadastro de investimentos (ações e criptomoedas)
- Consulta de todos os investimentos ou filtrados por tipo
- Atualização de quantidade e preço de compra de ativos
- Cálculo de lucro ou prejuízo na venda de ativos
- Exclusão de investimentos
- Integração com API externa para cotação de ativos

## Endpoints Principais

Todos os endpoints estão sob o path `/investments`.

| Método | Endpoint                 | Descrição                                 |
| ------ | ------------------------ | ----------------------------------------- |
| GET    | /investments/summary     | Resumo dos investimentos                  |
| POST   | /investments             | Cadastrar novo investimento               |
| GET    | /investments             | Listar todos os investimentos             |
| GET    | /investments/type={type} | Listar investimentos por tipo             |
| DELETE | /investments/id={id}     | Excluir investimento por ID               |
| PUT    | /investments             | Atualizar investimento                    |
| PUT    | /investments/sale        | Registrar venda e calcular lucro/prejuízo |


## Estrutura do Projeto

```
carteiraDeinvestimentos/
├── src/
│   ├── main/
│   │   ├── java/br/edu/ufop/web/carteira/investimentos/carteiraDeinvestimentos/
│   │   │   ├── controller/        # Controllers REST (endpoints)
│   │   │   ├── converter/         # Conversores de dados
│   │   │   ├── domain/            # Lógica de negócio
│   │   │   ├── dtos/              # Data Transfer Objects
│   │   │   ├── enums/             # Enumerações de tipos
│   │   │   ├── exception/         # Tratamento de exceções
│   │   │   ├── models/            # Modelos de entidades
│   │   │   ├── repositories/      # Interfaces de persistência
│   │   │   ├── service/           # Serviços de negócio
│   │   │   └── CarteiraDeinvestimentosApplication.java # Classe principal
│   │   └── resources/
│   │       ├── application.properties # Configurações
│   │       ├── static/               # Arquivos estáticos
│   │       └── templates/            # Templates para views
│   └── test/
│       └── java/br/edu/ufop/web/carteira/investimentos/carteiraDeinvestimentos/
│           └── CarteiraDeinvestimentosApplicationTests.java # Testes automatizados
├── pom.xml
├── mvnw / mvnw.cmd
└── docker-compose-dev.yaml
```

## Como Executar

1. Clone o repositório:
   ```bash
   git clone https://github.com/diasbruno00/TP3-web2.git
   ```
2. Acesse a pasta do projeto:
   ```bash
   cd carteiraDeinvestimentos
   ```
3. Configure o banco de dados em `src/main/resources/application.properties`.
4. Compile o projeto:
   ```bash
   mvn clean install
   ```
5. Execute a aplicação:
   ```bash
   mvn spring-boot:run
   ```

## Testes

Para rodar os testes automatizados:

```bash
mvn test
```

## Docker

Para executar o projeto com Docker Compose:

```bash
docker-compose -f docker-compose-dev.yaml up
```

## Autor

Bruno Dias
