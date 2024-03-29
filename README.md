# BirdieApi

Bem vindos! 👋

BirdApi é o back-end da aplicação Birdie que desenvolvi, que é uma plataforma de cadastro de hóspedes e reservas de quartos de um hotel. Você pode encontrar o front-end da aplicação [clicando aqui](https://github.com/pedro-la-goncalves/birdie-ui).

## Sobre o projeto
Por conta do prazo de 5 dias de desenvolvimento tive que optar por entregar um MVP. Há diversas melhorias que pretendo realizar, como:
- Aumentar o code coverage com os testes em Junit
    - Hoje existe somente testes unitários para a classe ReservationService
- Realizar testes de integração/repositório
- Reestruturar o projeto com base em DDD
- Mover a lógica financeira presente em ReservationService em um novo domínio
- Aumentar o número de informações coletadas dos hóspedes
- Possibilitar o cadastro de funcionários
- Possibilitar o cadastro de perfis de usuários
- Possibilitar o cadastro de quartos disponível pelo hotel
- Possibilitar o cadastro de serviços disponíveis pelo hotel
- Possibilitar o cadastro de produtos disponíveis pelo hotel
- Possibilitar que hóspedes possam navegar pela aplicação e requisitar produtos/serviços e realizar pagamentos

Este é um projeto que pretendo levar para frente aos poucos.

## Configurando o projeto
### 1. Verifique a versão do Java em sua máquina
Recomendo que você esteja usando uma versão mais atual do Node. Durante o desenvolvimento deste projeto não foi utilizado Docker, então não há arquivos de imagem no momento. A versão do Java utilizada durante o desenvolvimento do projeto foi a **17.0.6**.

### 2. Clone o projeto para a sua máquina
```
git clone https://github.com/pedro-la-goncalves/birdie-api.git
```

### 3. Crie um banco de dados local
Durante o desenvolvimento desta aplicação utilizei o banco de dados PostgreSQL na versão **16.2**. No seu pgAdmin crie um novo banco de dados chamado "birdie".

### 4. Configurando a conexão com o banco
No arquivo `application.properties` você deverá configurar os dados para a conexão com o banco de dados que você acabou de criar.

### 5. Inicie a aplicação
Busque pelo arquivo `BirdieApplication.java` para iniciar a aplicação.

### 6. Lembre-se de verificar em qual porta o seu projeto está rodando
Na hora de configurar o projeto do front-end é importante que você pegue a porta correta em que a aplicação back-end está rodando em sua máquina.

### Alegria 🎉🎉
Caso você não tenha configurado o projeto do front-end você pode [clicar aqui](https://github.com/pedro-la-goncalves/birdie-ui) para iniciar a configuração.
