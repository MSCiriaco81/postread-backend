# feed

## 📌 Propósito

Centralizar o contexto de feed social, incluindo exposição de feed do usuário, persistência de eventos e ordenação cronológica.

---

## 📂 Arquivos e Responsabilidades

* Nenhum arquivo direto nesta pasta (apenas subpastas).
* [controller/README.md](controller/README.md) → Endpoint HTTP para consulta do feed autenticado.
* [dto/README.md](dto/README.md) → Espaço reservado para contratos de entrada/saída do contexto.
* [model/README.md](model/README.md) → Entidade de evento do feed e tipos de evento.
* [repository/README.md](repository/README.md) → Consultas e persistência dos eventos de feed.
* [service/README.md](service/README.md) → Regras de criação e recuperação do feed.

---

## 🔧 Funções / Métodos Principais

Nenhuma função pública relevante declarada diretamente nesta pasta.

---

## ▶️ Exemplos de Uso

```java
// Fluxo típico do módulo feed:
// GET /api/v1/feed -> FeedController.getFeed(principal, pageable)
//  -> UserService.getByEmail(principal.getUsername())
//  -> FeedService.getFeed(userId, pageable)
//  -> FeedEventRepository.findByTargetUserIdOrderByCreatedAtDesc(...)
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

* Escopo documentado: src/main/java/com/postread/feed
* Esta documentação usa os arquivos atuais da pasta como fonte de verdade.
* Navegação rápida: [controller](controller/README.md) | [dto](dto/README.md) | [model](model/README.md) | [repository](repository/README.md) | [service](service/README.md)

---
