# auth

## 📌 Propósito

Documentar a cobertura de testes do contexto de autenticação (camadas controller e service).

---

## 📂 Arquivos e Responsabilidades

* [controller/AuthControllerTest.java](controller/AuthControllerTest.java) → Valida contratos HTTP de registro/login e cenários de erro de entrada/conflito.
* [service/AuthServiceTest.java](service/AuthServiceTest.java) → Valida regras de negócio de cadastro e autenticação.
* [../README.md](../README.md) → Guia mestre da suíte de testes.

---

## 🔧 Funções / Métodos Principais

### AuthControllerTest

* `register_validData_returns201`
* `register_shortUsername_returns400`
* `register_invalidEmail_returns400`
* `register_duplicateEmail_returns409`
* `login_validCredentials_returns200`
* `login_emptyFields_returns400`

---

### AuthServiceTest

* `register_success`
* `register_emailAlreadyExists_throwsConflict`
* `register_usernameAlreadyExists_throwsConflict`
* `login_success`
* `login_badCredentials_throws`

---

## ▶️ Exemplos de Uso

```java
// Executar apenas os testes de auth:
// mvn -Dtest=AuthControllerTest,AuthServiceTest test
```

---

## 🔗 Dependências

* [controller/AuthControllerTest.java](controller/AuthControllerTest.java)
* [service/AuthServiceTest.java](service/AuthServiceTest.java)
* [../README.md](../README.md)

---

## ⚠️ Regras e Convenções

* Manter responsabilidades da pasta isoladas por camada.
* Evitar dependências cíclicas entre módulos.
* Preservar contratos de entrada/saída (DTOs e assinaturas públicas).

---

## 🧠 Observações

* Escopo documentado: src/test/java/com/postread/auth
* Esta documentação usa os arquivos atuais da pasta como fonte de verdade.
* Total do módulo: 2 classes de teste, 11 métodos de teste.

---
