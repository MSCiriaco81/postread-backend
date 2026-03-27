# book

## 📌 Propósito

Centralizar o contexto de catálogo de livros, separando API, DTOs, domínio, persistência e regras de negócio.

---

## 📂 Arquivos e Responsabilidades

* Nenhum arquivo direto nesta pasta (apenas subpastas).
* [controller/README.md](controller/README.md) → Endpoints HTTP do catálogo.
* [dto/README.md](dto/README.md) → Contratos de entrada da API e conversão para entidade.
* [model/README.md](model/README.md) → Entidade de domínio persistida no MongoDB.
* [repository/README.md](repository/README.md) → Operações de persistência e busca.
* [service/README.md](service/README.md) → Regras de negócio e orquestração entre camadas.

---

## 🔧 Funções / Métodos Principais

Nenhuma função pública relevante declarada diretamente nesta pasta.

---

## ▶️ Exemplos de Uso

```java
// Fluxo típico do módulo book:
// POST /api/v1/books -> BookController.addBook(...) -> BookService.addBook(...) -> BookRepository.save(...)
// GET  /api/v1/books/{bookId} -> BookController.getBook(...) -> BookService.getBook(...)
// GET  /api/v1/books?q=java -> BookController.searchBooks(...) -> BookService.searchBooks(...)
```

---

## 🔗 Dependências

* [controller/README.md](controller/README.md)
* [dto/README.md](dto/README.md)
* [model/README.md](model/README.md)
* [repository/README.md](repository/README.md)
* [service/README.md](service/README.md)

---

## ⚠️ Regras e Convenções

* Manter responsabilidades da pasta isoladas por camada.
* Evitar dependências cíclicas entre módulos.
* Preservar contratos de entrada/saída (DTOs e assinaturas públicas).

---

## 🧠 Observações

* Escopo documentado: src/main/java/com/postread/book
* Esta documentação usa os arquivos atuais da pasta como fonte de verdade.
* Navegação rápida: [controller](controller/README.md) | [dto](dto/README.md) | [model](model/README.md) | [repository](repository/README.md) | [service](service/README.md)

---
