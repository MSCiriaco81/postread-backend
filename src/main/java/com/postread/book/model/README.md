# model

## 📌 Propósito

Representar entidades e estruturas de domínio persistidas ou manipuladas internamente.

---

## 📂 Arquivos e Responsabilidades

* Book.java → Entidade MongoDB (`@Document(collection = "books")`) com metadados de busca textual e criação.
* [../repository/README.md](../repository/README.md) → Camada que persiste e consulta esta entidade.
* [../dto/README.md](../dto/README.md) → Camada que converte payload para esta entidade.

---

## 🔧 Funções / Métodos Principais

Nenhuma função pública de negócio declarada; a entidade usa Lombok para gerar acessores e utilitários.

---

## ▶️ Exemplos de Uso

```java
// Exemplo de uso no repositório:
// Page<Book> page = bookRepository.searchByText("clean code", pageable);
```

---

## 🔗 Dependências

* [../README.md](../README.md)
* [../dto/README.md](../dto/README.md)
* [../repository/README.md](../repository/README.md)

---

## ⚠️ Regras e Convenções

* Manter responsabilidades da pasta isoladas por camada.
* Evitar dependências cíclicas entre módulos.
* Preservar contratos de entrada/saída (DTOs e assinaturas públicas).

---

## 🧠 Observações

* Escopo documentado: src/main/java/com/postread/book/model
* Esta documentação usa os arquivos atuais da pasta como fonte de verdade.
* `title` e `author` possuem `@TextIndexed` com pesos diferentes para busca textual no MongoDB.

---
