# dto

## 📌 Propósito

Reservar o espaço de contratos de entrada/saída para o contexto de feed.

---

## 📂 Arquivos e Responsabilidades

* Nenhuma classe Java implementada nesta pasta no estado atual.
* [../controller/README.md](../controller/README.md) → Camada que pode consumir DTOs futuros do feed.
* [../service/README.md](../service/README.md) → Camada que pode receber/retornar DTOs futuros.

---

## 🔧 Funções / Métodos Principais

Nenhuma função pública relevante declarada diretamente nesta pasta.

---

## ▶️ Exemplos de Uso

```java
// Atualmente o feed retorna diretamente Page<FeedEvent> no controller.
// Esta pasta pode receber DTOs dedicados quando houver necessidade de projeções/contratos específicos.
```

---

## 🔗 Dependências

* [../README.md](../README.md)
* [../controller/README.md](../controller/README.md)
* [../service/README.md](../service/README.md)

---

## ⚠️ Regras e Convenções

* Manter responsabilidades da pasta isoladas por camada.
* Evitar dependências cíclicas entre módulos.
* Preservar contratos de entrada/saída (DTOs e assinaturas públicas).

---

## 🧠 Observações

* Escopo documentado: src/main/java/com/postread/feed/dto
* Esta documentação usa os arquivos atuais da pasta como fonte de verdade.
* No estado atual do código, não há classes DTO implementadas neste contexto.

---
