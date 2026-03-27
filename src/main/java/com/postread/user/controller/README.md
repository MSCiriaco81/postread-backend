# controller

## 📌 Propósito

Expor endpoints HTTP autenticados para consulta e atualização de perfil de usuário.

---

## 📂 Arquivos e Responsabilidades

* UserController.java → Controlador REST para este contexto.
* [../service/README.md](../service/README.md) → Camada de negócio chamada pelos endpoints.
* [../dto/README.md](../dto/README.md) → Contratos de entrada/saída utilizados no controlador.

---

## 🔧 Funções / Métodos Principais

### getMyProfile(@AuthenticationPrincipal UserDetails principal)

**Descrição:**
Recebe `GET /api/v1/users/me`, resolve o usuário autenticado por email e retorna seu perfil.

**Parâmetros:**
* principal: @AuthenticationPrincipal UserDetails → parâmetro de entrada da operação.

**Retorno:**
`ResponseEntity<UserResponse>` com status `200 OK`.

---

### getProfile(@PathVariable String userId)

**Descrição:**
Recebe `GET /api/v1/users/{userId}` e retorna perfil por identificador.

**Parâmetros:**
* userId: @PathVariable String → parâmetro de entrada da operação.

**Retorno:**
`ResponseEntity<UserResponse>` com status `200 OK`.

---

### getProfileByUsername(@PathVariable String username)

**Descrição:**
Recebe `GET /api/v1/users/username/{username}` e retorna perfil por nome de usuário.

**Parâmetros:**
* username: @PathVariable String → parâmetro de entrada da operação.

**Retorno:**
`ResponseEntity<UserResponse>` com status `200 OK`.

---

### updateProfile(@AuthenticationPrincipal UserDetails principal, @Valid @RequestBody UpdateProfileRequest request)

**Descrição:**
Recebe `PATCH /api/v1/users/me`, resolve o usuário autenticado e delega atualização de perfil para `userService.updateProfile(...)`.

**Parâmetros:**
* principal: @AuthenticationPrincipal UserDetails → parâmetro de entrada da operação.
* request: @Valid @RequestBody UpdateProfileRequest → parâmetro de entrada da operação.

**Retorno:**
`ResponseEntity<UserResponse>` com status `200 OK`.

---

## ▶️ Exemplos de Uso

```java
// Exemplo simplificado de endpoint
// @PatchMapping("/me")
// public ResponseEntity<UserResponse> updateProfile(
//         @AuthenticationPrincipal UserDetails principal,
//         @Valid @RequestBody UpdateProfileRequest request) {
//     var user = userService.getByEmail(principal.getUsername());
//     return ResponseEntity.ok(userService.updateProfile(user.getId(), request));
// }
```

---

## 🔗 Dependências

* [../README.md](../README.md)
* [../service/README.md](../service/README.md)
* [../dto/README.md](../dto/README.md)

---

## ⚠️ Regras e Convenções

* Manter responsabilidades da pasta isoladas por camada.
* Evitar dependências cíclicas entre módulos.
* Preservar contratos de entrada/saída (DTOs e assinaturas públicas).

---

## 🧠 Observações

* Escopo documentado: src/main/java/com/postread/user/controller
* Esta documentação usa os arquivos atuais da pasta como fonte de verdade.
* A classe exige autenticação (`@SecurityRequirement(name = "bearerAuth")`).

---
