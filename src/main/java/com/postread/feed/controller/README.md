# controller

## 📌 Propósito

Expor endpoint HTTP do feed social para usuários autenticados e orquestrar a consulta paginada.

---

## 📂 Arquivos e Responsabilidades

* FeedController.java → Controlador REST para este contexto.
* [../service/README.md](../service/README.md) → Serviço que consulta os eventos de feed.
* [../model/README.md](../model/README.md) → Modelo de retorno no endpoint (`FeedEvent`).

---

## 🔧 Funções / Métodos Principais

### getFeed(@AuthenticationPrincipal UserDetails principal, @PageableDefault(size = 20) Pageable pageable)

**Descrição:**
Recebe `GET /api/v1/feed`, obtém o usuário autenticado via `principal`, resolve o `userId` com `UserService` e delega a busca paginada para `feedService.getFeed(userId, pageable)`.

**Parâmetros:**
* principal: @AuthenticationPrincipal UserDetails → usuário autenticado (email em `principal.getUsername()`).
* pageable: @PageableDefault(size = 20) Pageable → paginação padrão com tamanho 20.

**Retorno:**
`ResponseEntity<Page<FeedEvent>>` com status `200 OK` e feed ordenado por data de criação.

---

## ▶️ Exemplos de Uso

```java
// Exemplo simplificado de endpoint
// @GetMapping
// public ResponseEntity<Page<FeedEvent>> getFeed(
//         @AuthenticationPrincipal UserDetails principal,
//         @PageableDefault(size = 20) Pageable pageable) {
//     String userId = userService.getByEmail(principal.getUsername()).getId();
//     return ResponseEntity.ok(feedService.getFeed(userId, pageable));
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

* Escopo documentado: src/main/java/com/postread/feed/controller
* Esta documentação usa os arquivos atuais da pasta como fonte de verdade.
* A classe inteira exige autenticação via `@SecurityRequirement(name = "bearerAuth")`.

---
