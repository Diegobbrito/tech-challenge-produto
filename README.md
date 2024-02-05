# Pos Tech Lanchonete

Projeto desenvolvido para o Tech Challenge da Pos Tech FIAP+Alura.
API de listagem e gerenciamento de produtos.

## Video
Gravamos um video sobre a nossa quarta parte do projeto:
- https://youtu.be/MrVDanTUrKI

## Link da projeto no SonarCloud

- [SonarCloud - Produto](https://sonarcloud.io/project/overview?id=Diegobbrito_tech-challenge-produto)


## Como testar a aplicação com docker compose

Faça download do projeto e na pasta principal rode o comando no terminal:

```bash
   docker-compose --env-file exemplo.env up -d
```
No navegador, abra a pagina do Swagger da aplicação:
http://localhost:8080/lanchonete/swagger-ui/index.html#/

O Swagger está documentado com exemplos de request.

### Opções usando o swagger

Verificar os produtos disponiveis e seus respectivos ids para o pedido
- GET /produtos

A aplicação é iniciada com dados de categorias e produtos no banco de dados.
## Stack utilizada

**Banco de dados:** Mysql

**Back-end:** Java, Springboot

## Video
Gravamos um video sobre a nossa quarta parte do projeto:
- https://youtu.be/MrVDanTUrKI

## Autores

- [Diego B Brito](https://github.com/Diegobbrito)
- [Gustavo Joia](https://github.com/GustavoJoiaP)
