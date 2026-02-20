# view-level-engine-api

API de motor de decisão para definir o nível de visualização (view level) com base em um grafo de regras enviado na requisição. A execução percorre nós de condição até chegar em um nó de ação, retorna o `viewLevel` e persiste o grafo em MongoDB.

## Stack
- Java 17
- Spring Boot 4.0.2
- MongoDB (Spring Data MongoDB)
- Gradle

## Pré-requisitos
- Java 17
- MongoDB rodando (por padrão em `mongodb://localhost:27017`)

## Configuração
Arquivo: `src/main/resources/application.properties`

```
spring.application.name=view-level-engine-api

spring.mongodb.uri=mongodb://localhost:27017
spring.mongodb.database=rules_view_level
```

Ajuste `spring.mongodb.uri` e `spring.mongodb.database` conforme o seu ambiente.

## Executar localmente

```
./gradlew bootRun
```

## Testes

```
./gradlew test
```

## API

### POST `/engine/rule`
Executa o motor de regras e retorna o `viewLevel`.

**Request body**

- `version` (int)
- `startNode` (string)
- `nodes` (mapa de nós, chaveada por id)

**Nó (NodeDTO)**
- `type`: `CONDITION` ou `ACTION`
- `operation`: nome do operador (apenas para `CONDITION`)
- `arguments`: mapa com argumentos do operador (apenas para `CONDITION`)
- `onTrue`: id do próximo nó se a condição for verdadeira (apenas para `CONDITION`)
- `onFalse`: id do próximo nó se a condição for falsa (apenas para `CONDITION`)
- `set`: nível de visualização (apenas para `ACTION`)
- `next`: campo não utilizado atualmente

**View levels disponíveis**
`COMPLETE`, `BLOCKED`, `MASKED`

**Resposta**

```
{
  "viewLevel": "BLOCKED"
}
```

### Exemplo de requisição

```
POST /engine/rule
Content-Type: application/json

{
  "version": 1,
  "startNode": "n1",
  "nodes": {
    "n1": {
      "type": "CONDITION",
      "operation": "UserIsOperatorConditionOperator",
      "arguments": {},
      "onTrue": "n2",
      "onFalse": "n3"
    },
    "n2": {
      "type": "ACTION",
      "set": "BLOCKED"
    },
    "n3": {
      "type": "ACTION",
      "set": "RESTRICTED"
    }
  }
}
```

## Operadores de condição disponíveis
- `UserIsOperatorConditionOperator`: retorna `true` se o contexto contém a permissão `PERM_OPERATOR`.
- `PeriodOfTransactionIsLessThanConditionOperator`: compara dias desde a última transação (mockada como `hoje - 10 dias`) com `paramDaysTransaction`.
- `PeriodOfTransactionIsGreaterThanConditionOperator`: compara dias desde a última transação (mockada como `hoje - 30 dias`) com `paramDaysTransaction`.

`paramDaysTransaction` deve ser um inteiro dentro de `arguments`, por exemplo:

```
"arguments": { "paramDaysTransaction": 15 }
```

## Observações
- O contexto atual é parcialmente mockado em `CreateRuleAction`: permissões e cliente não são obtidos de token/integração ainda.
- O grafo de nós é salvo em MongoDB com `status = PUBLISHED` a cada execução.