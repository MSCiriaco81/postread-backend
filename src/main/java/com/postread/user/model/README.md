# model

## 📌 Propósito

Representar entidades e estruturas de domínio persistidas ou manipuladas internamente.

---

## 📂 Arquivos e Responsabilidades

* User.java → Entidade MongoDB de usuário (`@Document(collection = "users")`).
* [../repository/README.md](../repository/README.md) → Camada que persiste e consulta a entidade.
* [../service/README.md](../service/README.md) → Camada que aplica regras sobre os dados de usuário.

---

## 🔧 Funções / Métodos Principais

Nenhuma função pública de negócio declarada; a classe usa Lombok para geração de boilerplate.

---

## ▶️ Exemplos de Uso

```java
// Exemplo de campos padrão no modelo:
// roles = Set.of("ROLE_USER")
// active = true
```

---

## 🔗 Dependências

* [../README.md](../README.md)
* [../repository/README.md](../repository/README.md)
* [../service/README.md](../service/README.md)

---

## ⚠️ Regras e Convenções

* Manter responsabilidades da pasta isoladas por camada.
* Evitar dependências cíclicas entre módulos.
* Preservar contratos de entrada/saída (DTOs e assinaturas públicas).

---

## 🧠 Observações

* Escopo documentado: src/main/java/com/postread/user/model
* Esta documentação usa os arquivos atuais da pasta como fonte de verdade.
* `username` e `email` possuem índice único no MongoDB.

---
