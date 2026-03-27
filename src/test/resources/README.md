# resources

## 📌 Propósito

Armazenar configurações específicas do ambiente de testes automatizados do backend.

---

## 📂 Arquivos e Responsabilidades

* [application-test.yml](application-test.yml) → Propriedades usadas durante execução dos testes (perfil de teste, conexão de dados e ajustes de ambiente).

---

## 🔧 Funções / Métodos Principais

Esta pasta não declara código Java; ela fornece parâmetros consumidos pelo contexto Spring nos testes.

---

## ▶️ Exemplos de Uso

```bash
# Executar testes carregando as configurações de src/test/resources
mvn clean test

# Executar apenas uma classe de teste com o mesmo contexto de teste
mvn -Dtest=GlobalExceptionHandlerTest test
```

```yaml
# Exemplo de propriedade em application-test.yml
# spring:
#   data:
#     mongodb:
#       uri: mongodb://localhost:27017/postread_test
```

---

## 🔗 Dependências

* [application-test.yml](application-test.yml)
* [../README.md](../README.md)
* [../java/com/postread/README.md](../java/com/postread/README.md)

---

## ⚠️ Regras e Convenções

* Alterações em propriedades de teste devem ser acompanhadas de validação da suíte relacionada.
* Evitar replicar aqui configurações exclusivas de produção.
* Manter nomes e chaves de configuração alinhados com o que os testes realmente consomem.

---

## 🧠 Observações

* Escopo documentado: src/test/resources
* Esta documentação usa os arquivos atuais da pasta como fonte de verdade.
* O arquivo [application-test.yml](application-test.yml) impacta diretamente estabilidade e isolamento dos testes.

---
