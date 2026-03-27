# postread

## 📌 Propósito

Centralizar o pacote raiz da aplicação backend, incluindo o ponto de entrada Spring Boot e os módulos funcionais.

---

## 📂 Arquivos e Responsabilidades

* PostreadApplication.java → Classe de bootstrap da aplicação (`@SpringBootApplication`).
* [auth/README.md](auth/README.md) → Contexto de autenticação.
* [book/README.md](book/README.md) → Contexto de catálogo de livros.
* [feed/README.md](feed/README.md) → Contexto de feed social.
* [reading/README.md](reading/README.md) → Contexto de registros de leitura.
* [shared/README.md](shared/README.md) → Componentes transversais (config, segurança e exceções).
* [social/README.md](social/README.md) → Contexto de amizades e conexões sociais.
* [streak/README.md](streak/README.md) → Contexto de streaks e desafios.
* [user/README.md](user/README.md) → Contexto de perfil e dados de usuário.

---

## 🔧 Funções / Métodos Principais

### main(String[] args)

**Descrição:**
Ponto de entrada da aplicação. Inicializa o contexto Spring e inicia o backend via `SpringApplication.run(...)`.

**Parâmetros:**
* args: String[] → parâmetro de entrada da operação.

**Retorno:**
`void`.

---

## ▶️ Exemplos de Uso

```java
// Inicialização da aplicação
// public static void main(String[] args) {
//     SpringApplication.run(PostreadApplication.class, args);
// }
```

---

## 🔗 Dependências

* [auth/README.md](auth/README.md)
* [book/README.md](book/README.md)
* [feed/README.md](feed/README.md)
* [reading/README.md](reading/README.md)
* [shared/README.md](shared/README.md)
* [social/README.md](social/README.md)
* [streak/README.md](streak/README.md)
* [user/README.md](user/README.md)

---

## ⚠️ Regras e Convenções

* Manter responsabilidades da pasta isoladas por camada.
* Evitar dependências cíclicas entre módulos.
* Preservar contratos de entrada/saída (DTOs e assinaturas públicas).

---

## 🧠 Observações

* Escopo documentado: src/main/java/com/postread
* Esta documentação usa os arquivos atuais da pasta como fonte de verdade.
* Navegação rápida: [auth](auth/README.md) | [book](book/README.md) | [feed](feed/README.md) | [reading](reading/README.md) | [shared](shared/README.md) | [social](social/README.md) | [streak](streak/README.md) | [user](user/README.md)

---
