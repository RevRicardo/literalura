# Literalura - Challenge ONE ALURA - GutendexAPI
Projeto desenvolvido em Java com Spring Boot, criado como parte do 
Challenge ONE ALURA, com o objetivo de buscar informações sobre livros 
e autores em uma API externa (Gutendex API) e persistir esses dados em 
um banco de dados PostgreSQL para consultas futuras, evitando requisições
desnecessárias à API.

## Funcionalidades
A aplicação oferece um menu interativo no console com as seguintes opções:

Buscar livro pelo título: Realiza uma requisição à Gutendex API, busca o livro pelo título fornecido e, se encontrado, salva o livro e seu(s) autor(es) no banco de dados.
Listar livros registrados:
Listar todos os autores:
Listar autor por nome:
Listar autores por ano de nascimento:
Listar autores por ano de falecimento:
Listar autores vivos até o ano digitado:
Listar livros por um trecho do título:
Listar livros por um autor:
Listar livros em um determinado idioma:
TOP 10 de livros mais baixados:

## Tecnologias Utilizadas
Java 17: Linguagem de programação.
Spring Boot 3.5.0: Framework para desenvolvimento rápido de aplicações Java.
Spring Data JPA: Facilita a interação com o banco de dados usando o padrão JPA e Hibernate.
PostgreSQL: Banco de dados relacional para persistência dos dados.
Maven: Gerenciador de dependências e automação de build.
Jackson: Biblioteca para serialização/desserialização de JSON.
Gutendex API: API pública para buscar informações sobre livros (sem autenticação).
Como Configurar e Rodar
Siga os passos abaixo para configurar e executar o projeto em sua máquina.

## Pré-requisitos
JDK 17 ou superior instalado.
Apache Maven instalado.
PostgreSQL instalado e rodando.

1. Clonar o Repositório
git clone https://github.com/RevRicardo/literalura.git
cd literalura
2. Configurar o Banco de Dados PostgreSQL
   Crie um novo banco de dados no PostgreSQL (por exemplo, literalura_db).
   
As tabelas serão criadas automaticamente pelo Spring Data JPA
quando a aplicação for iniciada pela primeira vez, com base nas entidades
Autor e Livro.

3. Configurar application.properties
   No arquivo src/main/resources/application.properties.

spring.datasource.url=jdbc:postgresql://${DB_HOST}:5432/${DB_NAME}
spring.datasource.username=${DB_USER}
spring.datasource.password=${DB_PASSWORD}
spring.datasource.driver-class-name=org.postgresql.Driver

Aturaliza suas variaveis de sistema no Windows para acessar o banco de dados.
${DB_HOST}: Endereço do seu servidor PostgreSQL (geralmente localhost).
${DB_NAME}: Nome do banco de dados que você criou (ex: literalura_db).
${DB_USER}: Seu nome de usuário do PostgreSQL.
${DB_PASSWORD}: Sua senha do PostgreSQL.

4. Construir o Projeto
   Navegue até o diretório raiz do projeto no seu terminal e compile o projeto usando Maven:

Bash

mvn clean install

5. Executar a Aplicação
   Você pode rodar a aplicação Spring Boot diretamente do terminal:

Bash

mvn spring-boot:run
Ou, se estiver usando uma IDE como IntelliJ IDEA:

Importe o projeto Maven.
Execute a classe principal LiteraluraApplication.java (botão direito -> Run).
A aplicação iniciará e exibirá o menu no console, onde você poderá interagir com as funcionalidades.
