# controller

## 📌 Propósito

Expor endpoints HTTP autenticados para gerenciar conexões sociais entre usuários.

---

## 📂 Arquivos e Responsabilidades

* SocialController.java → Controlador REST para este contexto.
* [../service/README.md](../service/README.md) → Camada de negócio para operações de amizade.
* [../model/README.md](../model/README.md) → Modelo `Friendship` retornado em operações sociais.

---

## 🔧 Funções / Métodos Principais

### sendRequest(@AuthenticationPrincipal UserDetails principal, @PathVariable String receiverId)

**Descrição:**
Recebe `POST /api/v1/social/friends/request/{receiverId}`, resolve o `userId` autenticado e delega criação da solicitação para `socialService.sendFriendRequest(...)`.

**Parâmetros:**
* principal: @AuthenticationPrincipal UserDetails → parâmetro de entrada da operação.
* receiverId: @PathVariable String → parâmetro de entrada da operação.

**Retorno:**
`ResponseEntity<Friendship>` com status `201 Created`.

---

### acceptRequest(@AuthenticationPrincipal UserDetails principal, @PathVariable String friendshipId)

**Descrição:**
Recebe `POST /api/v1/social/friends/accept/{friendshipId}`, resolve o usuário autenticado e delega aceite para `socialService.acceptFriendRequest(...)`.

**Parâmetros:**
* principal: @AuthenticationPrincipal UserDetails → parâmetro de entrada da operação.
* friendshipId: @PathVariable String → parâmetro de entrada da operação.

**Retorno:**
`ResponseEntity<Friendship>` com status `200 OK`.

---

### rejectRequest(@AuthenticationPrincipal UserDetails principal, @PathVariable String friendshipId)

**Descrição:**
Recebe `POST /api/v1/social/friends/reject/{friendshipId}`, resolve o usuário autenticado e delega rejeição para `socialService.rejectFriendRequest(...)`.

**Parâmetros:**
* principal: @AuthenticationPrincipal UserDetails → parâmetro de entrada da operação.
* friendshipId: @PathVariable String → parâmetro de entrada da operação.

**Retorno:**
`ResponseEntity<Void>` com status `204 No Content`.

---

### getFriends(@AuthenticationPrincipal UserDetails principal)

**Descrição:**
Recebe `GET /api/v1/social/friends` e retorna lista de amigos do usuário autenticado.

**Parâmetros:**
* principal: @AuthenticationPrincipal UserDetails → parâmetro de entrada da operação.

**Retorno:**
`ResponseEntity<List<UserResponse>>` com status `200 OK`.

---

### getPendingRequests(@AuthenticationPrincipal UserDetails principal)

**Descrição:**
Recebe `GET /api/v1/social/friends/requests` e retorna solicitações pendentes recebidas pelo usuário autenticado.

**Parâmetros:**
* principal: @AuthenticationPrincipal UserDetails → parâmetro de entrada da operação.

**Retorno:**
`ResponseEntity<List<Friendship>>` com status `200 OK`.

---

## ▶️ Exemplos de Uso

```java
// Exemplo simplificado de endpoint
// @PostMapping("/friends/request/{receiverId}")
// public ResponseEntity<Friendship> sendRequest(
//         @AuthenticationPrincipal UserDetails principal,
//         @PathVariable String receiverId) {
//     String userId = userService.getByEmail(principal.getUsername()).getId();
//     return ResponseEntity.status(HttpStatus.CREATED)
//             .body(socialService.sendFriendRequest(userId, receiverId));
// }
```

---

## 🔗 Dependências

* [../README.md](../README.md)
* [../service/README.md](../service/README.md)
* [../model/README.md](../model/README.md)
* [../../user/README.md](../../user/README.md)

---

## ⚠️ Regras e Convenções

* Manter responsabilidades da pasta isoladas por camada.
* Evitar dependências cíclicas entre módulos.
* Preservar contratos de entrada/saída (DTOs e assinaturas públicas).

---

## 🧠 Observações

* Escopo documentado: src/main/java/com/postread/social/controller
* Esta documentação usa os arquivos atuais da pasta como fonte de verdade.
* A classe exige autenticação (`@SecurityRequirement(name = "bearerAuth")`).

---
