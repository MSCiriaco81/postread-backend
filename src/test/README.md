# test

## 📌 Propósito

Centralizar os testes automatizados do backend e servir como ponto de entrada para navegação da suíte de testes.

---

## 📂 Arquivos e Responsabilidades

* [java/com/postread/README.md](java/com/postread/README.md) → Guia mestre dos testes por módulo, com cenários cobertos por classe.
* [resources/application-test.yml](resources/application-test.yml) → Configuração de ambiente para execução de testes.
* [resources/README.md](resources/README.md) → Documentação dos recursos usados nos testes.

---

## 🔧 Funções / Métodos Principais

Esta pasta não possui classes Java diretamente. O detalhamento de classes e métodos de teste está em [java/com/postread/README.md](java/com/postread/README.md).

---

## ▶️ Exemplos de Uso

```bash
# Executar toda a suíte de testes
mvn clean test

# Executar apenas os testes de um módulo (exemplo: social)
mvn -Dtest="SocialControllerTest,SocialServiceTest" test

# Executar uma única classe de teste
mvn -Dtest=AuthServiceTest test
```

---

## 🔗 Dependências

* [java/com/postread/README.md](java/com/postread/README.md)
* [resources/application-test.yml](resources/application-test.yml)
* [resources/README.md](resources/README.md)

---

## ⚠️ Regras e Convenções

* Novas regras de negócio devem incluir testes na camada apropriada (controller/service) no mesmo PR.
* Testes devem manter nomes descritivos, priorizando cenário e resultado esperado.
* Configurações de teste devem ficar isoladas em `application-test.yml`.
* Evitar dependências cíclicas entre módulos de teste.

---

## 🧠 Observações

* Escopo documentado: src/test
* Esta documentação usa os arquivos atuais da pasta como fonte de verdade.
* Para visão detalhada de cobertura e cenários, comece por [java/com/postread/README.md](java/com/postread/README.md).

---
