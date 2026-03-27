# user

## 📌 Propósito

Documentar a cobertura de testes do contexto de usuário.

---

## 📂 Arquivos e Responsabilidades

* [service/UserServiceTest.java](service/UserServiceTest.java) → Valida consulta e atualização de perfil de usuário.
* [../README.md](../README.md) → Guia mestre da suíte de testes.

---

## 🔧 Funções / Métodos Principais

### UserServiceTest

* `getProfile_userExists_returnsResponse`
* `getProfile_userNotFound_throwsException`
* `getProfileByUsername_found_returnsResponse`
* `updateProfile_updatesFields`
* `updateProfile_nullFields_doesNotOverwrite`

---

## ▶️ Exemplos de Uso

```java
// Executar apenas os testes de user:
// mvn -Dtest=UserServiceTest test
```

---

## 🔗 Dependências

* [service/UserServiceTest.java](service/UserServiceTest.java)
* [../README.md](../README.md)

---

## ⚠️ Regras e Convenções

* Manter responsabilidades da pasta isoladas por camada.
* Evitar dependências cíclicas entre módulos.
* Preservar contratos de entrada/saída (DTOs e assinaturas públicas).

---

## 🧠 Observações

* Escopo documentado: src/test/java/com/postread/user
* Esta documentação usa os arquivos atuais da pasta como fonte de verdade.
* Total do módulo: 1 classe de teste, 5 métodos de teste.

---
