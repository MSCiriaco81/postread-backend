# security

## 📌 Propósito

Concentrar autenticação, autorização e componentes de segurança da API.

---

## 📂 Arquivos e Responsabilidades

* JwtAuthenticationFilter.java → Filtro por requisição que extrai e valida JWT do header `Authorization`.
* JwtService.java → Serviço de geração, leitura e validação de tokens JWT.
* [../config/README.md](../config/README.md) → Configuração que registra o filtro na cadeia de segurança.

---

## 🔧 Funções / Métodos Principais

### generateToken(UserDetails userDetails)

**Descrição:**
Gera token JWT sem claims extras para o usuário informado.

**Parâmetros:**
* userDetails: UserDetails → parâmetro de entrada da operação.

**Retorno:**
`String` com JWT assinado.

---

### generateToken(Map<String, Object> extraClaims, UserDetails userDetails)

**Descrição:**
Gera token JWT com claims adicionais, subject do usuário e expiração configurada.

**Parâmetros:**
* extraClaims: Map<String, Object> → parâmetro de entrada da operação.
* userDetails: UserDetails → parâmetro de entrada da operação.

**Retorno:**
`String` com JWT assinado.

---

### extractUsername(String token)

**Descrição:**
Extrai o `subject` (username/email) do token.

**Parâmetros:**
* token: String → parâmetro de entrada da operação.

**Retorno:**
`String` com username.

---

### isTokenValid(String token, UserDetails userDetails)

**Descrição:**
Valida assinatura/estrutura do token, compara subject com usuário e verifica expiração.

**Parâmetros:**
* token: String → parâmetro de entrada da operação.
* userDetails: UserDetails → parâmetro de entrada da operação.

**Retorno:**
`boolean` indicando validade.

---

## ▶️ Exemplos de Uso

```java
// Exemplo de geração de token:
// String jwt = jwtService.generateToken(userDetails);
```

---

## 🔗 Dependências

* [../README.md](../README.md)
* [../config/README.md](../config/README.md)

---

## ⚠️ Regras e Convenções

* Manter responsabilidades da pasta isoladas por camada.
* Evitar dependências cíclicas entre módulos.
* Preservar contratos de entrada/saída (DTOs e assinaturas públicas).

---

## 🧠 Observações

* Escopo documentado: src/main/java/com/postread/shared/security
* Esta documentação usa os arquivos atuais da pasta como fonte de verdade.
* O filtro JWT não bloqueia requisições sem token; a decisão de acesso é da `SecurityConfig`.

---
