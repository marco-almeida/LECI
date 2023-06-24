CREATE SCHEMA stocks;
go CREATE TABLE stocks.produto(
        codigo INTEGER,
        nome VARCHAR,
        preco MONEY,
        iva FLOAT,
        stock VARCHAR,
        PRIMARY KEY(codigo)
    );
CREATE TABLE stocks.tem(
    encomenda INTEGER,
    cod_produto INTEGER,
    quantidade INTEGER,
    PRIMARY KEY(cod_produto, encomenda)
);
CREATE TABLE stocks.encomenda(
    numero INTEGER,
    adata DATE,
    nif_fornecedor INTEGER,
    PRIMARY KEY(numero)
);
CREATE TABLE stocks.fornecedor(
    nif INTEGER,
    nome VARCHAR,
    endereco VARCHAR,
    condicoes_pagamento VARCHAR,
    numero_fax INTEGER,
    PRIMARY KEY(nif)
);
CREATE TABLE stocks.tipo(
    tipo_fornecedor INTEGER,
    nif_fornecedor INTEGER,
    PRIMARY KEY(tipo_fornecedor)
);
CREATE TABLE stocks.tipo_fornecedor(
    id INTEGER,
    designacao VARCHAR,
    PRIMARY KEY(id)
);
ALTER TABLE stocks.tem
ADD CONSTRAINT FK_1 FOREIGN KEY (cod_produto) REFERENCES stocks.produto(codigo);
ALTER TABLE stocks.tem
ADD CONSTRAINT FK_2 FOREIGN KEY (encomenda) REFERENCES stocks.encomenda(numero);
ALTER TABLE stocks.encomenda
ADD CONSTRAINT FK_3 FOREIGN KEY (nif_fornecedor) REFERENCES stocks.fornecedor(nif);
ALTER TABLE stocks.tipo
ADD CONSTRAINT FK_4 FOREIGN KEY (nif_fornecedor) REFERENCES stocks.fornecedor(nif);
ALTER TABLE stocks.tipo
ADD CONSTRAINT FK_5 FOREIGN KEY (tipo_fornecedor) REFERENCES stocks.tipo_fornecedor(id);