# service

## 📌 Propósito

Implementar as regras de autenticação e coordenar integrações com componentes internos (usuário e segurança).

---

## 📂 Arquivos e Responsabilidades

* AuthService.java → Serviço com regras de negócio do contexto.
* [../dto/README.md](../dto/README.md) → Contratos recebidos e retornados pelos métodos públicos.
* [../controller/README.md](../controller/README.md) → Camada chamadora deste serviço.

---

## 🔧 Funções / Métodos Principais

### register(RegisterRequest request)

**Descrição:**
Executa o cadastro de usuário com validações de unicidade e geração de token.

**Parâmetros:**
* request: RegisterRequest → parâmetro de entrada da operação.

**Retorno:**
`AuthResponse` com `token`, `userId`, `username` e `email` após persistir o usuário.

**Fluxo interno:**
* Verifica `existsByEmail(request.email())`; em conflito lança `ConflictException`.
* Verifica `existsByUsername(request.username())`; em conflito lança `ConflictException`.
* Cria `User` com `passwordHash` gerado por `PasswordEncoder`.
* Salva usuário via `UserRepository`.
* Gera JWT com `JwtService.generateToken(...)`.

---

### login(LoginRequest request)

**Descrição:**
Executa autenticação de credenciais e retorna o resultado com JWT.

**Parâmetros:**
* request: LoginRequest → parâmetro de entrada da operação.

**Retorno:**
`AuthResponse` com `token`, `userId`, `username` e `email`.

**Fluxo interno:**
* Autentica credenciais com `AuthenticationManager.authenticate(...)`.
* Busca usuário por email em `UserRepository.findByEmail(...)`.
* Se usuário não for encontrado após autenticação, lança `RuntimeException("User not found")`.
* Gera JWT com `JwtService.generateToken(...)`.

---

## ▶️ Exemplos de Uso

```java
// Exemplo de chamada de serviço
// AuthResponse auth = authService.login(loginRequest);
// AuthResponse created = authService.register(registerRequest);
```

---

## 🔗 Dependências

* [../README.md](../README.md)
* [../dto/README.md](../dto/README.md)
* [../controller/README.md](../controller/README.md)
* shared/
* user/

---

## ⚠️ Regras e Convenções

* Manter responsabilidades da pasta isoladas por camada.
* Evitar dependências cíclicas entre módulos.
* Preservar contratos de entrada/saída (DTOs e assinaturas públicas).
* Centralizar decisões de negócio no service para manter controller enxuto.

---

## 🧠 Observações

* Escopo documentado: src/main/java/com/postread/auth/service
* Esta documentação usa os arquivos atuais da pasta como fonte de verdade.
* Qualquer mudança de fluxo deve refletir em testes de serviço e de controlador.
* O método privado `toUserDetails(User user)` mapeia papéis removendo o prefixo `ROLE_` antes de montar o `UserDetails`.

---
