# dto

## 📌 Propósito

Definir contratos de entrada da API de livros e conversão desses dados para entidade de domínio.

---

## 📂 Arquivos e Responsabilidades

* BookRequest.java → DTO de criação de livro com validações e método de conversão para `Book`.
* [../controller/README.md](../controller/README.md) → Camada que recebe este DTO via HTTP.
* [../service/README.md](../service/README.md) → Camada que utiliza a conversão para persistência.
* [../model/README.md](../model/README.md) → Entidade de destino da conversão.

---

## 🔧 Funções / Métodos Principais

### toBook()

**Descrição:**
Converte os campos do `BookRequest` em uma instância `Book` usando o builder da entidade.

**Parâmetros:**
* Sem parâmetros.

**Retorno:**
`Book` pronto para persistência no repositório.

---

## ▶️ Exemplos de Uso

```java
// Exemplo de uso no serviço:
// Book book = request.toBook();
// return bookRepository.save(book);
```

---

## 🔗 Dependências

* [../README.md](../README.md)
* [../controller/README.md](../controller/README.md)
* [../model/README.md](../model/README.md)
* [../service/README.md](../service/README.md)

---

## ⚠️ Regras e Convenções

* Manter responsabilidades da pasta isoladas por camada.
* Evitar dependências cíclicas entre módulos.
* Preservar contratos de entrada/saída (DTOs e assinaturas públicas).

---

## 🧠 Observações

* Escopo documentado: src/main/java/com/postread/book/dto
* Esta documentação usa os arquivos atuais da pasta como fonte de verdade.
* Campos obrigatórios atuais: `title` e `author` com `@NotBlank`.

---
