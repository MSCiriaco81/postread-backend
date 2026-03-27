# social

## 📌 Propósito

Centralizar o contexto social da aplicação: solicitações de amizade, lista de amigos e pendências.

---

## 📂 Arquivos e Responsabilidades

* Nenhum arquivo direto nesta pasta (apenas subpastas).
* [controller/README.md](controller/README.md) → Endpoints de relacionamento social autenticados.
* [dto/README.md](dto/README.md) → Espaço para contratos específicos do contexto social.
* [model/README.md](model/README.md) → Entidade de amizade e status do relacionamento.
* [repository/README.md](repository/README.md) → Consultas de amizade entre usuários.
* [service/README.md](service/README.md) → Regras de negócio de amizade e listagem social.

---

## 🔧 Funções / Métodos Principais

Nenhuma função pública relevante declarada diretamente nesta pasta.

---

## ▶️ Exemplos de Uso

```java
// Fluxo típico do módulo social:
// POST /api/v1/social/friends/request/{receiverId} -> SocialController.sendRequest(...)
//  -> SocialService.sendFriendRequest(requesterId, receiverId)
//  -> FriendshipRepository.save(...)
```

---

## 🔗 Dependências

* [controller/README.md](controller/README.md)
* [dto/README.md](dto/README.md)
* [model/README.md](model/README.md)
* [repository/README.md](repository/README.md)
* [service/README.md](service/README.md)

---

## ⚠️ Regras e Convenções

* Manter responsabilidades da pasta isoladas por camada.
* Evitar dependências cíclicas entre módulos.
* Preservar contratos de entrada/saída (DTOs e assinaturas públicas).

---

## 🧠 Observações

* Escopo documentado: src/main/java/com/postread/social
* Esta documentação usa os arquivos atuais da pasta como fonte de verdade.
* Navegação rápida: [controller](controller/README.md) | [dto](dto/README.md) | [model](model/README.md) | [repository](repository/README.md) | [service](service/README.md)

---
