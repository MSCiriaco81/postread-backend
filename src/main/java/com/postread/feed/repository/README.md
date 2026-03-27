# repository

## 📌 Propósito

Abstrair o acesso a dados e operações de persistência.

---

## 📂 Arquivos e Responsabilidades

* FeedEventRepository.java → Repositório para operações de persistência.
* [../model/README.md](../model/README.md) → Entidade principal manipulada pelo repositório.
* [../service/README.md](../service/README.md) → Camada consumidora dos métodos de consulta.

---

## 🔧 Funções / Métodos Principais

### findByTargetUserIdOrderByCreatedAtDesc(String targetUserId, Pageable pageable)

**Descrição:**
Recupera eventos de feed de um usuário alvo, ordenados por `createdAt` decrescente.

**Parâmetros:**
* targetUserId: String → identificador do usuário dono do feed.
* pageable: Pageable → parâmetros de paginação.

**Retorno:**
`Page<FeedEvent>` com eventos paginados do feed.

---

## ▶️ Exemplos de Uso

```java
// Exemplo de uso no serviço:
// return feedEventRepository.findByTargetUserIdOrderByCreatedAtDesc(userId, pageable);
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

* Escopo documentado: src/main/java/com/postread/feed/repository
* Esta documentação usa os arquivos atuais da pasta como fonte de verdade.
* A interface estende `MongoRepository<FeedEvent, String>` e herda operações CRUD padrão.

---
