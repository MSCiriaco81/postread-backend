# controller

## 📌 Propósito

Expor endpoints HTTP de autenticação, validar entrada e delegar o processamento para a camada de serviço.

---

## 📂 Arquivos e Responsabilidades

* AuthController.java → Controlador REST para este contexto.
* [../service/README.md](../service/README.md) → Implementação das regras chamadas por este controlador.
* [../dto/README.md](../dto/README.md) → Objetos de request/response utilizados nos endpoints.

---

## 🔧 Funções / Métodos Principais

### register(@Valid @RequestBody RegisterRequest request)

**Descrição:**
Recebe o payload de cadastro no endpoint `POST /api/v1/auth/register`, valida com `@Valid` e delega para `authService.register(request)`.

**Parâmetros:**
* request: @Valid @RequestBody RegisterRequest → parâmetro de entrada da operação.

**Retorno:**
`ResponseEntity<AuthResponse>` com status `201 Created` e corpo contendo `token`, `userId`, `username` e `email`.

---

### login(@Valid @RequestBody LoginRequest request)

**Descrição:**
Recebe o payload de login no endpoint `POST /api/v1/auth/login`, valida com `@Valid` e delega para `authService.login(request)`.

**Parâmetros:**
* request: @Valid @RequestBody LoginRequest → parâmetro de entrada da operação.

**Retorno:**
`ResponseEntity<AuthResponse>` com status `200 OK` e corpo contendo `token`, `userId`, `username` e `email`.

---

## ▶️ Exemplos de Uso

```java
// Exemplo simplificado de endpoint de autenticação
// @PostMapping("/login")
// public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
//     return ResponseEntity.ok(authService.login(request));
// }
//
// @PostMapping("/register")
// public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest request) {
//     return ResponseEntity.status(HttpStatus.CREATED).body(authService.register(request));
// }
```

---

## 🔗 Dependências

* [../README.md](../README.md)
* [../dto/README.md](../dto/README.md)
* [../service/README.md](../service/README.md)

---

## ⚠️ Regras e Convenções

* Manter responsabilidades da pasta isoladas por camada.
* Evitar dependências cíclicas entre módulos.
* Preservar contratos de entrada/saída (DTOs e assinaturas públicas).
* Não implementar regra de negócio no controller; manter apenas orquestração HTTP.

---

## 🧠 Observações

* Escopo documentado: src/main/java/com/postread/auth/controller
* Esta documentação usa os arquivos atuais da pasta como fonte de verdade.
* Retornar status HTTP coerente com o resultado da autenticação.
* `@ResponseStatus(HttpStatus.CREATED)` e `ResponseEntity.status(HttpStatus.CREATED)` reforçam a semântica do cadastro.

---
