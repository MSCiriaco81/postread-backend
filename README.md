# PostRead Backend

Backend da plataforma PostRead: uma API REST para rede social de leitores com autenticação JWT, registro de leituras, feed de atividades, conexões sociais e desafios de consistência (streaks).

## Sobre o Projeto

O PostRead combina conceitos de:

- Rede social de leitura (compartilhamento e conexão entre leitores)
- Gamificação por consistência (streak diária individual e em grupo)

No backend, isso se traduz em regras de negócio para:

- Cadastro, autenticação e proteção de rotas
- Registro de sessões de leitura
- Catálogo e busca de livros
- Feed social de atividades recentes
- Solicitações e gestão de amizades
- Streaks com check-in e acompanhamento de progresso

## Objetivo

Entregar uma base sólida, organizada e escalável para sustentar a experiência social de leitura, com foco em:

- Segurança stateless com JWT
- Organização em camadas (controller, service, repository)
- Testabilidade das regras críticas
- Evolução contínua de funcionalidades sociais e de gamificação

## Arquitetura

Arquitetura em camadas:

- Controller: endpoints REST e contrato HTTP
- Service: regras de negócio e orquestração entre módulos
- Repository: persistência no MongoDB
- Shared/Security: autenticação, autorização, filtros e infraestrutura comum
- Shared/Exception: tratamento global de erros com respostas padronizadas

Essa estrutura facilita manutenção, evolução incremental e clareza de responsabilidades.

## Módulos Funcionais

- Auth: registro e login com emissão de token
- User: perfil e atualização de dados do usuário
- Book: cadastro, consulta e busca de livros
- Reading: registro e histórico de leituras
- Feed: eventos de atividade da rede
- Social: amizades (solicitar, aceitar, rejeitar, listar)
- Streak: desafios e consistência diária com check-in
- Shared: segurança JWT, configurações e exceptions

Documentação detalhada por módulo:

- [src/main/java/com/postread/README.md](src/main/java/com/postread/README.md)
- [src/main/java/com/postread/auth/README.md](src/main/java/com/postread/auth/README.md)
- [src/main/java/com/postread/user/README.md](src/main/java/com/postread/user/README.md)
- [src/main/java/com/postread/book/README.md](src/main/java/com/postread/book/README.md)
- [src/main/java/com/postread/reading/README.md](src/main/java/com/postread/reading/README.md)
- [src/main/java/com/postread/feed/README.md](src/main/java/com/postread/feed/README.md)
- [src/main/java/com/postread/social/README.md](src/main/java/com/postread/social/README.md)
- [src/main/java/com/postread/streak/README.md](src/main/java/com/postread/streak/README.md)
- [src/main/java/com/postread/shared/README.md](src/main/java/com/postread/shared/README.md)

## Segurança

Implementação baseada em Spring Security + JWT:

- Login e registro no módulo de autenticação
- Geração e validação de token JWT
- Filtro de autenticação para rotas protegidas
- Modelo stateless (sem sessão no servidor)

## Persistência de Dados

- Banco: MongoDB
- Driver e integração: Spring Data MongoDB
- Configuração por variáveis de ambiente com fallback local

Exemplo atual de configuração principal:

- Mongo URI padrão: `mongodb://localhost:27017/postread`
- Database padrão: `postread`

Referências:

- [src/main/resources/application.yml](src/main/resources/application.yml)
- [src/main/resources/README.md](src/main/resources/README.md)

## Tecnologias Utilizadas

- Java 21
- Spring Boot 3.3.4
- Spring Web
- Spring Validation
- Spring Security
- Spring Data MongoDB
- JWT (jjwt 0.12.6)
- Lombok
- Springdoc OpenAPI (Swagger)
- Maven

Referência de dependências:

- [pom.xml](pom.xml)

## Execução Local

Pré-requisitos:

- Java 21
- Maven 3.9+
- MongoDB em execução

Variáveis de ambiente suportadas:

- `MONGODB_URI`
- `MONGODB_DATABASE`
- `JWT_SECRET`
- `JWT_EXPIRATION_MS`

Comandos:

```bash
# instalar dependências e compilar
mvn clean compile

# subir aplicação
mvn spring-boot:run
```

Aplicação e documentação:

- API: `http://localhost:8080`
- Swagger UI: `http://localhost:8080/swagger-ui.html`
- OpenAPI JSON: `http://localhost:8080/api-docs`

## Testes

O projeto possui testes de:

- Camada HTTP (controllers)
- Camada de negócio (services)
- Segurança JWT
- Tratamento global de exceções
- Smoke test de contexto Spring

Estado atual da suíte documentada:

- 14 classes de teste
- 75 métodos de teste

Comandos:

```bash
# executar toda a suíte
mvn clean test

# executar classe específica
mvn -Dtest=AuthServiceTest test
```

Referências de testes:

- [src/test/README.md](src/test/README.md)
- [src/test/java/com/postread/README.md](src/test/java/com/postread/README.md)

## Integração com Frontend

Este backend foi desenhado para integração com SPA (React), fornecendo endpoints REST para autenticação, dados de leitura, interação social e gamificação.

## Evolução

A base atual permite evoluções como:

- Novas mecânicas de gamificação
- Expansão de interações sociais
- Maior cobertura de testes de integração ponta a ponta
- Observabilidade e métricas para produção

## Diferencial

O diferencial do PostRead está na convergência de três pilares:

- Leitura
- Rede social
- Gamificação por consistência

Isso cria uma experiência onde o usuário não apenas registra leitura, mas também mantém engajamento por metas, progresso e interação com outros leitores.
