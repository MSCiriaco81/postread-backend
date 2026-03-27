# resources

## 📌 Propósito

Armazenar configurações e recursos estáticos utilizados pelo backend.

---

## 📂 Arquivos e Responsabilidades

* application.yml → Configuração principal da aplicação (porta, MongoDB, JWT, OpenAPI/Swagger e níveis de log).

---

## 🔧 Funções / Métodos Principais

Nenhuma função pública relevante declarada diretamente nesta pasta.

---

## ▶️ Exemplos de Uso

```java
// Exemplo de configuração em application.yml:
// server.port = 8080
// spring.data.mongodb.uri = ${MONGODB_URI:mongodb://localhost:27017/postread}
// postread.jwt.expiration-ms = ${JWT_EXPIRATION_MS:86400000}
```

---

## 🔗 Dependências

* [../README.md](../README.md)
* [../java/com/postread/shared/README.md](../java/com/postread/shared/README.md)

---

## ⚠️ Regras e Convenções

* Manter responsabilidades da pasta isoladas por camada.
* Evitar dependências cíclicas entre módulos.
* Preservar contratos de entrada/saída (DTOs e assinaturas públicas).

---

## 🧠 Observações

* Escopo documentado: src/main/resources
* Esta documentação usa os arquivos atuais da pasta como fonte de verdade.
* Valores sensíveis e variáveis de ambiente são resolvidos por placeholders `${...}` no `application.yml`.

---
