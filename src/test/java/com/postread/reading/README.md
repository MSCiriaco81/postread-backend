# reading

## 📌 Propósito

Documentar a cobertura de testes do contexto de leitura (controller e service).

---

## 📂 Arquivos e Responsabilidades

* [controller/ReadingControllerTest.java](controller/ReadingControllerTest.java) → Valida endpoints de leitura, autenticação e códigos HTTP.
* [service/ReadingServiceTest.java](service/ReadingServiceTest.java) → Valida regras de registro, busca e autorização para exclusão.
* [../README.md](../README.md) → Guia mestre da suíte de testes.

---

## 🔧 Funções / Métodos Principais

### ReadingControllerTest

* `logReading_valid_returns201`
* `logReading_emptyBookId_returns400`
* `getMyReadings_returnsPage`
* `deleteEntry_returns204`
* `logReading_noAuth_returns401`

---

### ReadingServiceTest

* `logReading_savesEntryAndTriggersSideEffects`
* `logReading_nullDate_usesToday`
* `logReading_withDate_usesProvidedDate`
* `getUserReadings_returnsPage`
* `deleteEntry_owner_deletes`
* `deleteEntry_notOwner_throwsAccessDenied`
* `getEntry_notFound_throws`

---

## ▶️ Exemplos de Uso

```java
// Executar apenas os testes de reading:
// mvn -Dtest=ReadingControllerTest,ReadingServiceTest test
```

---

## 🔗 Dependências

* [controller/ReadingControllerTest.java](controller/ReadingControllerTest.java)
* [service/ReadingServiceTest.java](service/ReadingServiceTest.java)
* [../README.md](../README.md)

---

## ⚠️ Regras e Convenções

* Manter responsabilidades da pasta isoladas por camada.
* Evitar dependências cíclicas entre módulos.
* Preservar contratos de entrada/saída (DTOs e assinaturas públicas).

---

## 🧠 Observações

* Escopo documentado: src/test/java/com/postread/reading
* Esta documentação usa os arquivos atuais da pasta como fonte de verdade.
* Total do módulo: 2 classes de teste, 12 métodos de teste.

---
