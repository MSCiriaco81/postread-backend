# repository

## 📌 Propósito

Abstrair o acesso a dados e operações de persistência.

---

## 📂 Arquivos e Responsabilidades

* BookRepository.java → Repositório para operações de persistência.
* [../model/README.md](../model/README.md) → Entidade principal manipulada pelo repositório.
* [../service/README.md](../service/README.md) → Camada que consome os métodos deste repositório.

---

## 🔧 Funções / Métodos Principais

### searchByText(String query, Pageable pageable)

**Descrição:**
Executa busca textual no MongoDB com `@Query("{ $text: { $search: ?0 } }")`.

**Parâmetros:**
* query: String → termo de busca textual.
* pageable: Pageable → configuração de paginação.

**Retorno:**
`Page<Book>` com resultados paginados da pesquisa textual.

---

### findByAuthorContainingIgnoreCase(String author, Pageable pageable)

**Descrição:**
Busca livros por autor de forma parcial e case-insensitive usando query derivada do Spring Data.

**Parâmetros:**
* author: String → trecho do nome do autor.
* pageable: Pageable → configuração de paginação.

**Retorno:**
`Page<Book>` com resultados paginados filtrados por autor.

---

## ▶️ Exemplos de Uso

```java
// Exemplo de uso no serviço:
// return bookRepository.searchByText(query, pageable);
```

---

## 🔗 Dependências

* [../README.md](../README.md)
* [../model/README.md](../model/README.md)
* [../service/README.md](../service/README.md)

---

## ⚠️ Regras e Convenções

* Manter responsabilidades da pasta isoladas por camada.
* Evitar dependências cíclicas entre módulos.
* Preservar contratos de entrada/saída (DTOs e assinaturas públicas).

---

## 🧠 Observações

* Escopo documentado: src/main/java/com/postread/book/repository
* Esta documentação usa os arquivos atuais da pasta como fonte de verdade.
* Interface estende `MongoRepository<Book, String>` e já inclui operações CRUD padrão.

---
