# postread

## 📌 Propósito

Servir como guia mestre da suíte de testes do backend, documentando cobertura por módulo, cenários validados e comandos de execução.

---

## 📂 Arquivos e Responsabilidades

* [PostreadApplicationTest.java](PostreadApplicationTest.java) → Smoke test de inicialização do contexto Spring.
* [auth/README.md](auth/README.md) → Testes de autenticação (controller e service).
* [book/README.md](book/README.md) → Testes de catálogo de livros.
* [feed/README.md](feed/README.md) → Testes de feed social.
* [reading/README.md](reading/README.md) → Testes de histórico de leitura e regras associadas.
* [shared/README.md](shared/README.md) → Testes de tratamento de exceção e segurança JWT.
* [social/README.md](social/README.md) → Testes de conexões sociais e amizades.
* [streak/README.md](streak/README.md) → Testes de streaks e check-ins.
* [user/README.md](user/README.md) → Testes de serviço de usuário.

---

## 🔧 Funções / Métodos Principais

### Visão Geral da Suíte

* Classes de teste: 14
* Métodos de teste: 75
* Escopo: unit tests, service tests, web layer tests e smoke test de contexto.

---

### Cobertura por Classe de Teste

1. [PostreadApplicationTest.java](PostreadApplicationTest.java)
* Contexto Spring sobe sem erros.

2. [auth/controller/AuthControllerTest.java](auth/controller/AuthControllerTest.java)
* Registro com payload válido retorna 201 e token.
* Validação de username curto e email inválido retorna 400.
* Conflito de cadastro retorna 409.
* Login válido retorna 200.
* Login com campos vazios retorna 400.

3. [auth/service/AuthServiceTest.java](auth/service/AuthServiceTest.java)
* Cadastro com sucesso gera token e persiste usuário.
* Conflitos por email/username geram exceção de domínio.
* Login válido retorna token.
* Credenciais inválidas propagam BadCredentialsException.

4. [book/controller/BookControllerTest.java](book/controller/BookControllerTest.java)
* Criação de livro retorna 201.
* Validação de título vazio retorna 400.
* Busca por ID retorna 200 ou 404 conforme existência.
* Busca por query retorna página de resultados.

5. [book/service/BookServiceTest.java](book/service/BookServiceTest.java)
* Persistência de novo livro.
* Busca por ID com sucesso e cenário de não encontrado.
* Busca textual e fallback para listagem completa.

6. [feed/service/FeedServiceTest.java](feed/service/FeedServiceTest.java)
* Criação de evento no feed.
* Retorno paginado com e sem eventos.

7. [reading/controller/ReadingControllerTest.java](reading/controller/ReadingControllerTest.java)
* Log de leitura válido retorna 201.
* Validação de request inválido retorna 400.
* Histórico paginado retorna 200.
* Exclusão retorna 204.
* Acesso sem autenticação retorna 401 no endpoint protegido.

8. [reading/service/ReadingServiceTest.java](reading/service/ReadingServiceTest.java)
* Registro de leitura salva entrada e dispara integrações (streak/feed).
* Data padrão aplicada quando não informada.
* Data informada respeitada quando presente.
* Listagem paginada de leituras do usuário.
* Exclusão autorizada e bloqueio de exclusão por não proprietário.
* Busca por entrada inexistente retorna ResourceNotFoundException.

9. [shared/exception/GlobalExceptionHandlerTest.java](shared/exception/GlobalExceptionHandlerTest.java)
* Mapeamento correto para 404, 400, 409, 401, 403 e 500.
* Resposta de validação inclui mapa de fieldErrors.

10. [shared/security/JwtServiceTest.java](shared/security/JwtServiceTest.java)
* Geração de token válido.
* Extração de subject (email).
* Validação positiva, usuário incorreto e token expirado.

11. [social/controller/SocialControllerTest.java](social/controller/SocialControllerTest.java)
* Envio de solicitação de amizade com retorno 201.
* Conflito de amizade existente retorna 409.
* Aceite de amizade retorna status ACCEPTED.
* Listagem de amigos retorna dados.
* Acesso sem autenticação retorna 401.

12. [social/service/SocialServiceTest.java](social/service/SocialServiceTest.java)
* Criação de solicitação PENDING.
* Bloqueio de autoamizade e duplicidade.
* Regras de aceite (receiver obrigatório, status pendente).
* Resolução de lista de amigos e friendIds em ambas direções.

13. [streak/service/StreakServiceTest.java](streak/service/StreakServiceTest.java)
* Criação de streak inclui creator sem duplicidade.
* Check-in para participante válido.
* Bloqueio para não participante.
* Processamento de leitura em todas streaks ativas.
* Incremento de streak quando grupo completa o dia.
* Tratamento de streak inexistente.

14. [user/service/UserServiceTest.java](user/service/UserServiceTest.java)
* Consulta de perfil por ID e username.
* Cenário de usuário inexistente.
* Atualização de bio/foto.
* Não sobrescrita de campos nulos.

---

## ▶️ Exemplos de Uso

```java
// Executar todos os testes do backend:
// mvn clean test
//
// Executar uma classe específica (exemplo):
// mvn -Dtest=AuthServiceTest test
```

---

## 🔗 Dependências

* [auth/README.md](auth/README.md)
* [book/README.md](book/README.md)
* [feed/README.md](feed/README.md)
* [reading/README.md](reading/README.md)
* [shared/README.md](shared/README.md)
* [social/README.md](social/README.md)
* [streak/README.md](streak/README.md)
* [user/README.md](user/README.md)

---

## ⚠️ Regras e Convenções

* Manter responsabilidades da pasta isoladas por camada.
* Evitar dependências cíclicas entre módulos.
* Preservar contratos de entrada/saída (DTOs e assinaturas públicas).
* Ao adicionar comportamento novo em produção, atualizar os testes da camada correspondente (controller/service) no mesmo PR.
* Priorizar nomes de testes descritivos no formato cenário + resultado esperado.

---

## 🧠 Observações

* Escopo documentado: src/test/java/com/postread
* Esta documentação usa os arquivos atuais da pasta como fonte de verdade.
* A suíte atual concentra validação de regras de negócio e contratos HTTP, com foco em cenários de sucesso e erro.
* Recomenda-se evolução contínua com testes de integração end-to-end para fluxos críticos entre módulos.

---
