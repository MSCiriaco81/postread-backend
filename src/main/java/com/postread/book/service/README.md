# service

## 📌 Propósito

Implementar regras de negócio e coordenar operações entre camadas.

---

## 📂 Arquivos e Responsabilidades

* BookService.java → Serviço com regras de negócio do contexto.
* [../repository/README.md](../repository/README.md) → Persistência e consultas do catálogo.
* [../dto/README.md](../dto/README.md) → Contrato de entrada convertido para entidade.
* [../model/README.md](../model/README.md) → Entidade retornada pelos métodos de serviço.

---

## 🔧 Funções / Métodos Principais

### addBook(BookRequest request)

**Descrição:**
Converte o `BookRequest` para `Book` por meio de `request.toBook()` e persiste no repositório.

**Parâmetros:**
* request: BookRequest → parâmetro de entrada da operação.

**Retorno:**
`Book` criado e salvo.

---

### getBook(String bookId)

**Descrição:**
Busca livro por ID; quando não encontrado, lança `ResourceNotFoundException("Book", bookId)`.

**Parâmetros:**
* bookId: String → parâmetro de entrada da operação.

**Retorno:**
`Book` correspondente ao identificador informado.

---

### searchBooks(String query, Pageable pageable)

**Descrição:**
Executa busca paginada: se `query` vier nula/vazia retorna `findAll(pageable)`, caso contrário usa `searchByText(query, pageable)`.

**Parâmetros:**
* query: String → parâmetro de entrada da operação.
* pageable: Pageable → parâmetro de entrada da operação.

**Retorno:**
`Page<Book>` com resultados paginados.

---

## ▶️ Exemplos de Uso

```java
// Exemplo de chamadas de serviço
// Book created = bookService.addBook(request);
// Book details = bookService.getBook(bookId);
// Page<Book> page = bookService.searchBooks(q, pageable);
```

---

## 🔗 Dependências

* [../README.md](../README.md)
* [../dto/README.md](../dto/README.md)
* [../model/README.md](../model/README.md)
* [../repository/README.md](../repository/README.md)
* shared/

---

## ⚠️ Regras e Convenções

* Manter responsabilidades da pasta isoladas por camada.
* Evitar dependências cíclicas entre módulos.
* Preservar contratos de entrada/saída (DTOs e assinaturas públicas).

---

## 🧠 Observações

* Escopo documentado: src/main/java/com/postread/book/service
* Esta documentação usa os arquivos atuais da pasta como fonte de verdade.
* O método `searchBooks` concentra a regra de fallback entre busca geral e busca textual.

---
