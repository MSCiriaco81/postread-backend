# social

## 📌 Propósito

Documentar a cobertura de testes do contexto social (amizades e conexões).

---

## 📂 Arquivos e Responsabilidades

* [controller/SocialControllerTest.java](controller/SocialControllerTest.java) → Valida endpoints HTTP sociais e cenários de autenticação/conflito.
* [service/SocialServiceTest.java](service/SocialServiceTest.java) → Valida regras de negócio de amizade, aceite e listagem.
* [../README.md](../README.md) → Guia mestre da suíte de testes.

---

## 🔧 Funções / Métodos Principais

### SocialControllerTest

* `sendRequest_returns201`
* `sendRequest_alreadyExists_returns409`
* `acceptRequest_returnsAccepted`
* `getFriends_returnsList`
* `getFriends_noAuth_returns401`

---

### SocialServiceTest

* `sendFriendRequest_valid_createsPending`
* `sendFriendRequest_selfRequest_throwsBusiness`
* `sendFriendRequest_alreadyExists_throwsConflict`
* `acceptFriendRequest_pending_acceptsIt`
* `acceptFriendRequest_notReceiver_throwsBusiness`
* `acceptFriendRequest_alreadyAccepted_throwsBusiness`
* `getFriends_returnsAcceptedFriends`
* `getFriends_noFriends_returnsEmpty`
* `getFriendIds_resolvesBothDirections`

---

## ▶️ Exemplos de Uso

```java
// Executar apenas os testes de social:
// mvn -Dtest=SocialControllerTest,SocialServiceTest test
```

---

## 🔗 Dependências

* [controller/SocialControllerTest.java](controller/SocialControllerTest.java)
* [service/SocialServiceTest.java](service/SocialServiceTest.java)
* [../README.md](../README.md)

---

## ⚠️ Regras e Convenções

* Manter responsabilidades da pasta isoladas por camada.
* Evitar dependências cíclicas entre módulos.
* Preservar contratos de entrada/saída (DTOs e assinaturas públicas).

---

## 🧠 Observações

* Escopo documentado: src/test/java/com/postread/social
* Esta documentação usa os arquivos atuais da pasta como fonte de verdade.
* Total do módulo: 2 classes de teste, 14 métodos de teste.

---
