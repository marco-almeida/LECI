CREATE SCHEMA conferencias;
go
CREATE TABLE conferencias.artigo(
    numero_registo INTEGER,
    titulo VARCHAR,
    mail_autor VARCHAR,
    PRIMARY KEY(numero_registo)
);
CREATE TABLE conferencias.autor(
    mail VARCHAR,
    nome VARCHAR,
    instituicao VARCHAR,
    PRIMARY KEY(mail)
);
CREATE TABLE conferencias.instituicao(
    nome VARCHAR,
    endereco VARCHAR,
    PRIMARY KEY(nome)
);
CREATE TABLE conferencias.participante(
    instituicao VARCHAR,
    nome VARCHAR,
    morada VARCHAR,
    data_insc DATE,
    email VARCHAR,
    PRIMARY KEY(email)
);
CREATE TABLE conferencias.estudante(
    email VARCHAR,
    comprovativo VARCHAR,
    PRIMARY KEY(email)
);
CREATE TABLE conferencias.nao_estudante(
    email VARCHAR,
    ref_banco VARCHAR,
    PRIMARY KEY(email)
);

ALTER TABLE conferencias.artigo
ADD CONSTRAINT FK_1 FOREIGN KEY (mail_autor) REFERENCES conferencias.autor(mail);
ALTER TABLE conferencias.autor
ADD CONSTRAINT FK_2 FOREIGN KEY (instituicao) REFERENCES conferencias.instituicao(nome);
ALTER TABLE conferencias.participante
ADD CONSTRAINT FK_3 FOREIGN KEY (instituicao) REFERENCES conferencias.instituicao(nome);
ALTER TABLE conferencias.estudante
ADD CONSTRAINT FK_4 FOREIGN KEY (email) REFERENCES conferencias.participante(email);
ALTER TABLE conferencias.nao_estudante
ADD CONSTRAINT FK_5 FOREIGN KEY (email) REFERENCES conferencias.participante(email);