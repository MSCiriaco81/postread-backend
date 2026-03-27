# feed

## 📌 Propósito

Documentar a cobertura de testes do contexto de feed.

---

## 📂 Arquivos e Responsabilidades

* [service/FeedServiceTest.java](service/FeedServiceTest.java) → Valida criação de eventos e consulta paginada de feed.
* [../README.md](../README.md) → Guia mestre da suíte de testes.

---

## 🔧 Funções / Métodos Principais

### FeedServiceTest

* `createEvent_savesEvent`
* `getFeed_returnsPaginatedEvents`
* `getFeed_noEvents_returnsEmptyPage`

---

## ▶️ Exemplos de Uso

```java
// Executar apenas os testes de feed:
// mvn -Dtest=FeedServiceTest test
```

---

## 🔗 Dependências

* [service/FeedServiceTest.java](service/FeedServiceTest.java)
* [../README.md](../README.md)

---

## ⚠️ Regras e Convenções

* Manter responsabilidades da pasta isoladas por camada.
* Evitar dependências cíclicas entre módulos.
* Preservar contratos de entrada/saída (DTOs e assinaturas públicas).

---

## 🧠 Observações

* Escopo documentado: src/test/java/com/postread/feed
* Esta documentação usa os arquivos atuais da pasta como fonte de verdade.
* Total do módulo: 1 classe de teste, 3 métodos de teste.

---
