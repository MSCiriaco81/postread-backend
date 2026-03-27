# controller

## 📌 Propósito

Expor endpoints HTTP de catálogo de livros, validar entrada e delegar processamento para o serviço.

---

## 📂 Arquivos e Responsabilidades

* BookController.java → Controlador REST para este contexto.
* [../service/README.md](../service/README.md) → Camada que implementa as regras chamadas pelos endpoints.
* [../dto/README.md](../dto/README.md) → Contrato de entrada para criação de livro.

---

## 🔧 Funções / Métodos Principais

### addBook(@Valid @RequestBody BookRequest request)

**Descrição:**
Recebe `POST /api/v1/books`, valida o payload com `@Valid`, exige autenticação (`bearerAuth`) e delega para `bookService.addBook(request)`.

**Parâmetros:**
* request: @Valid @RequestBody BookRequest → parâmetro de entrada da operação.

**Retorno:**
`ResponseEntity<Book>` com status `201 Created` e o livro persistido.

---

### getBook(@PathVariable String bookId)

**Descrição:**
Recebe `GET /api/v1/books/{bookId}` e delega a busca de detalhes para `bookService.getBook(bookId)`.

**Parâmetros:**
* bookId: @PathVariable String → parâmetro de entrada da operação.

**Retorno:**
`ResponseEntity<Book>` com status `200 OK` e os dados do livro.

---

### searchBooks(@RequestParam(required = false) String q, @PageableDefault(size = 20) Pageable pageable)

**Descrição:**
Recebe `GET /api/v1/books` com consulta opcional `q` e paginação, delegando para `bookService.searchBooks(q, pageable)`.

**Parâmetros:**
* q: @RequestParam(required = false) String → texto de busca opcional (título/autor).
* pageable: @PageableDefault(size = 20) Pageable → parâmetros de paginação da busca.

**Retorno:**
`ResponseEntity<Page<Book>>` com status `200 OK` e resultados paginados.

---

## ▶️ Exemplos de Uso

```java
// Exemplo simplificado de endpoints do controlador
// @PostMapping
// public ResponseEntity<Book> addBook(@Valid @RequestBody BookRequest request) {
//     return ResponseEntity.status(HttpStatus.CREATED).body(bookService.addBook(request));
// }
//
// @GetMapping
// public ResponseEntity<Page<Book>> searchBooks(@RequestParam(required = false) String q, Pageable pageable) {
//     return ResponseEntity.ok(bookService.searchBooks(q, pageable));
// }
```

---

## 🔗 Dependências

* [../README.md](../README.md)
* [../dto/README.md](../dto/README.md)
* [../service/README.md](../service/README.md)

---

## ⚠️ Regras e Convenções

* Manter responsabilidades da pasta isoladas por camada.
* Evitar dependências cíclicas entre módulos.
* Preservar contratos de entrada/saída (DTOs e assinaturas públicas).

---

## 🧠 Observações

* Escopo documentado: src/main/java/com/postread/book/controller
* Esta documentação usa os arquivos atuais da pasta como fonte de verdade.
* Métodos expõem anotações OpenAPI (`@Operation`) para documentação da API.

---
