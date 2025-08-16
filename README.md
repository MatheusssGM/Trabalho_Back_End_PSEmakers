# API-PS - Sistema de Livraria

Esta API é um sistema de gerenciamento de livraria desenvolvido em Java com Spring Boot. Ela permite o cadastro, autenticação e gerenciamento de pessoas (usuários), livros e empréstimos, com autenticação JWT e integração com o ViaCEP para busca de endereços.

## Funcionalidades

- **Autenticação JWT**: Login e registro de usuários.
- **Gerenciamento de Pessoas**: Cadastro, atualização, listagem, alteração de senha e remoção.
- **Gerenciamento de Livros**: Cadastro, atualização, listagem e remoção.
- **Empréstimos**: Realização de empréstimos, devoluções, atualização e remoção.
- **Busca de Endereço**: Integração com ViaCEP para preenchimento automático de endereço pelo CEP.
- **Controle de Acesso**: Perfis de usuário (ADMIN, USER) com permissões distintas.
- **Documentação Swagger**: Interface interativa para testar a API.

## Como Executar

### Com Docker

1. **Clone o repositório**  
   ```sh
   git clone <url-do-repositorio>
   cd Trabalho_Back_End_PSEmakers
   ```

2. **Suba os containers**  
   ```sh
   docker-compose up --build
   ```

3. **Acesse a documentação Swagger**  
   [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

### Localmente (sem Docker)

1. Configure o PostgreSQL conforme `src/main/resources/application.properties`.
2. Execute as migrações Flyway (ocorre automaticamente ao iniciar).
3. Compile e rode:
   ```sh
   ./mvnw spring-boot:run
   ```

## Como utilizar os endpoints

Para utilizar os endpoints protegidos da API, é necessário:

1. **Registrar um novo usuário** utilizando o endpoint `POST /register`.
2. **Realizar o login** utilizando o endpoint `POST /login` para obter um token JWT.
3. **Enviar o token JWT** no cabeçalho das requisições protegidas, utilizando o header:  
   ```
   Authorization: Bearer <seu_token_jwt>
   ```

Sem o token, o acesso aos endpoints protegidos será negado.

## Principais Endpoints

- **Autenticação**
  - `POST /login` - Login e obtenção do token JWT
  - `POST /register` - Registro de novo usuário

- **Pessoa**
  - `GET /pessoa/all` - Lista todas as pessoas
  - `GET /pessoa/{idPessoa}` - Busca pessoa por ID
  - `POST /pessoa/create` - Cria nova pessoa (ADMIN)
  - `PUT /pessoa/update/{idPessoa}` - Atualiza pessoa (ADMIN)
  - `PUT /pessoa/changePassword` - Altera senha do usuário autenticado
  - `DELETE /pessoa/delete/{idPessoa}` - Remove pessoa (ADMIN)

- **Livro**
  - `GET /livro/all` - Lista todos os livros
  - `GET /livro/{idLivro}` - Busca livro por ID
  - `POST /livro/create` - Cria novo livro (ADMIN)
  - `PUT /livro/update/{idLivro}` - Atualiza livro (ADMIN)
  - `DELETE /livro/delete/{idLivro}` - Remove livro (ADMIN)

- **Empréstimo**
  - `GET /emprestimo/all` - Lista todos os empréstimos
  - `GET /emprestimo/{idEmprestimo}` - Busca empréstimo por ID
  - `POST /emprestimo/createEmp` - Realiza empréstimo (ADMIN)
  - `POST /emprestimo/createDev/{idEmprestimo}` - Realiza devolução (ADMIN)
  - `PUT /emprestimo/update/{idEmprestimo}` - Atualiza empréstimo (ADMIN)
  - `DELETE /emprestimo/delete/{idEmprestimo}` - Remove empréstimo (ADMIN)

## Observações

- Para acessar endpoints protegidos, obtenha o token JWT via `login` e envie no header `Authorization: Bearer <token>`.
- Apenas usuários com perfil ADMIN podem cadastrar, atualizar ou remover pessoas, livros e empréstimos.
- O endereço é preenchido automaticamente ao informar um CEP válido.
- A documentação interativa está disponível via Swagger.

## Tecnologias

- Java 17, Spring Boot 3, Spring Security, Spring Data JPA, PostgreSQL, Flyway, OpenFeign, Swagger/OpenAPI, Docker

---