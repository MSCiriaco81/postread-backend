# shared

## 📌 Propósito

Documentar a cobertura de testes dos componentes transversais (tratamento de exceção e JWT).

---

## 📂 Arquivos e Responsabilidades

* [exception/GlobalExceptionHandlerTest.java](exception/GlobalExceptionHandlerTest.java) → Valida mapeamento de exceções para ProblemDetail/status HTTP.
* [security/JwtServiceTest.java](security/JwtServiceTest.java) → Valida geração e validação de tokens JWT.
* [../README.md](../README.md) → Guia mestre da suíte de testes.

---

## 🔧 Funções / Métodos Principais

### GlobalExceptionHandlerTest

* `handleNotFound_returns404`
* `handleBusiness_returns400`
* `handleConflict_returns409`
* `handleBadCredentials_returns401`
* `handleAccessDenied_returns403`
* `handleValidation_returns400WithFieldErrors`
* `handleGeneral_returns500`

---

### JwtServiceTest

* `generateToken_returnsNonBlankToken`
* `extractUsername_returnsCorrectEmail`
* `isTokenValid_freshToken_returnsTrue`
* `isTokenValid_wrongUser_returnsFalse`
* `isTokenValid_expiredToken_returnsFalse`

---

## ▶️ Exemplos de Uso

```java
// Executar apenas os testes de shared:
// mvn -Dtest=GlobalExceptionHandlerTest,JwtServiceTest test
```

---

## 🔗 Dependências

* [exception/GlobalExceptionHandlerTest.java](exception/GlobalExceptionHandlerTest.java)
* [security/JwtServiceTest.java](security/JwtServiceTest.java)
* [../README.md](../README.md)

---

## ⚠️ Regras e Convenções

* Manter responsabilidades da pasta isoladas por camada.
* Evitar dependências cíclicas entre módulos.
* Preservar contratos de entrada/saída (DTOs e assinaturas públicas).

---

## 🧠 Observações

* Escopo documentado: src/test/java/com/postread/shared
* Esta documentação usa os arquivos atuais da pasta como fonte de verdade.
* Total do módulo: 2 classes de teste, 12 métodos de teste.

---
