# service

## 📌 Propósito

Implementar regras de negócio e coordenar operações entre camadas.

---

## 📂 Arquivos e Responsabilidades

* FeedService.java → Serviço com regras de negócio do contexto.
* [../repository/README.md](../repository/README.md) → Persistência e consulta de eventos do feed.
* [../model/README.md](../model/README.md) → Entidade manipulada pelos métodos públicos.

---

## 🔧 Funções / Métodos Principais

### createEvent(FeedEvent event)

**Descrição:**
Persiste um novo evento de feed no repositório.

**Parâmetros:**
* event: FeedEvent → parâmetro de entrada da operação.

**Retorno:**
`FeedEvent` salvo.

---

### getFeed(String userId, Pageable pageable)

**Descrição:**
Recupera o feed paginado do usuário via consulta ordenada por data de criação decrescente.

**Parâmetros:**
* userId: String → parâmetro de entrada da operação.
* pageable: Pageable → parâmetro de entrada da operação.

**Retorno:**
`Page<FeedEvent>` com eventos paginados.

---

## ▶️ Exemplos de Uso

```java
// Exemplo de chamadas de serviço
// FeedEvent created = feedService.createEvent(event);
// Page<FeedEvent> feed = feedService.getFeed(userId, pageable);
```

---

## 🔗 Dependências

* [../README.md](../README.md)
* [../model/README.md](../model/README.md)
* [../repository/README.md](../repository/README.md)

---

## ⚠️ Regras e Convenções

* Manter responsabilidades da pasta isoladas por camada.
* Evitar dependências cíclicas entre módulos.
* Preservar contratos de entrada/saída (DTOs e assinaturas públicas).

---

## 🧠 Observações

* Escopo documentado: src/main/java/com/postread/feed/service
* Esta documentação usa os arquivos atuais da pasta como fonte de verdade.
* No estado atual, o service atua como camada fina sobre o repositório para criação e listagem de eventos.

---
