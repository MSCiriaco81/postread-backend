# service

## 📌 Propósito

Implementar regras de negócio e coordenar operações entre camadas.

---

## 📂 Arquivos e Responsabilidades

* ReadingService.java → Serviço com regras de negócio do contexto.
* [../repository/README.md](../repository/README.md) → Persistência e consultas de entradas de leitura.
* [../dto/README.md](../dto/README.md) → Contrato de entrada usado no registro.
* [../model/README.md](../model/README.md) → Entidade de leitura criada/consultada.

---

## 🔧 Funções / Métodos Principais

### logReading(String userId, ReadingEntryRequest request)

**Descrição:**
Cria e persiste uma entrada de leitura; quando `request.date()` é nulo usa `LocalDate.now()`. Após salvar, processa streak e tenta publicar evento no feed dos amigos.

**Parâmetros:**
* userId: String → parâmetro de entrada da operação.
* request: ReadingEntryRequest → parâmetro de entrada da operação.

**Retorno:**
`ReadingEntry` salvo.

---

### getUserReadings(String userId, Pageable pageable)

**Descrição:**
Recupera histórico paginado de leitura do usuário ordenado por data.

**Parâmetros:**
* userId: String → parâmetro de entrada da operação.
* pageable: Pageable → parâmetro de entrada da operação.

**Retorno:**
`Page<ReadingEntry>` com histórico paginado.

---

### getUserReadingsInRange(String userId, LocalDate from, LocalDate to)

**Descrição:**
Recupera leituras do usuário no intervalo de datas informado.

**Parâmetros:**
* userId: String → parâmetro de entrada da operação.
* from: LocalDate → parâmetro de entrada da operação.
* to: LocalDate → parâmetro de entrada da operação.

**Retorno:**
`List<ReadingEntry>` com leituras do período.

---

### getEntry(String entryId)

**Descrição:**
Busca uma entrada por ID; quando não existe lança `ResourceNotFoundException("ReadingEntry", entryId)`.

**Parâmetros:**
* entryId: String → parâmetro de entrada da operação.

**Retorno:**
`ReadingEntry` encontrado.

---

### deleteEntry(String userId, String entryId)

**Descrição:**
Remove entrada de leitura após validar propriedade: se o `userId` não for o dono da entrada, lança `AccessDeniedException("Not your entry")`.

**Parâmetros:**
* userId: String → parâmetro de entrada da operação.
* entryId: String → parâmetro de entrada da operação.

**Retorno:**
`void`.

---

## ▶️ Exemplos de Uso

```java
// Exemplo de chamadas de serviço
// ReadingEntry created = readingService.logReading(userId, request);
// Page<ReadingEntry> page = readingService.getUserReadings(userId, pageable);
// readingService.deleteEntry(userId, entryId);
```

---

## 🔗 Dependências

* [../README.md](../README.md)
* [../dto/README.md](../dto/README.md)
* [../model/README.md](../model/README.md)
* [../repository/README.md](../repository/README.md)
* [../../feed/README.md](../../feed/README.md)
* [../../social/README.md](../../social/README.md)
* [../../streak/README.md](../../streak/README.md)
* [../../shared/README.md](../../shared/README.md)

---

## ⚠️ Regras e Convenções

* Manter responsabilidades da pasta isoladas por camada.
* Evitar dependências cíclicas entre módulos.
* Preservar contratos de entrada/saída (DTOs e assinaturas públicas).

---

## 🧠 Observações

* Escopo documentado: src/main/java/com/postread/reading/service
* Esta documentação usa os arquivos atuais da pasta como fonte de verdade.
* O broadcast para amigos é encapsulado no método privado `broadcastToFriends(...)` e falhas nessa etapa são logadas sem interromper o registro da leitura.

---
