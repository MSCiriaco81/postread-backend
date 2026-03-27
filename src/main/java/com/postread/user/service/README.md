# service

## 📌 Propósito

Implementar regras de negócio e coordenar operações entre camadas.

---

## 📂 Arquivos e Responsabilidades

* UserDetailsServiceImpl.java → Classe Java deste módulo.
* UserService.java → Serviço com regras de negócio do contexto.
* [../repository/README.md](../repository/README.md) → Persistência e consultas de usuário.
* [../dto/README.md](../dto/README.md) → Contratos usados para entrada/saída.

---

## 🔧 Funções / Métodos Principais

### loadUserByUsername(String email)

**Descrição:**
Carrega usuário por email para autenticação no Spring Security, mapeando roles para `GrantedAuthority`.

**Parâmetros:**
* email: String → parâmetro de entrada da operação.

**Retorno:**
`UserDetails`.

---

### getProfile(String userId)

**Descrição:**
Busca usuário por ID e converte para `UserResponse`; quando não encontrado lança `ResourceNotFoundException`.

**Parâmetros:**
* userId: String → parâmetro de entrada da operação.

**Retorno:**
`UserResponse`.

---

### getProfileByUsername(String username)

**Descrição:**
Busca usuário por username e converte para `UserResponse`.

**Parâmetros:**
* username: String → parâmetro de entrada da operação.

**Retorno:**
`UserResponse`.

---

### updateProfile(String userId, UpdateProfileRequest request)

**Descrição:**
Atualiza campos de perfil (`bio`, `profilePicture`) quando informados e salva alterações.

**Parâmetros:**
* userId: String → parâmetro de entrada da operação.
* request: UpdateProfileRequest → parâmetro de entrada da operação.

**Retorno:**
`UserResponse`.

---

### getById(String userId)

**Descrição:**
Retorna entidade `User` por ID para uso interno de outros módulos.

**Parâmetros:**
* userId: String → parâmetro de entrada da operação.

**Retorno:**
`User`.

---

### getByEmail(String email)

**Descrição:**
Retorna entidade `User` por email para uso interno de autenticação e resolução de usuário atual.

**Parâmetros:**
* email: String → parâmetro de entrada da operação.

**Retorno:**
`User`.

---

## ▶️ Exemplos de Uso

```java
// Exemplo de chamadas de serviço
// UserResponse me = userService.getProfileByUsername(username);
// UserResponse updated = userService.updateProfile(userId, request);
// User user = userService.getByEmail(email);
```

---

## 🔗 Dependências

* [../README.md](../README.md)
* [../dto/README.md](../dto/README.md)
* [../model/README.md](../model/README.md)
* [../repository/README.md](../repository/README.md)
* [../../shared/README.md](../../shared/README.md)

---

## ⚠️ Regras e Convenções

* Manter responsabilidades da pasta isoladas por camada.
* Evitar dependências cíclicas entre módulos.
* Preservar contratos de entrada/saída (DTOs e assinaturas públicas).

---

## 🧠 Observações

* Escopo documentado: src/main/java/com/postread/user/service
* Esta documentação usa os arquivos atuais da pasta como fonte de verdade.
* `UserDetailsServiceImpl` usa email como username para autenticação.

---
