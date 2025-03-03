# AgendariaGoumert

Projeto para a disciplina de Desenvolvimento de Aplicações Web 2, o intuito do projeto é desenvolver o projeto seguindo as especificações do professor.

Descrição fornecida: Vocês irão fazer um sistema de agenda online usando Spring web MVC, Thymeleaf e banco de dados. Funcionalidades a seguir:

 - O usuário pode se cadastrar no sistema.
 - O usuário pode se logar no sistema.
 - Uma vez logado no sistema o usuário pode alterar a senha e as informações de perfil. 
 - Uma vez logado no sistema o usuário pode cadastrar contatos.
 - Na tela inicial do usuário ele deve ser capaz de ver todos os contatos que ele cadastrou e não pode ver os contatos de outros usuários.
 - Uma vez logado o usuário pode pesquisar uma lista de contatos por nome.
	 Ex. Se entre os contatos do usuário existirem os contatos José Marcio
	 Calado, Josivaldo Antonio, Joselito amaro e Andreia Josélia e o   
	 usuário pesquisar a palavra ¨jo¨, todos os contatos acima
	 devem ser apresentados
	 
Diagrama de classe:

```mermaid
classDiagram
    class Usuario {
        long id
        String usuario
        String nome
        String senha
        String email
        List~Contato~ contatos
         +addContato(): boolean
    }

    class Contato {
        long id
        String nomeContato
        List~Email~ emails
        List~NumeroTelefone~ telefones
        +addEmail(): boolean
        +addTelefone(): boolean
    }

    class Email {
        long id
        String email
        +validarEmail(): boolean
    }

    class NumeroTelefone {
        long id
        String numeroDDD
        String numeroTelefone
        +validarNumeroTelefone(): boolean
        +validarNumeroDDD(): boolean
    }

    Usuario "1" --> "0..*" Contato : tem
    Contato "1" --> "0..*" Email : tem
    Contato "1" --> "0..*" NumeroTelefone : tem

  