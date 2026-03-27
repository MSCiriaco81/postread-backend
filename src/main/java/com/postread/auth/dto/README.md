# dto

## 📌 Propósito

Definir os contratos de entrada e saída usados no fluxo de autenticação, garantindo consistência entre API e serviço.

---

## 📂 Arquivos e Responsabilidades

* AuthResponse.java → Payload de saída com `token`, `userId`, `username` e `email`.
* LoginRequest.java → Payload de entrada para login com `email` e `password` obrigatórios.
* RegisterRequest.java → Payload de entrada para cadastro com validação de tamanho e formato.
* [../controller/README.md](../controller/README.md) → Camada que consome e devolve estes contratos.
* [../service/README.md](../service/README.md) → Camada que processa os dados destes contratos.

---

## 🔧 Funções / Métodos Principais

Nenhuma função pública de negócio; esta pasta concentra `record`s para transporte de dados e validação de entrada.

---

## ▶️ Exemplos de Uso

```java
// Exemplo de uso de DTO em endpoint
// public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request)
//
// RegisterRequest:
// - username: @NotBlank, @Size(min = 3, max = 30)
// - email:    @NotBlank, @Email
// - password: @NotBlank, @Size(min = 8, max = 100)
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
* Alterações em campos/nomes de DTO exigem revisão no controller e no service.

---

## 🧠 Observações

* Escopo documentado: src/main/java/com/postread/auth/dto
* Esta documentação usa os arquivos atuais da pasta como fonte de verdade.
* Preferir validações de entrada declarativas nos DTOs quando aplicável.
* `LoginRequest` valida apenas presença dos campos; validações adicionais podem ser tratadas no service/security.

---
