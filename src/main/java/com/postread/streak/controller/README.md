# controller

## 📌 Propósito

Expor endpoints HTTP autenticados para criar, consultar e registrar check-ins em streaks.

---

## 📂 Arquivos e Responsabilidades

* StreakController.java → Controlador REST para este contexto.
* [../service/README.md](../service/README.md) → Camada de negócio chamada pelos endpoints.
* [../dto/README.md](../dto/README.md) → Contrato de entrada para criação de streak.
* [../model/README.md](../model/README.md) → Modelos retornados pelas operações (`Streak`, `StreakActivity`).

---

## 🔧 Funções / Métodos Principais

### createStreak(@AuthenticationPrincipal UserDetails principal, @Valid @RequestBody CreateStreakRequest request)

**Descrição:**
Recebe `POST /api/v1/streaks`, resolve o `userId` autenticado e delega criação para `streakService.createStreak(...)`.

**Parâmetros:**
* principal: @AuthenticationPrincipal UserDetails → parâmetro de entrada da operação.
* request: @Valid @RequestBody CreateStreakRequest → parâmetro de entrada da operação.

**Retorno:**
`ResponseEntity<Streak>` com status `201 Created`.

---

### getMyStreaks(@AuthenticationPrincipal UserDetails principal)

**Descrição:**
Recebe `GET /api/v1/streaks` e retorna streaks ativas do usuário autenticado via `streakService.getMyActiveStreaks(...)`.

**Parâmetros:**
* principal: @AuthenticationPrincipal UserDetails → parâmetro de entrada da operação.

**Retorno:**
`ResponseEntity<List<Streak>>` com status `200 OK`.

---

### getStreak(@PathVariable String streakId)

**Descrição:**
Recebe `GET /api/v1/streaks/{streakId}` e retorna detalhes da streak.

**Parâmetros:**
* streakId: @PathVariable String → parâmetro de entrada da operação.

**Retorno:**
`ResponseEntity<Streak>` com status `200 OK`.

---

### checkIn(@AuthenticationPrincipal UserDetails principal, @PathVariable String streakId, @RequestParam(defaultValue = "0") int minutesRead)

**Descrição:**
Recebe `POST /api/v1/streaks/{streakId}/checkin`, resolve o `userId` autenticado e delega registro de atividade para `streakService.checkIn(...)`.

**Parâmetros:**
* principal: @AuthenticationPrincipal UserDetails → parâmetro de entrada da operação.
* streakId: @PathVariable String → identificador da streak.
* minutesRead: @RequestParam(defaultValue = "0") int → minutos do check-in.

**Retorno:**
`ResponseEntity<StreakActivity>` com status `200 OK`.

---

## ▶️ Exemplos de Uso

```java
// Exemplo simplificado de endpoint
// @PostMapping("/{streakId}/checkin")
// public ResponseEntity<StreakActivity> checkIn(
//         @AuthenticationPrincipal UserDetails principal,
//         @PathVariable String streakId,
//         @RequestParam(defaultValue = "0") int minutesRead) {
//     String userId = userService.getByEmail(principal.getUsername()).getId();
//     return ResponseEntity.ok(streakService.checkIn(userId, streakId, minutesRead));
// }
```

---

## 🔗 Dependências

* [../README.md](../README.md)
* [../service/README.md](../service/README.md)
* [../dto/README.md](../dto/README.md)
* [../model/README.md](../model/README.md)
* [../../user/README.md](../../user/README.md)

---

## ⚠️ Regras e Convenções

* Manter responsabilidades da pasta isoladas por camada.
* Evitar dependências cíclicas entre módulos.
* Preservar contratos de entrada/saída (DTOs e assinaturas públicas).

---

## 🧠 Observações

* Escopo documentado: src/main/java/com/postread/streak/controller
* Esta documentação usa os arquivos atuais da pasta como fonte de verdade.
* A classe exige autenticação (`@SecurityRequirement(name = "bearerAuth")`).

---
