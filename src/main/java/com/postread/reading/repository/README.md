# repository

## 📌 Propósito

Abstrair o acesso a dados e operações de persistência.

---

## 📂 Arquivos e Responsabilidades

* ReadingEntryRepository.java → Repositório para operações de persistência.
* [../model/README.md](../model/README.md) → Entidade principal manipulada pelo repositório.
* [../service/README.md](../service/README.md) → Camada que consome os métodos de consulta.

---

## 🔧 Funções / Métodos Principais

### findByUserIdOrderByDateDesc(String userId, Pageable pageable)

**Descrição:**
Consulta histórico de leitura paginado por usuário, ordenando por data decrescente.

**Parâmetros:**
* userId: String → identificador do usuário.
* pageable: Pageable → parâmetros de paginação.

**Retorno:**
`Page<ReadingEntry>` com histórico paginado.

---

### findByUserIdAndDateBetween(String userId, LocalDate from, LocalDate to)

**Descrição:**
Consulta leituras do usuário em um intervalo de datas.

**Parâmetros:**
* userId: String → identificador do usuário.
* from: LocalDate → data inicial.
* to: LocalDate → data final.

**Retorno:**
`List<ReadingEntry>` com leituras no período.

---

### findByUserIdAndBookId(String userId, String bookId)

**Descrição:**
Consulta leituras de um usuário para um livro específico.

**Parâmetros:**
* userId: String → identificador do usuário.
* bookId: String → identificador do livro.

**Retorno:**
`List<ReadingEntry>` com entradas de leitura do livro.

---

### existsByUserIdAndDate(String userId, LocalDate date)

**Descrição:**
Verifica se existe entrada de leitura para o usuário em uma data específica.

**Parâmetros:**
* userId: String → identificador do usuário.
* date: LocalDate → data da verificação.

**Retorno:**
`boolean` indicando existência de registro.

---

### sumByUserId(String userId)

**Descrição:**
Executa agregação MongoDB para somar minutos e páginas lidas de um usuário.

**Parâmetros:**
* userId: String → identificador do usuário.

**Retorno:**
`ReadingEntryRepository.ReadingStats` com totais agregados (`totalMinutes`, `totalPages`).

---

## ▶️ Exemplos de Uso

```java
// Exemplo de uso no service:
// return readingEntryRepository.findByUserIdOrderByDateDesc(userId, pageable);
```

---

## 🔗 Dependências

* [../README.md](../README.md)
* [../model/README.md](../model/README.md)
* [../service/README.md](../service/README.md)

---

## ⚠️ Regras e Convenções

* Manter responsabilidades da pasta isoladas por camada.
* Evitar dependências cíclicas entre módulos.
* Preservar contratos de entrada/saída (DTOs e assinaturas públicas).

---

## 🧠 Observações

* Escopo documentado: src/main/java/com/postread/reading/repository
* Esta documentação usa os arquivos atuais da pasta como fonte de verdade.
* Interface estende `MongoRepository<ReadingEntry, String>` e herda operações CRUD padrão.

---
