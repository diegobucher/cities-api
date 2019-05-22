## Aplicação JSF realizada em PrimeFaces, PostgreSQL, JPA, Hibernate

## Tutorial


## Passos para Instalação

**1. Clone o repositório**

```bash
git clone https://github.com/diegobucher/cities_api.git
```

**2. Configure o PostgreSQL**

Primeiro, crie um banco de dados chamado `cities`. Em seguida, abra o arquivo `src/main/resources/hibernate.cfg.xml` e altere o nome de usuário e a senha do datasource de acordo com a instalação do PostgreSQL.

**3. Execute o aplicativo**

```bash
mvn clean package
java -jar target/cities-api-0.0.1-SNAPSHOT.jar
```