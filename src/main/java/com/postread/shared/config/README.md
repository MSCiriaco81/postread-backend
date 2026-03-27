# config

## 📌 Propósito

Centralizar configurações técnicas da aplicação e integrações de infraestrutura.

---

## 📂 Arquivos e Responsabilidades

* MongoConfig.java → Habilita auditoria Mongo e descoberta de repositórios (`@EnableMongoAuditing`, `@EnableMongoRepositories`).
* OpenApiConfig.java → Define metadados OpenAPI e esquema de segurança `bearerAuth`.
* SecurityConfig.java → Define cadeia de segurança stateless JWT, autenticação e CORS.
* [../security/README.md](../security/README.md) → Componentes JWT usados na configuração de segurança.

---

## 🔧 Funções / Métodos Principais

### securityFilterChain(HttpSecurity http)

**Descrição:**
Configura segurança HTTP stateless: desativa CSRF/basic/form login, aplica CORS, libera endpoints públicos, protege demais rotas e registra `JwtAuthenticationFilter`.

**Parâmetros:**
* http: HttpSecurity → parâmetro de entrada da operação.

**Retorno:**
`SecurityFilterChain` configurado.

---

### authenticationProvider()

**Descrição:**
Cria `DaoAuthenticationProvider` com `UserDetailsService` e `PasswordEncoder`.

**Parâmetros:**
* Sem parâmetros.

**Retorno:**
`AuthenticationProvider`.

---

### authenticationManager(AuthenticationConfiguration config)

**Descrição:**
Obtém `AuthenticationManager` a partir da configuração de autenticação do Spring.

**Parâmetros:**
* config: AuthenticationConfiguration → parâmetro de entrada da operação.

**Retorno:**
`AuthenticationManager`.

---

### passwordEncoder()

**Descrição:**
Cria codificador de senha `BCryptPasswordEncoder` com força 12.

**Parâmetros:**
* Sem parâmetros.

**Retorno:**
`PasswordEncoder`.

---

### corsConfigurationSource()

**Descrição:**
Define política CORS global com origens `http://localhost:*` e `https://*.postread.com`, métodos padrão e credenciais habilitadas.

**Parâmetros:**
* Sem parâmetros.

**Retorno:**
`CorsConfigurationSource`.

---

## ▶️ Exemplos de Uso

```java
// Exemplo simplificado de configuração de segurança:
// .authorizeHttpRequests(auth -> auth
//     .requestMatchers(PUBLIC_ENDPOINTS).permitAll()
//     .requestMatchers(HttpMethod.GET, "/api/v1/books/**").permitAll()
//     .anyRequest().authenticated())
```

---

## 🔗 Dependências

* [../README.md](../README.md)
* [../security/README.md](../security/README.md)

---

## ⚠️ Regras e Convenções

* Manter responsabilidades da pasta isoladas por camada.
* Evitar dependências cíclicas entre módulos.
* Preservar contratos de entrada/saída (DTOs e assinaturas públicas).

---

## 🧠 Observações

* Escopo documentado: src/main/java/com/postread/shared/config
* Esta documentação usa os arquivos atuais da pasta como fonte de verdade.
* `MongoConfig` e `OpenApiConfig` não expõem métodos públicos; atuam via anotações de configuração.

---
