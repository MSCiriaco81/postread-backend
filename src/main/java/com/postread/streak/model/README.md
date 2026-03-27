# model

## 📌 Propósito

Representar entidades e estruturas de domínio persistidas ou manipuladas internamente.

---

## 📂 Arquivos e Responsabilidades

* Streak.java → Entidade MongoDB de streaks/grupos de leitura.
* StreakActivity.java → Entidade MongoDB de atividades diárias por streak e usuário.
* [../repository/README.md](../repository/README.md) → Camada que persiste/consulta ambas as entidades.
* [../service/README.md](../service/README.md) → Camada que aplica regras de progresso e check-in.

---

## 🔧 Funções / Métodos Principais

Nenhuma função pública de negócio declarada; as classes usam Lombok para geração de boilerplate e enums internos para tipo/status de streak.

---

## ▶️ Exemplos de Uso

```java
// Exemplo de uso no service:
// StreakActivity activity = StreakActivity.builder()
//     .streakId(streak.getId())
//     .userId(userId)
//     .date(LocalDate.now())
//     .minutesRead(minutesRead)
//     .completed(true)
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

* Escopo documentado: src/main/java/com/postread/streak/model
* Esta documentação usa os arquivos atuais da pasta como fonte de verdade.
* `StreakActivity` possui índice composto único (`streakId + userId + date`) para evitar duplicidade diária.

---
