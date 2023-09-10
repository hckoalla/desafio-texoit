# Desafio TexoIT

Esse projeto é uma API RESTful que faz a leitura da lista de indicados e vencedores da categoria Pior Filme do Golden Raspberry Awards.

### Pré-requisitos

Um ambiente de desenvolvimento com: 

- Java;
- Maven;
- E uma IDE de sua preferencia (durante o desenvolvimento foi utilizado Eclipse).

PS: Como o banco de dados utilizado é um banco de dados embarcado, não é necessario nenhuma outra instalação para executar a aplicação.

### Instalação

Para o utilizar a aplicação, é necessario:

- Baixar o projeto e importar na sua IDE como um projeto Maven;
- Baixar as dependencias usando o maven:

```
mvn install
```

- Executar a classe Application.java (localizada em com.desafiotextoit) para iniciar a aplicação com SpringBoot.

Existem 2 maneiras de voce verificar se a aplicação iniciou com sucesso:

A) Acessar o console do H2, atraves do link: 

```
http://localhost:9999/h2-console
```
Detalhes de login no arquivo application.properties

```
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
```

B) Fazer uma chamada atraves do Postman, chamando a URL abaixo com um metodo GET. 

```
http://localhost:9999/producers/winner-interval
```

Voce deverá obter um resultado semelhante a esse:

```
{
    "min": [
        {
            "producer": "Joel Silver",
            "interval": 1,
            "previousWin": 1990,
            "followingWin": 1991
        }
    ],
    "max": [
        {
            "producer": "Matthew Vaughn",
            "interval": 13,
            "previousWin": 2002,
            "followingWin": 2015
        }
    ]
}
```

## Testes

Todas as classes de teste de integração, estão armazenadas em com.desafiotextoit.test, uma para cada entidade envolvida. Nos testes, foram verificados:

A) Filmes: quantidade de registros e quantidade de vencedores;

B) Produtores: quantidade de registros e se existe algum registro com o nome em branco;

C) Estudios: quantidade de registros e se existe algum registro com o nome em branco.