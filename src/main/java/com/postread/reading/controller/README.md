# controller

## 📌 Propósito

Expor endpoints HTTP autenticados para registrar e consultar leituras do usuário.

---

## 📂 Arquivos e Responsabilidades

* ReadingController.java → Controlador REST para este contexto.
* [../service/README.md](../service/README.md) → Camada de negócio chamada pelos endpoints.
* [../dto/README.md](../dto/README.md) → Contrato de entrada para registro de leitura.
* [../model/README.md](../model/README.md) → Modelo retornado pelas operações.

---

## 🔧 Funções / Métodos Principais

### logReading(@AuthenticationPrincipal UserDetails principal, @Valid @RequestBody ReadingEntryRequest request)

**Descrição:**
Recebe `POST /api/v1/readings`, resolve o `userId` a partir do usuário autenticado e delega o registro da leitura para `readingService.logReading(...)`.

**Parâmetros:**
* principal: @AuthenticationPrincipal UserDetails → parâmetro de entrada da operação.
* request: @Valid @RequestBody ReadingEntryRequest → parâmetro de entrada da operação.

**Retorno:**
`ResponseEntity<ReadingEntry>` com status `201 Created` e a entrada persistida.

---

### getMyReadings(@AuthenticationPrincipal UserDetails principal, @PageableDefault(size = 20) Pageable pageable)

**Descrição:**
Recebe `GET /api/v1/readings`, resolve o `userId` autenticado e retorna histórico paginado via `readingService.getUserReadings(...)`.

**Parâmetros:**
* principal: @AuthenticationPrincipal UserDetails → usuário autenticado.
* pageable: @PageableDefault(size = 20) Pageable → paginação padrão com tamanho 20.

**Retorno:**
`ResponseEntity<Page<ReadingEntry>>` com status `200 OK`.

---

### getReadingsInRange(@AuthenticationPrincipal UserDetails principal, @RequestParam LocalDate from, @RequestParam LocalDate to)

**Descrição:**
Recebe `GET /api/v1/readings/range`, resolve o `userId` autenticado e consulta leituras no intervalo de datas.

**Parâmetros:**
* principal: @AuthenticationPrincipal UserDetails → usuário autenticado.
* from: @RequestParam LocalDate → início do período (ISO date).
* to: @RequestParam LocalDate → fim do período (ISO date).

**Retorno:**
`ResponseEntity<List<ReadingEntry>>` com status `200 OK`.

---

### deleteEntry(@AuthenticationPrincipal UserDetails principal, @PathVariable String entryId)

**Descrição:**
Recebe `DELETE /api/v1/readings/{entryId}`, resolve o `userId` autenticado e delega a remoção para `readingService.deleteEntry(...)`.

**Parâmetros:**
* principal: @AuthenticationPrincipal UserDetails → parâmetro de entrada da operação.
* entryId: @PathVariable String → parâmetro de entrada da operação.

**Retorno:**
`ResponseEntity<Void>` com status `204 No Content`.

---

## ▶️ Exemplos de Uso

```java
// Exemplo simplificado de endpoint
// @GetMapping
// public ResponseEntity<Page<ReadingEntry>> getMyReadings(
//         @AuthenticationPrincipal UserDetails principal,
//         @PageableDefault(size = 20) Pageable pageable) {
//     String userId = userService.getByEmail(principal.getUsername()).getId();
//     return ResponseEntity.ok(readingService.getUserReadings(userId, pageable));
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

* Escopo documentado: src/main/java/com/postread/reading/controller
* Esta documentação usa os arquivos atuais da pasta como fonte de verdade.
* A classe exige autenticação (`@SecurityRequirement(name = "bearerAuth")`).

---
