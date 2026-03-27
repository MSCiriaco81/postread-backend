# streak

## 📌 Propósito

Documentar a cobertura de testes do contexto de streaks e check-ins.

---

## 📂 Arquivos e Responsabilidades

* [service/StreakServiceTest.java](service/StreakServiceTest.java) → Valida criação de streak, check-in, processamento por leitura e cenários de erro.
* [../README.md](../README.md) → Guia mestre da suíte de testes.

---

## 🔧 Funções / Métodos Principais

### StreakServiceTest

* `createStreak_includesCreatorInParticipants`
* `createStreak_noDuplicateCreator`
* `checkIn_validParticipant_recordsActivity`
* `checkIn_notParticipant_throwsBusiness`
* `processReadingEntry_processesAllActiveStreaks`
* `processReadingEntry_allComplete_incrementsStreak`
* `getStreak_notFound_throws`

---

## ▶️ Exemplos de Uso

```java
// Executar apenas os testes de streak:
// mvn -Dtest=StreakServiceTest test
```

---

## 🔗 Dependências

* [service/StreakServiceTest.java](service/StreakServiceTest.java)
* [../README.md](../README.md)

---

## ⚠️ Regras e Convenções

* Manter responsabilidades da pasta isoladas por camada.
* Evitar dependências cíclicas entre módulos.
* Preservar contratos de entrada/saída (DTOs e assinaturas públicas).

---

## 🧠 Observações

* Escopo documentado: src/test/java/com/postread/streak
* Esta documentação usa os arquivos atuais da pasta como fonte de verdade.
* Total do módulo: 1 classe de teste, 7 métodos de teste.

---
