# auth

## 📌 Propósito

Centralizar o contexto de autenticação, separando responsabilidades entre entrada HTTP, contratos de dados e regras de negócio.

---

## 📂 Arquivos e Responsabilidades

* Nenhum arquivo direto nesta pasta (apenas subpastas).
* [controller/README.md](controller/README.md) → Endpoints e adaptação de requisições HTTP.
* [dto/README.md](dto/README.md) → Contratos de entrada e saída da API.
* [service/README.md](service/README.md) → Regras de autenticação e integração com outras camadas.

---

## 🔧 Funções / Métodos Principais

Nenhuma função pública relevante declarada diretamente nesta pasta.

---

## ▶️ Exemplos de Uso

```java
// Fluxo típico do módulo auth:
// POST /api/v1/auth/register -> AuthController.register(@Valid RegisterRequest)
//  -> AuthService.register(...)
//  -> valida conflitos (email/username), salva usuário, gera JWT
//  -> AuthResponse(token, userId, username, email)
//
// POST /api/v1/auth/login -> AuthController.login(@Valid LoginRequest)
//  -> AuthService.login(...)
//  -> autentica credenciais, busca usuário, gera JWT
//  -> AuthResponse(token, userId, username, email)
```

---

## 🔗 Dependências

* [controller/README.md](controller/README.md)
* [dto/README.md](dto/README.md)
* [service/README.md](service/README.md)

---

## ⚠️ Regras e Convenções

* Manter responsabilidades da pasta isoladas por camada.
* Evitar dependências cíclicas entre módulos.
* Preservar contratos de entrada/saída (DTOs e assinaturas públicas).
* Tratar validação de payload no controller e regra de negócio no service.

---

## 🧠 Observações

* Escopo documentado: src/main/java/com/postread/auth
* Esta documentação usa os arquivos atuais da pasta como fonte de verdade.
* Navegação rápida: [controller](controller/README.md) | [dto](dto/README.md) | [service](service/README.md)
* Endpoints documentados no código com OpenAPI (`@Tag` e `@Operation`).

---
