# view-level-engine-api

API de motor de decisão para definir o nível de visualização (view level) com base em um grafo de regras persistido no MongoDB. A execução percorre nós de condição até chegar em um nó de ação e retorna o `viewLevel`.

## Stack
- Java 17
- Spring Boot 4.0.2
- MongoDB (Spring Data MongoDB)
- Gradle
- Lombok

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

## CORS

A API permite requisições de `http://localhost:5173` (métodos: `GET`, `POST`, `PUT`, `DELETE`, `OPTIONS`).

## Seed automático

Na inicialização, se o banco estiver vazio, o `OperationSeed` popula automaticamente a coleção `operation` com todos os operadores de condição disponíveis e os nós de ação (`COMPLETE`, `RESTRICTED`, `BLOCKED`, `MASKED`).

---

## API

### POST `/engine/rule`
Cria e persiste um novo grafo de regras no MongoDB.

**Request body**

| Campo | Tipo | Descrição |
|---|---|---|
| `startNode` | string | ID do nó inicial |
| `statusEnum` | string | Status da regra: `PUBLISHING`, `PUBLISHED` ou `DRAFT` |
| `nodes` | mapa `<id, NodeDTO>` | Mapa de nós do grafo |
| `positions` | mapa `<id, NodePositionDTO>` | (opcional) Posições dos nós no editor visual |
| `edges` | lista de `RuleEdgeDTO` | (opcional) Arestas do grafo |

> Ao salvar com `statusEnum = PUBLISHED`, qualquer regra anteriormente publicada é automaticamente movida para `DRAFT`.

**Resposta:** `200 OK`

---

### PUT `/engine/rule/{id}`
Atualiza um grafo de regras existente pelo ID, com validação da árvore.

**Path param:** `id` — ID da regra a atualizar.

**Request body:** mesmo formato do `POST /engine/rule`.

**Resposta:** `200 OK`

---

### GET `/rules`
Retorna a regra com status `PUBLISHED`.

**Resposta**

```json
{
  "id": "...",
  "startNode": "node1",
  "status": "PUBLISHED",
  "nodes": { ... },
  "positions": { ... },
  "edges": [ ... ]
}
```

---

### GET `/rules/operators`
Retorna a lista de operadores disponíveis (populados pelo seed).

**Resposta**

```json
[
  {
    "id": "...",
    "name": "USER_IS_OPERATOR",
    "type": "CONDITION",
    "hasArgs": false
  },
  {
    "id": "...",
    "name": "PERIOD_OF_TRANSACTION_IS_LESS_THAN",
    "type": "CONDITION_WITH_ARGS",
    "hasArgs": true
  }
]
```

---

### GET `/view-level-customer/{id}`
Executa o motor de regras sobre a regra publicada para um cliente e retorna o `viewLevel`.

**Path param:** `id` — ID do cliente (Long).

**Resposta**

```json
{
  "viewLevel": "BLOCKED"
}
```

---

## Estrutura dos DTOs

### NodeDTO

| Campo | Tipo | Descrição |
|---|---|---|
| `type` | `NodeTypeEnum` | `CONDITION`, `CONDITION_WITH_ARGS` ou `ACTION` |
| `operation` | `ConditionOperatorEnum` | Operador de condição (somente para nós de condição) |
| `arguments` | `{ "value": <número> }` | Argumento do operador (somente para `CONDITION_WITH_ARGS`) |
| `onTrue` | string | ID do próximo nó se a condição for verdadeira |
| `onFalse` | string | ID do próximo nó se a condição for falsa |
| `set` | string | `ViewLevelEnum` a definir (somente para `ACTION`) |

### NodePositionDTO

| Campo | Tipo | Descrição |
|---|---|---|
| `x` | integer | Posição horizontal no editor visual |
| `y` | integer | Posição vertical no editor visual |

### RuleEdgeDTO

| Campo | Tipo | Descrição |
|---|---|---|
| `id` | string | ID da aresta |
| `source` | string | ID do nó de origem |
| `target` | string | ID do nó de destino |
| `sourceHandle` | boolean | Handle de saída (`true` = onTrue, `false` = onFalse) |

---

## Enums

### NodeTypeEnum
`CONDITION` · `CONDITION_WITH_ARGS` · `ACTION`

### StatusEnum
`PUBLISHING` · `PUBLISHED` · `DRAFT`

### ViewLevelEnum
`COMPLETE` · `RESTRICTED` · `BLOCKED` · `MASKED`

### ConditionOperatorEnum
`USER_IS_OPERATOR` · `PERIOD_OF_TRANSACTION_IS_GREATER_THAN` · `PERIOD_OF_TRANSACTION_IS_LESS_THAN`

---

## Operadores de condição disponíveis

- `USER_IS_OPERATOR` (`CONDITION`): retorna `true` se o contexto contém a permissão `PERM_OPERATOR`. Não requer argumentos.
- `PERIOD_OF_TRANSACTION_IS_LESS_THAN` (`CONDITION_WITH_ARGS`): retorna `true` se os dias desde a última transação (mockado como `hoje - 10 dias`) forem ≤ `value`.
- `PERIOD_OF_TRANSACTION_IS_GREATER_THAN` (`CONDITION_WITH_ARGS`): retorna `true` se os dias desde a última transação (mockado como `hoje - 30 dias`) forem ≥ `value`.

`value` deve ser um inteiro dentro de `arguments`, por exemplo:

```json
"arguments": { "value": 15 }
```

---

## Exemplo de criação de regra

```
POST /engine/rule
Content-Type: application/json

{
  "startNode": "node1",
  "statusEnum": "PUBLISHED",
  "nodes": {
    "node1": {
      "type": "CONDITION",
      "operation": "USER_IS_OPERATOR",
      "arguments": null,
      "onTrue": "node2",
      "onFalse": "node3"
    },
    "node2": {
      "type": "ACTION",
      "set": "BLOCKED"
    },
    "node3": {
      "type": "ACTION",
      "set": "RESTRICTED"
    }
  }
}
```

## Exemplo de execução do motor

```
GET /view-level-customer/42
```

Resposta:

```json
{
  "viewLevel": "BLOCKED"
}
```

---

## Observações
- O contexto em `GetViewLevelCustomerAction` é parcialmente mockado: permissões (`PERM_ADMIN`, `PERM_OPERATOR`) e cliente não são obtidos de token/integração ainda.
- O grafo de nós é salvo em MongoDB. Ao publicar uma nova regra, a regra publicada anterior é movida para `DRAFT` automaticamente.
- A validação da árvore é aplicada no `PUT /engine/rule/{id}` via `TreeValidator`.
