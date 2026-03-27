# dto

## 📌 Propósito

Definir contratos de entrada e saída entre API e serviços.

---

## 📂 Arquivos e Responsabilidades

* UpdateProfileRequest.java → DTO para atualização de perfil (`bio`, `profilePicture`).
* UserResponse.java → DTO de saída com dados públicos do usuário.
* [../controller/README.md](../controller/README.md) → Camada que consome e retorna estes contratos.
* [../service/README.md](../service/README.md) → Camada que monta `UserResponse` e aplica atualização.

---

## 🔧 Funções / Métodos Principais

### from(User user)

**Descrição:**
Converte entidade `User` para `UserResponse`.

**Parâmetros:**
* user: User → parâmetro de entrada da operação.

**Retorno:**
`UserResponse`.

---

## ▶️ Exemplos de Uso

```java
// Exemplo de validação em UpdateProfileRequest:
// - bio: @Size(max = 200)
// - profilePicture: campo opcional
```

---

## 🔗 Dependências

* [../README.md](../README.md)
* [../controller/README.md](../controller/README.md)
* [../service/README.md](../service/README.md)

---

## ⚠️ Regras e Convenções

* Manter responsabilidades da pasta isoladas por camada.
* Evitar dependências cíclicas entre módulos.
* Preservar contratos de entrada/saída (DTOs e assinaturas públicas).

---

## 🧠 Observações

* Escopo documentado: src/main/java/com/postread/user/dto
* Esta documentação usa os arquivos atuais da pasta como fonte de verdade.
* `UserResponse.from(...)` é utilitário estático de mapeamento entre domínio e contrato de API.

---
