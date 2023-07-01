CREATE table peoples_tb(
        id bigint PRIMARY KEY AUTO_INCREMENT,
        nome varchar(100) not null,
        cpf varchar(15) not null,
        data_de_aniversario date not null,
        rua varchar(100) not null,
        complemento varchar(100),
        numero varchar(20),
        bairro varchar(100) not null,
        cidade varchar(100) not null,
        cep varchar(9) not null,
        uf char(2) not null,
        ativo tinyint not null

)