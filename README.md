# Projeto FIAP


## Arquitetura e desenvolvimento JAVA - Tech Challenge

O Tech Challenge é o projeto da fase que englobará os conhecimentos obtidos em todas as disciplinas da fase


# Autenticação de Usuários

Este é um projeto desenvolvido em Java com Spring Boot para autenticação de usuários. Ele utiliza um banco de dados H2 em memória e está configurado para execução em contêineres Docker.

---

## 🛠️ Tecnologias Utilizadas

- **Linguagem:** Java 21
- **Framework:** Spring Boot
- **Banco de Dados:** H2 (em memória)
- **Gerenciamento de Dependências:** Maven
- **Ferramenta de Contêiner:** Docker

---

## 🚀 Funcionalidades

- Sistema de autenticação de usuários
- Banco de dados H2 configurado para inicialização automática
- Endpoint acessível via HTTP para gerenciamento de autenticações
- Console do H2 para inspeção de dados: `/h2-console`

---

## 🐋 Executando com Docker

### Pré-requisitos
Certifique-se de ter instalado:
- [Docker](https://www.docker.com/get-started)
- [Docker Compose](https://docs.docker.com/compose/install/)

### Passos
1. **Construa e suba os contêineres:**
   ```bash
   docker-compose up --build