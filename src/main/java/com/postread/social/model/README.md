# model

## 📌 Propósito

Representar entidades e estruturas de domínio persistidas ou manipuladas internamente.

---

## 📂 Arquivos e Responsabilidades

* Friendship.java → Entidade MongoDB de relacionamento social (`@Document(collection = "friendships")`).
* [../repository/README.md](../repository/README.md) → Camada que persiste/consulta amizades.
* [../service/README.md](../service/README.md) → Camada que aplica regras sobre status de amizade.

---

## 🔧 Funções / Métodos Principais

Nenhuma função pública de negócio declarada; a classe usa Lombok para geração de boilerplate e enum interno `FriendshipStatus`.

---

## ▶️ Exemplos de Uso

```java
// Exemplo de construção no serviço:
// Friendship friendship = Friendship.builder()
//     .requesterId(requesterId)
//     .receiverId(receiverId)
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

* Escopo documentado: src/main/java/com/postread/social/model
* Esta documentação usa os arquivos atuais da pasta como fonte de verdade.
* Índice composto único impede duplicidade de amizade na direção requester->receiver.

---
