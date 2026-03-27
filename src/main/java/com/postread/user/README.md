# user

## 📌 Propósito

Centralizar gerenciamento de perfil de usuário, dados de autenticação e acesso a informações de conta.

---

## 📂 Arquivos e Responsabilidades

* Nenhum arquivo direto nesta pasta (apenas subpastas).
* [controller/README.md](controller/README.md) → Endpoints de perfil do usuário autenticado e consulta pública por ID/username.
* [dto/README.md](dto/README.md) → Contratos de atualização de perfil e resposta de usuário.
* [model/README.md](model/README.md) → Entidade de usuário persistida no MongoDB.
* [repository/README.md](repository/README.md) → Consultas de usuário por id, email e username.
* [service/README.md](service/README.md) → Regras de negócio de perfil e integração com Spring Security.

---

## 🔧 Funções / Métodos Principais

Nenhuma função pública relevante declarada diretamente nesta pasta.

---

## ▶️ Exemplos de Uso

```java
// Fluxo típico do módulo user:
// GET /api/v1/users/me -> UserController.getMyProfile(...)
//  -> UserService.getByEmail(principal.getUsername())
//  -> UserService.getProfileByUsername(...)
//  -> UserResponse
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

* Escopo documentado: src/main/java/com/postread/user
* Esta documentação usa os arquivos atuais da pasta como fonte de verdade.
* Navegação rápida: [controller](controller/README.md) | [dto](dto/README.md) | [model](model/README.md) | [repository](repository/README.md) | [service](service/README.md)

---
