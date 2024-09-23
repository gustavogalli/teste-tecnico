# Desafio Backend - Sistema de Contas a Pagar

Este repositório contém a implementação de uma API REST para um sistema simples de contas a pagar. O sistema permite realizar operações de CRUD em contas a pagar, alterar a situação das contas quando um pagamento é efetuado, obter informações sobre as contas cadastradas e importar um lote de contas de um arquivo CSV.

## Requisitos Gerais

1. **Linguagem**: Java 17
2. **Framework**: Spring Boot
3. **Banco de Dados**: PostgreSQL
4. **Containerização**: A aplicação é executada em um container Docker
5. **Orquestração**: Docker Compose para orquestrar a aplicação, banco de dados e outros serviços necessários
6. **Hospedagem**: GitHub
7. **Autenticação**: Spring Security
8. **Arquitetura**: Domain Driven Design
9. **Migrações de Banco**: Flyway

## Requisitos Específicos

1. **Banco de Dados**:
   - Criar uma tabela para armazenar as contas a pagar com os seguintes campos:
     - `id`
     - `data_vencimento`
     - `data_pagamento`
     - `valor`
     - `descricao`
     - `situacao`
   
2. **Entidade Conta**:
   - Implementar a entidade "Conta" na aplicação, de acordo com a tabela criada anteriormente.

3. **APIs Implementadas**:
   - Cadastrar conta.
   - Atualizar conta.
   - Alterar a situação da conta.
   - Obter a lista de contas a pagar, com filtros de data de vencimento e descrição.
   - Obter conta filtrando pelo id.
   - Obter valor total pago por período.

4. **Importação via CSV**:
   - Implementar um mecanismo para importação de contas a pagar via arquivo CSV, consumido através de uma API.

5. **Testes Unitários**:
   - Implementar testes unitários para as funcionalidades da aplicação.

## Como Executar o Projeto

1. Clone o repositório:

   ```bash
   git clone https://github.com/gustavogalli/teste-tecnico.git
