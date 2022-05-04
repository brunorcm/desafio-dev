<h1 align="center"> DESAFIO DEV :: BYCODERS </h1>

![Screenshot](https://github.com/brunorcm/desafio-dev/blob/main/imagens/cadastro.png)
![Screenshot](https://github.com/brunorcm/desafio-dev/blob/main/imagens/consulta.png)

# Desafio dev
### Desafio dev 

https://github.com/brunorcm/desafio-dev/

---

## Stack

- [**Java**](https://www.java.com/pt-BR/) 
- [**Spring Boot**](https://spring.io/projects/spring-boot) 
- [**MySQL**](https://www.mysql.com/) 
- [**Docker**](https://www.docker.com/) 
- [**Swagger**](https://swagger.io/specification/)

---

## Instalação

Pré-requisitos (As ferramentas abaixo devem estar instaladas em seu ambiente UNIX):

- [docker](https://www.docker.com/)
- [docker-compose](https://docs.docker.com/compose/)
- [git](https://git-scm.com/)
- [maven](https://maven.apache.org/)


#### Rodando o projeto


1. Clone o repositório em seu ambiente local, digitando:
    ```
    $ git clone https://github.com/brunorcm/desafio-dev.git
    ```
1. Vá até o local onde clonou o repositório e entre no diretório da aplicação:
    ```
    $ cd desafio-dev/projeto-bycoders 
    
    ```
1. Faça o build do projeto:
    ```
    $ mvn clean install package    
    
    ```
1. Caso tenha alguma instalação do mysql rodando na porta <b>3306</b> em sua máquina, certifique-se de pará-la antes:
     ```
    $ sudo service mysql stop
1. Para subir o projeto via docker-compose será necessário criar uma rede (network) para que os contâineres da aplicação e do banco de dados se enxerguem :
     ```
    $ sudo docker network create projeto_network
    
1. Feito o passo anterior, para subir o projeto e o banco, executar:
     ```
    $ sudo docker-compose up
    
1. Se desejar em algum momento parar os contâineres (aplicação e banco), pode ser executado o comando abaixo:
     ```
    $ sudo docker-compose down
    
    ```
1. Abra o seu navegador e digite `http://localhost:8080/desafio-dev/`. Se tudo subiu corretamente aparecerá de imediato a tela para envio do arquivo CNAB.


### Consultando documentação da API

1. O projeto foi documentado usando o Swagger (Open API).

1. Para visualizar as especificações da API geradas automaticamente (em formato JSON), acessar:

    
    `http://localhost:8080/desafio-dev/v3/api-docs/`
    
1. Já a documentação gerada via Swagger pode ser acessada pelo endereço:

    `http://localhost:8080/desafio-dev/swagger-ui/index.html`    

---

### Atendimento aos critérios do desafio

Os seguintes critérios do desafio foram abordados e implementados:
- Instruções para clonar o repositório, baixar e rodar o projeto
- Compatibilidade com ambiente UNIX
- Git commits seguindo padrões
- Uso de banco relacional (MySQL)
- Uso de docker/docker-compose
- Testes unitários
- Documentação da API 
- Uso de framework (Spring Boot)
  
