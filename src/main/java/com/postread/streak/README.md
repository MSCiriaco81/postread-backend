# streak

## 📌 Propósito

Centralizar o contexto de streaks e desafios de leitura em grupo, com acompanhamento de progresso e check-ins.

---

## 📂 Arquivos e Responsabilidades

* Nenhum arquivo direto nesta pasta (apenas subpastas).
* [controller/README.md](controller/README.md) → Endpoints de criação, consulta e check-in de streaks.
* [dto/README.md](dto/README.md) → Contrato de entrada para criação de streak.
* [model/README.md](model/README.md) → Entidades de streak e atividade diária.
* [repository/README.md](repository/README.md) → Persistência e consultas de streaks/atividades.
* [service/README.md](service/README.md) → Regras de negócio de progresso, check-in e processamento por leitura.

---

## 🔧 Funções / Métodos Principais

Nenhuma função pública relevante declarada diretamente nesta pasta.

---

## ▶️ Exemplos de Uso

```java
// Fluxo típico do módulo streak:
// POST /api/v1/streaks -> StreakController.createStreak(...)
// POST /api/v1/streaks/{streakId}/checkin -> StreakController.checkIn(...)
// ReadingService.logReading(...) -> StreakService.processReadingEntry(...)
```

---

## 🔗 Dependências

* [controller/README.md](controller/README.md)
* [dto/README.md](dto/README.md)
* [model/README.md](model/README.md)
* [repository/README.md](repository/README.md)
* [service/README.md](service/README.md)

---

## ⚠️ Regras e Convenções

* Manter responsabilidades da pasta isoladas por camada.
* Evitar dependências cíclicas entre módulos.
* Preservar contratos de entrada/saída (DTOs e assinaturas públicas).

---

## 🧠 Observações

* Escopo documentado: src/main/java/com/postread/streak
* Esta documentação usa os arquivos atuais da pasta como fonte de verdade.
* Navegação rápida: [controller](controller/README.md) | [dto](dto/README.md) | [model](model/README.md) | [repository](repository/README.md) | [service](service/README.md)

---
