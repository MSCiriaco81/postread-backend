# book

## 📌 Propósito

Documentar a cobertura de testes do contexto de livros (camadas controller e service).

---

## 📂 Arquivos e Responsabilidades

* [controller/BookControllerTest.java](controller/BookControllerTest.java) → Valida endpoints de criação e consulta de livros.
* [service/BookServiceTest.java](service/BookServiceTest.java) → Valida regras de persistência, busca e not found.
* [../README.md](../README.md) → Guia mestre da suíte de testes.

---

## 🔧 Funções / Métodos Principais

### BookControllerTest

* `addBook_returns201`
* `addBook_emptyTitle_returns400`
* `getBook_noAuth_returns200`
* `getBook_notFound_returns404`
* `searchBooks_withQuery_returnsPage`

---

### BookServiceTest

* `addBook_savesAndReturnsBook`
* `getBook_exists_returnsBook`
* `getBook_notFound_throwsException`
* `searchBooks_withQuery_callsTextSearch`
* `searchBooks_emptyQuery_callsFindAll`

---

## ▶️ Exemplos de Uso

```java
// Executar apenas os testes de book:
// mvn -Dtest=BookControllerTest,BookServiceTest test
```

---

## 🔗 Dependências

* [controller/BookControllerTest.java](controller/BookControllerTest.java)
* [service/BookServiceTest.java](service/BookServiceTest.java)
* [../README.md](../README.md)

---

## ⚠️ Regras e Convenções

* Manter responsabilidades da pasta isoladas por camada.
* Evitar dependências cíclicas entre módulos.
* Preservar contratos de entrada/saída (DTOs e assinaturas públicas).

---

## 🧠 Observações

* Escopo documentado: src/test/java/com/postread/book
* Esta documentação usa os arquivos atuais da pasta como fonte de verdade.
* Total do módulo: 2 classes de teste, 10 métodos de teste.

---
