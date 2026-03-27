# model

## 📌 Propósito

Representar entidades e estruturas de domínio persistidas ou manipuladas internamente.

---

## 📂 Arquivos e Responsabilidades

* FeedEvent.java → Entidade MongoDB do feed (`@Document(collection = "feed_events")`).
* [../repository/README.md](../repository/README.md) → Camada que consulta e persiste a entidade.
* [../service/README.md](../service/README.md) → Camada que manipula a entidade no fluxo de negócio.

---

## 🔧 Funções / Métodos Principais

Nenhuma função pública de negócio declarada; a classe usa Lombok para geração de boilerplate e enum interno `EventType` para tipagem de eventos.

---

## ▶️ Exemplos de Uso

```java
// Exemplo de uso no serviço:
// FeedEvent saved = feedEventRepository.save(event);
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

* Escopo documentado: src/main/java/com/postread/feed/model
* Esta documentação usa os arquivos atuais da pasta como fonte de verdade.
* A entidade define índice composto `targetUserId + createdAt(desc)` para otimizar leitura do feed por usuário.

---
