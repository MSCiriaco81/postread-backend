# service

## 📌 Propósito

Implementar regras de negócio e coordenar operações entre camadas.

---

## 📂 Arquivos e Responsabilidades

* StreakService.java → Serviço com regras de negócio do contexto.
* [../repository/README.md](../repository/README.md) → Persistência e consultas de streaks e atividades.
* [../dto/README.md](../dto/README.md) → Contrato de entrada para criação de streak.
* [../model/README.md](../model/README.md) → Entidades `Streak` e `StreakActivity`.

---

## 🔧 Funções / Métodos Principais

### createStreak(String creatorId, CreateStreakRequest request)

**Descrição:**
Cria streak garantindo que o criador esteja na lista de participantes (adiciona no início quando necessário) e persiste com data inicial atual.

**Parâmetros:**
* creatorId: String → parâmetro de entrada da operação.
* request: CreateStreakRequest → parâmetro de entrada da operação.

**Retorno:**
`Streak` criado.

---

### getMyActiveStreaks(String userId)

**Descrição:**
Lista streaks ativas em que o usuário participa.

**Parâmetros:**
* userId: String → parâmetro de entrada da operação.

**Retorno:**
`List<Streak>`.

---

### getStreak(String streakId)

**Descrição:**
Busca streak por ID; quando não encontrada lança `ResourceNotFoundException("Streak", streakId)`.

**Parâmetros:**
* streakId: String → parâmetro de entrada da operação.

**Retorno:**
`Streak`.

---

### processReadingEntry(String userId, LocalDate date, Integer minutesRead)

**Descrição:**
Processa uma nova leitura do usuário em todas as streaks ativas: registra atividade e tenta recalcular progresso (com proteção por `try/catch` por streak).

**Parâmetros:**
* userId: String → parâmetro de entrada da operação.
* date: LocalDate → parâmetro de entrada da operação.
* minutesRead: Integer → parâmetro de entrada da operação.

**Retorno:**
`void`.

---

### checkIn(String userId, String streakId, int minutesRead)

**Descrição:**
Registra check-in manual do dia na streak quando o usuário é participante; caso contrário lança `BusinessException`.

**Parâmetros:**
* userId: String → parâmetro de entrada da operação.
* streakId: String → parâmetro de entrada da operação.
* minutesRead: int → parâmetro de entrada da operação.

**Retorno:**
`StreakActivity`.

---

## ▶️ Exemplos de Uso

```java
// Exemplo de chamadas de serviço
// Streak created = streakService.createStreak(userId, request);
// StreakActivity checkin = streakService.checkIn(userId, streakId, 20);
// streakService.processReadingEntry(userId, LocalDate.now(), 15);
```

---

## 🔗 Dependências

* [../README.md](../README.md)
* [../dto/README.md](../dto/README.md)
* [../model/README.md](../model/README.md)
* [../repository/README.md](../repository/README.md)
* [../../shared/README.md](../../shared/README.md)

---

## ⚠️ Regras e Convenções

* Manter responsabilidades da pasta isoladas por camada.
* Evitar dependências cíclicas entre módulos.
* Preservar contratos de entrada/saída (DTOs e assinaturas públicas).

---

## 🧠 Observações

* Escopo documentado: src/main/java/com/postread/streak/service
* Esta documentação usa os arquivos atuais da pasta como fonte de verdade.
* O recálculo automático atual aplica regra explícita para `GoalType.CONSECUTIVE_DAYS`.

---
