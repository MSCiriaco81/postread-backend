# dto

## 📌 Propósito

Definir contratos de entrada para criação de streaks e desafios.

---

## 📂 Arquivos e Responsabilidades

* CreateStreakRequest.java → DTO de criação de streak com título, participantes, tipo de meta e valor da meta.
* [../controller/README.md](../controller/README.md) → Camada que recebe o DTO via HTTP.
* [../service/README.md](../service/README.md) → Camada que consome o DTO para criar entidade `Streak`.

---

## 🔧 Funções / Métodos Principais

Nenhuma função pública relevante declarada diretamente nesta pasta.

---

## ▶️ Exemplos de Uso

```java
// Campos e validações de CreateStreakRequest:
// - title: @NotBlank
// - participantIds: lista de participantes
// - goalType: @NotNull (CONSECUTIVE_DAYS, TOTAL_MINUTES, TOTAL_PAGES)
// - goalValue: @Min(1)
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

* Escopo documentado: src/main/java/com/postread/streak/dto
* Esta documentação usa os arquivos atuais da pasta como fonte de verdade.
* O DTO é `record` e não possui métodos de negócio.

---
