# repository

## 📌 Propósito

Abstrair o acesso a dados e operações de persistência.

---

## 📂 Arquivos e Responsabilidades

* UserRepository.java → Repositório para operações de persistência.
* [../model/README.md](../model/README.md) → Entidade principal manipulada pelo repositório.
* [../service/README.md](../service/README.md) → Camada consumidora das consultas.

---

## 🔧 Funções / Métodos Principais

### findByEmail(String email)

**Descrição:**
Busca usuário por email.

**Parâmetros:**
* email: String → email do usuário.

**Retorno:**
`Optional<User>`.

---

### findByUsername(String username)

**Descrição:**
Busca usuário por username.

**Parâmetros:**
* username: String → nome de usuário.

**Retorno:**
`Optional<User>`.

---

### existsByEmail(String email)

**Descrição:**
Verifica existência de usuário por email.

**Parâmetros:**
* email: String → email para verificação.

**Retorno:**
`boolean`.

---

### existsByUsername(String username)

**Descrição:**
Verifica existência de usuário por username.

**Parâmetros:**
* username: String → username para verificação.

**Retorno:**
`boolean`.

---

## ▶️ Exemplos de Uso

```java
// Exemplo de uso no serviço:
// return userRepository.findByEmail(email)
//     .orElseThrow(() -> new ResourceNotFoundException("User not found: " + email));
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

* Escopo documentado: src/main/java/com/postread/user/repository
* Esta documentação usa os arquivos atuais da pasta como fonte de verdade.
* A interface estende `MongoRepository<User, String>` e herda operações CRUD padrão.

---
