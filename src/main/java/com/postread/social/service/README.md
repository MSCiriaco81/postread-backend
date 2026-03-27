# service

## 📌 Propósito

Implementar regras de negócio e coordenar operações entre camadas.

---

## 📂 Arquivos e Responsabilidades

* SocialService.java → Serviço com regras de negócio do contexto.
* [../repository/README.md](../repository/README.md) → Consultas e persistência de amizades.
* [../model/README.md](../model/README.md) → Entidade social manipulada pelos métodos.

---

## 🔧 Funções / Métodos Principais

### sendFriendRequest(String requesterId, String receiverId)

**Descrição:**
Cria solicitação de amizade validando regras: não permitir autoamizade, validar existência do destinatário e impedir duplicidade de relação entre os dois usuários.

**Parâmetros:**
* requesterId: String → parâmetro de entrada da operação.
* receiverId: String → parâmetro de entrada da operação.

**Retorno:**
`Friendship` com status inicial `PENDING`.

---

### acceptFriendRequest(String userId, String friendshipId)

**Descrição:**
Aceita uma solicitação pendente quando o usuário autenticado é o receptor, atualizando status para `ACCEPTED` e `updatedAt`.

**Parâmetros:**
* userId: String → parâmetro de entrada da operação.
* friendshipId: String → parâmetro de entrada da operação.

**Retorno:**
`Friendship` atualizado.

---

### rejectFriendRequest(String userId, String friendshipId)

**Descrição:**
Rejeita solicitação quando o usuário autenticado é o receptor, atualizando status para `REJECTED` e `updatedAt`.

**Parâmetros:**
* userId: String → parâmetro de entrada da operação.
* friendshipId: String → parâmetro de entrada da operação.

**Retorno:**
`void`.

---

### getFriends(String userId)

**Descrição:**
Lista amizades `ACCEPTED`, resolve o outro participante de cada relação e retorna dados de usuário como `UserResponse`.

**Parâmetros:**
* userId: String → parâmetro de entrada da operação.

**Retorno:**
`List<UserResponse>`.

---

### getFriendIds(String userId)

**Descrição:**
Retorna apenas IDs dos amigos em relações `ACCEPTED`.

**Parâmetros:**
* userId: String → parâmetro de entrada da operação.

**Retorno:**
`List<String>`.

---

### getPendingRequests(String userId)

**Descrição:**
Retorna solicitações pendentes recebidas pelo usuário (`receiverId = userId`, status `PENDING`).

**Parâmetros:**
* userId: String → parâmetro de entrada da operação.

**Retorno:**
`List<Friendship>`.

---

## ▶️ Exemplos de Uso

```java
// Exemplo de chamadas de serviço
// Friendship request = socialService.sendFriendRequest(requesterId, receiverId);
// Friendship accepted = socialService.acceptFriendRequest(userId, friendshipId);
// List<UserResponse> friends = socialService.getFriends(userId);
```

---

## 🔗 Dependências

* [../README.md](../README.md)
* [../model/README.md](../model/README.md)
* [../repository/README.md](../repository/README.md)
* [../../shared/README.md](../../shared/README.md)
* [../../user/README.md](../../user/README.md)

---

## ⚠️ Regras e Convenções

* Manter responsabilidades da pasta isoladas por camada.
* Evitar dependências cíclicas entre módulos.
* Preservar contratos de entrada/saída (DTOs e assinaturas públicas).

---

## 🧠 Observações

* Escopo documentado: src/main/java/com/postread/social/service
* Esta documentação usa os arquivos atuais da pasta como fonte de verdade.
* Regras de negócio de autorização no aceite/rejeição são aplicadas no service (somente o receptor pode decidir).

---
