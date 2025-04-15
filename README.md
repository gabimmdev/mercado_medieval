[![Typing SVG](https://readme-typing-svg.herokuapp.com/?color=8B4513&size=35&center=true&vCenter=true&width=1000&lines=+🏰+API+Mercado+Medieval)](https://git.io/typing-svg)

Este projeto é uma API REST desenvolvida com **Spring Boot** que simula um mercado medieval, permitindo o cadastro, atualização, remoção e busca de **personagens** com diferentes **classes**, níveis e moedas. A aplicação utiliza banco de dados **H2** em memória para fins de desenvolvimento e testes rápidos.

---

### 📦 Tecnologias Utilizadas

- Java 17+
- Spring Boot
- Spring Web
- Spring Data JPA
- H2 Database
- Jakarta Validation

---

### 🚀 Como executar

1. Clone o projeto:
```bash
git clone https://github.com/seu-usuario/mercado-medieval-api.git
cd mercado-medieval-api
```
2. Use sua IDE preferida (IntelliJ, Eclipse, VS Code) ou execute pela linha de comando:
```bash
   ./mvnw spring-boot:run
```
---
### 🧪 Testar a API
Você pode usar Postman, Insomnia ou o próprio Swagger (caso adicione no projeto). Aqui estão alguns endpoints úteis:

📌 GET /personagens <br>
Lista todos os personagens.

📌 POST /personagens <br>
Cria um novo personagem.

Exemplo de corpo da requisição (JSON):
```json
{
  "nome": "Aldarion",
  "classe": "GUERREIRO",
  "nivel": 5,
  "moedas": 200
}
```
📌 PUT /personagens/{id} <br>
    Atualiza um personagem existente.

📌 DELETE /personagens/{id} <br>
    Remove um personagem pelo ID.

📌 GET /personagens/buscar/nome?nome=al <br>
    Busca personagens por nome (parcial ou completo, sem case-sensitive).

📌 GET /personagens/buscar/classe?classe=GUERREIRO <br>
    Busca personagens por classe.
