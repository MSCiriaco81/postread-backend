# exception

## 📌 Propósito

Padronizar exceções de domínio e o tratamento de erros da aplicação.

---

## 📂 Arquivos e Responsabilidades

* BusinessException.java → Exceção de regra de negócio.
* ConflictException.java → Exceção para conflitos de estado (ex.: duplicidade).
* ResourceNotFoundException.java → Exceção para recurso inexistente.
* GlobalExceptionHandler.java → Conversão centralizada de exceções para `ProblemDetail`.

---

## 🔧 Funções / Métodos Principais

### handleNotFound(ResourceNotFoundException ex)

**Descrição:**
Converte `ResourceNotFoundException` em resposta `404 Not Found` com tipo `/errors/not-found`.

**Parâmetros:**
* ex: ResourceNotFoundException → parâmetro de entrada da operação.

**Retorno:**
`ProblemDetail`.

---

### handleBusiness(BusinessException ex)

**Descrição:**
Converte `BusinessException` em resposta `400 Bad Request` com tipo `/errors/business`.

**Parâmetros:**
* ex: BusinessException → parâmetro de entrada da operação.

**Retorno:**
`ProblemDetail`.

---

### handleConflict(ConflictException ex)

**Descrição:**
Converte `ConflictException` em resposta `409 Conflict` com tipo `/errors/conflict`.

**Parâmetros:**
* ex: ConflictException → parâmetro de entrada da operação.

**Retorno:**
`ProblemDetail`.

---

### handleBadCredentials(BadCredentialsException ex)

**Descrição:**
Converte `BadCredentialsException` em resposta `401 Unauthorized` com mensagem fixa `Invalid credentials`.

**Parâmetros:**
* ex: BadCredentialsException → parâmetro de entrada da operação.

**Retorno:**
`ProblemDetail`.

---

### handleAccessDenied(AccessDeniedException ex)

**Descrição:**
Converte `AccessDeniedException` em resposta `403 Forbidden`.

**Parâmetros:**
* ex: AccessDeniedException → parâmetro de entrada da operação.

**Retorno:**
`ProblemDetail`.

---

### handleValidation(MethodArgumentNotValidException ex)

**Descrição:**
Converte erros de validação em `400 Bad Request` e inclui mapa `fieldErrors` no `ProblemDetail`.

**Parâmetros:**
* ex: MethodArgumentNotValidException → parâmetro de entrada da operação.

**Retorno:**
`ProblemDetail`.

---

### handleGeneral(Exception ex)

**Descrição:**
Captura exceções não tratadas, registra log de erro e retorna `500 Internal Server Error` padronizado.

**Parâmetros:**
* ex: Exception → parâmetro de entrada da operação.

**Retorno:**
`ProblemDetail`.

---

## ▶️ Exemplos de Uso

```java
// Exemplo de criação de exceção de domínio:
// throw new ResourceNotFoundException("Book", bookId);
```

---

## 🔗 Dependências

* [../README.md](../README.md)

---

## ⚠️ Regras e Convenções

* Manter responsabilidades da pasta isoladas por camada.
* Evitar dependências cíclicas entre módulos.
* Preservar contratos de entrada/saída (DTOs e assinaturas públicas).

---

## 🧠 Observações

* Escopo documentado: src/main/java/com/postread/shared/exception
* Esta documentação usa os arquivos atuais da pasta como fonte de verdade.
* Todos os handlers adicionam `timestamp` no `ProblemDetail`.

---
