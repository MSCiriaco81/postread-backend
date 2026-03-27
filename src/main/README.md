# main

## 📌 Propósito

Centralizar o código-fonte principal da aplicação backend, incluindo lógica Java e configurações de execução.

---

## 📂 Arquivos e Responsabilidades

* Nenhum arquivo direto nesta pasta (apenas subpastas).
* [java/com/postread/README.md](java/com/postread/README.md) → Módulos de domínio, controllers, serviços e regras de negócio da aplicação.
* [resources/README.md](resources/README.md) → Configurações da aplicação e recursos de runtime.

---

## 🔧 Funções / Métodos Principais

Nenhuma função pública relevante declarada diretamente nesta pasta.

---

## ▶️ Exemplos de Uso

```java
// Fluxo típico do backend a partir de src/main:
// 1) API recebe requisição em src/main/java/com/postread/<modulo>/controller
// 2) Regras de negócio em src/main/java/com/postread/<modulo>/service
// 3) Configuração e propriedades em src/main/resources
```

---

## 🔗 Dependências

* [java/com/postread/README.md](java/com/postread/README.md)
* [resources/README.md](resources/README.md)

---

## ⚠️ Regras e Convenções

* Manter responsabilidades da pasta isoladas por camada.
* Evitar dependências cíclicas entre módulos.
* Preservar contratos de entrada/saída (DTOs e assinaturas públicas).

---

## 🧠 Observações

* Escopo documentado: src/main
* Esta documentação usa os arquivos atuais da pasta como fonte de verdade.
* Navegação rápida: [java](java/com/postread/README.md) | [resources](resources/README.md)

---
