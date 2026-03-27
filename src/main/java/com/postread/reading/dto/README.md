# dto

## 📌 Propósito

Definir o contrato de entrada para registro de sessões de leitura.

---

## 📂 Arquivos e Responsabilidades

* ReadingEntryRequest.java → DTO de entrada para log de leitura com validações de campos.
* [../controller/README.md](../controller/README.md) → Camada que recebe este DTO via HTTP.
* [../service/README.md](../service/README.md) → Camada que consome o DTO para criar `ReadingEntry`.

---

## 🔧 Funções / Métodos Principais

Nenhuma função pública relevante declarada diretamente nesta pasta.

---

## ▶️ Exemplos de Uso

```java
// Campos e validações de ReadingEntryRequest:
// - bookId: @NotBlank
// - minutesRead: @Min(0)
// - pagesRead: @Min(0)
// - rating: @Min(1) @Max(5)
// - notes: @Size(max = 2000)
// - date: opcional (quando nulo, service usa LocalDate.now())
```

---

## 🔗 Dependências

* [../README.md](../README.md)
* [../controller/README.md](../controller/README.md)
* [../service/README.md](../service/README.md)

---

## ⚠️ Regras e Convenções

* Manter responsabilidades da pasta isoladas por camada.
* Evitar dependências cíclicas entre módulos.
* Preservar contratos de entrada/saída (DTOs e assinaturas públicas).

---

## 🧠 Observações

* Escopo documentado: src/main/java/com/postread/reading/dto
* Esta documentação usa os arquivos atuais da pasta como fonte de verdade.
* O DTO é um `record` e não possui métodos de negócio.

---
