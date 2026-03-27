# reading

## 📌 Propósito

Centralizar o contexto de registro de leitura, histórico do usuário e integração com streak/feed social.

---

## 📂 Arquivos e Responsabilidades

* Nenhum arquivo direto nesta pasta (apenas subpastas).
* [controller/README.md](controller/README.md) → Endpoints para registrar, consultar e remover entradas de leitura.
* [dto/README.md](dto/README.md) → Contrato de entrada para log de leitura.
* [model/README.md](model/README.md) → Entidade de sessão de leitura persistida no MongoDB.
* [repository/README.md](repository/README.md) → Consultas de leitura por usuário, período e estatísticas agregadas.
* [service/README.md](service/README.md) → Regras de negócio de leitura e integrações com streak/feed/social.

---

## 🔧 Funções / Métodos Principais

Nenhuma função pública relevante declarada diretamente nesta pasta.

---

## ▶️ Exemplos de Uso

```java
// Fluxo típico do módulo reading:
// POST /api/v1/readings -> ReadingController.logReading(...)
//  -> ReadingService.logReading(userId, request)
//  -> ReadingEntryRepository.save(...)
//  -> StreakService.processReadingEntry(...)
//  -> FeedService.createEvent(...) para amigos
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

* Escopo documentado: src/main/java/com/postread/reading
* Esta documentação usa os arquivos atuais da pasta como fonte de verdade.
* Navegação rápida: [controller](controller/README.md) | [dto](dto/README.md) | [model](model/README.md) | [repository](repository/README.md) | [service](service/README.md)

---
