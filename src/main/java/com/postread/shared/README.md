# shared

## 📌 Propósito

Centralizar recursos transversais da aplicação: configuração, segurança JWT e tratamento global de exceções.

---

## 📂 Arquivos e Responsabilidades

* Nenhum arquivo direto nesta pasta (apenas subpastas).
* [config/README.md](config/README.md) → Configurações de MongoDB, segurança e OpenAPI.
* [exception/README.md](exception/README.md) → Exceções de domínio e padronização de respostas de erro.
* [security/README.md](security/README.md) → Geração/validação de JWT e filtro de autenticação.

---

## 🔧 Funções / Métodos Principais

Nenhuma função pública relevante declarada diretamente nesta pasta.

---

## ▶️ Exemplos de Uso

```java
// Exemplo de uso transversal:
// - SecurityConfig registra o JwtAuthenticationFilter na cadeia de segurança.
// - JwtService gera token no login e valida token por request.
// - GlobalExceptionHandler converte exceções em ProblemDetail padronizado.
```

---

## 🔗 Dependências

* [config/README.md](config/README.md)
* [exception/README.md](exception/README.md)
* [security/README.md](security/README.md)

---

## ⚠️ Regras e Convenções

* Manter responsabilidades da pasta isoladas por camada.
* Evitar dependências cíclicas entre módulos.
* Preservar contratos de entrada/saída (DTOs e assinaturas públicas).

---

## 🧠 Observações

* Escopo documentado: src/main/java/com/postread/shared
* Esta documentação usa os arquivos atuais da pasta como fonte de verdade.
* Navegação rápida: [config](config/README.md) | [exception](exception/README.md) | [security](security/README.md)

---
