# PoC - Spring Boot 3

Criando uma API RESTful com Spring Boot 3, implementando todos os modelos de maturidade desenvolvidos por Leonard Richardson.

# Products API RESTful

- Spring Boot 3
- Java 20
- Spring Web MVC
- Spring Data JPA
- Spring Validation
- Spring Hateoas
- PostgreSQL 12

# Padrão REST

No início dos anos 2000 Roy Fielding apresentou em sua tese de doutorado o padrão REST, o principal objetivo desse padrão era tentar resolver problemas do protocolo SOAP, como alta complexidade de implementação e lentidão na comunicação. Embora não seja um padrão novo, REST tem ganhado bastante destaque nos últimos anos, sobretudo depois da popularização da arquitetura de microserviços. O problema é que nem sempre implementamos o padrão REST da forma que foi originalmente especificada por Roy Fielding, já que para ele uma API so pode ser considerada REST se de fato implementar um conjunto de constraints.

Para padronizar e facilitar o desenvolvimento de APIs REST, [Leonard Richardson](https://martinfowler.com/articles/richardsonMaturityModel.html) propôs um modelo de maturidade para esse tipo de API, definido em 4 níveis. E o objetivo desse post é justamente apresentar esse modelo de maturidade proposto por Leonard Richardson. Ainda há uma discussão em relação a esse modelo, já que alguns defendem que para de fato ser considerada REST, uma API deve implementar os 4 níveis do modelo, outros consideram que implementando os 3 primeiros níveis, uma API já pode ser considerada REST. O objetivo desse post não é entrar nessa discussão, queremos aqui apresentar os 4 níveis do modelo.


## Nível 0 — POX

Esse é considerado o nível mais básico e uma API que implementa apenas esse nível não pode ser considerada REST. Nesse nível os nomes dos recursos não seguem qualquer padrão e estão sendo usados apenas para fazer invocação de métodos remotos. Nesse nível usamos o protocolo HTTP para comunicação, mas sem seguir qualquer tipo de regras para implementar os métodos.

Para facilitar o entendimento podemos observar no quadro abaixo a modelagem de uma API para um crud de clientes.

| Verbo HTTP | URI              | Operação  |
|------------|------------------|-----------|
| GET        | /getProduct/1    | Pesquisar |
| POST       | /saveProduct     | Criar     |
| POST       | /updateProduct/1 | Alterar   |
| GET/POST   | /removeProduct/1 | Excluir   |

No modelo acima fazemos uso apenas dos métodos GET e POST e como já citado, os nomes dos métodos não seguem nenhum padrão.


## Nível 1 — Recursos (Resources)

Nesse nível fazemos uso de recursos para modelar a API, para representar cada recurso fazemos uso de substantivos no plural. No exemplo do crud de cliente, os recursos seriam identificados pelo substantivo “clientes”.

Um detalhe interessante é que no nível 1 já usamos os verbos HTTP de forma correta, já que se os verbos não fossem usados, as rotas de pesquisar, alterar e incluir ficariam ambíguas.

| Verbo HTTP | URI         | Operação  |
|------------|-------------|-----------|
| GET        | /products/1 | Pesquisar |
| POST       | /products   | Criar     |
| PUT        | /products/1 | Alterar   |
| DELETE     | /products/1 | Excluir   |


## Nível 2 — Verbos HTTP

Como já foi adiantado no nível 1, o nível 2 se encarrega de garantir que os verbos HTTP sejam usados de forma correta. Os verbos mais utilizados são GET, POST, PUT e DELETE.

| Verbo HTTP | Função          |
|------------|-----------------|
| GET        | Recuperar Dados |
| POST       | Gravar Dados    |
| PUT        | Alterar Dados   |
| DELETE     | Excluir Dados   |

Os métodos GET, PUT e DELETE são considerados idempotente. Um método é considerado idempotente quando uma requisição idêntica pode ser executada várias vezes sem alterar o estado do servidor.


## Nível 3 — HATEOAS

O nível 3 é sem dúvidas o menos explorado, muitas APIs existentes no mercado não implementam esse nível.

HATEOAS significa Hypermedia as the Engine of Application State. Uma API que implementa esse nível fornece aos seus clientes links que indicarão como poderá ser feita a navegação entre seus recursos. Ou seja, quem for consumir a API precisará saber apenas a rota principal e a resposta dessa requisição terá todas as demais rotas possíveis.

![HATEOAS significa Hypermedia as the Engine of Application State. Uma API que implementa esse nível fornece aos seus clientes links que indicarão como poderá ser feita a navegação entre seus recursos. Ou seja, quem for consumir a API precisará saber apenas a rota principal e a resposta dessa requisição terá todas as demais rotas possíveis.](images/1_g-_jI5oc-7tD9K78FEahiw.webp "Nível 3 — HATEOAS")

No exemplo acima, podemos ver o resultado de uma API que implementa HATEAOS, veja que na resposta dessa API há uma coleção “links”, cada link aponta para uma rota dessa API. No caso desse exemplo, temos um link para a própria rota, um link para alterar um cliente e outra para exlcuir.


## Conclusão

No exemplo implementado com Spring Boot 3, foi uma breve forma de apresentar o modelo de maturidade de proposto por Leonard Richardson. Deixo o link para um artigo bem detalhado de Martin Fowler.


## Referências

[Richardson Maturity Model](https://martinfowler.com/articles/richardsonMaturityModel.html)

