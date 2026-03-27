# repository

## 📌 Propósito

Abstrair o acesso a dados e operações de persistência.

---

## 📂 Arquivos e Responsabilidades

* FriendshipRepository.java → Repositório para operações de persistência.
* [../model/README.md](../model/README.md) → Entidade principal manipulada pelo repositório.
* [../service/README.md](../service/README.md) → Camada que consome consultas de amizade.

---

## 🔧 Funções / Métodos Principais

### findBetweenUsers(String userId1, String userId2)

**Descrição:**
Consulta amizade existente entre dois usuários em qualquer direção (`requester/receiver` invertidos).

**Parâmetros:**
* userId1: String → identificador do primeiro usuário.
* userId2: String → identificador do segundo usuário.

**Retorno:**
`Optional<Friendship>`.

---

### findByUserAndStatus(String userId, FriendshipStatus status)

**Descrição:**
Consulta amizades de um usuário (como requester ou receiver) filtrando por status.

**Parâmetros:**
* userId: String → identificador do usuário.
* status: FriendshipStatus → status de amizade para filtro.

**Retorno:**
`List<Friendship>`.

---

### findByReceiverIdAndStatus(String receiverId, FriendshipStatus status)

**Descrição:**
Consulta solicitações recebidas por um usuário com status específico.

**Parâmetros:**
* receiverId: String → usuário receptor da solicitação.
* status: FriendshipStatus → status para filtro (ex.: `PENDING`).

**Retorno:**
`List<Friendship>`.

---

## ▶️ Exemplos de Uso

```java
// Exemplo de uso no serviço:
// return friendshipRepository.findByReceiverIdAndStatus(userId, FriendshipStatus.PENDING);
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

* Escopo documentado: src/main/java/com/postread/social/repository
* Esta documentação usa os arquivos atuais da pasta como fonte de verdade.
* Interface estende `MongoRepository<Friendship, String>` e herda operações CRUD padrão.

---
