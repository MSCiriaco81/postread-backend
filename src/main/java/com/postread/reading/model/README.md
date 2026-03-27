# model

## 📌 Propósito

Representar entidades e estruturas de domínio persistidas ou manipuladas internamente.

---

## 📂 Arquivos e Responsabilidades

* ReadingEntry.java → Entidade MongoDB (`@Document(collection = "reading_entries")`) para registros de leitura.
* [../repository/README.md](../repository/README.md) → Camada que consulta e persiste a entidade.
* [../service/README.md](../service/README.md) → Camada que monta e manipula entradas de leitura.

---

## 🔧 Funções / Métodos Principais

Nenhuma função pública de negócio declarada; a classe utiliza Lombok para geração de boilerplate.

---

## ▶️ Exemplos de Uso

```java
// Exemplo de construção no service:
// ReadingEntry entry = ReadingEntry.builder()
//     .userId(userId)
//     .bookId(request.bookId())
//     .date(date)
//     .build();
```

---

## 🔗 Dependências

* [../README.md](../README.md)
* [../repository/README.md](../repository/README.md)
* [../service/README.md](../service/README.md)

---

## ⚠️ Regras e Convenções

* Manter responsabilidades da pasta isoladas por camada.
* Evitar dependências cíclicas entre módulos.
* Preservar contratos de entrada/saída (DTOs e assinaturas públicas).

---

## 🧠 Observações

* Escopo documentado: src/main/java/com/postread/reading/model
* Esta documentação usa os arquivos atuais da pasta como fonte de verdade.
* Índices compostos definidos: `userId + date(desc)` e `userId + bookId`.

---
