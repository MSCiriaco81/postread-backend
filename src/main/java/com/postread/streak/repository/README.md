# repository

## 📌 Propósito

Abstrair o acesso a dados e operações de persistência.

---

## 📂 Arquivos e Responsabilidades

* StreakActivityRepository.java → Repositório para operações de persistência.
* StreakRepository.java → Repositório para operações de persistência.
* [../model/README.md](../model/README.md) → Entidades manipuladas pelos repositórios.
* [../service/README.md](../service/README.md) → Camada consumidora das consultas.

---

## 🔧 Funções / Métodos Principais

### findActiveByParticipant(String userId)

**Descrição:**
Consulta streaks com status `ACTIVE` em que o usuário participa.

**Parâmetros:**
* userId: String → identificador do participante.

**Retorno:**
`List<Streak>`.

---

### findByCreatorId(String creatorId)

**Descrição:**
Consulta streaks criadas por um usuário.

**Parâmetros:**
* creatorId: String → identificador do criador.

**Retorno:**
`List<Streak>`.

---

### findByStreakIdAndUserIdAndDate(String streakId, String userId, LocalDate date)

**Descrição:**
Consulta atividade diária específica de um usuário em uma streak.

**Parâmetros:**
* streakId: String → identificador da streak.
* userId: String → identificador do usuário.
* date: LocalDate → data da atividade.

**Retorno:**
`Optional<StreakActivity>`.

---

### findByStreakIdAndDate(String streakId, LocalDate date)

**Descrição:**
Lista atividades de uma streak em uma data específica.

**Parâmetros:**
* streakId: String → identificador da streak.
* date: LocalDate → data da consulta.

**Retorno:**
`List<StreakActivity>`.

---

### findByStreakIdAndUserIdOrderByDateDesc(String streakId, String userId)

**Descrição:**
Lista histórico de atividades de um usuário em uma streak, ordenado por data decrescente.

**Parâmetros:**
* streakId: String → identificador da streak.
* userId: String → identificador do usuário.

**Retorno:**
`List<StreakActivity>`.

---

## ▶️ Exemplos de Uso

```java
// Exemplo de uso no service:
// return streakRepository.findActiveByParticipant(userId);
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

* Escopo documentado: src/main/java/com/postread/streak/repository
* Esta documentação usa os arquivos atuais da pasta como fonte de verdade.
* Ambos os repositórios estendem `MongoRepository` e herdam operações CRUD padrão.

---
