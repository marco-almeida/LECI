create schema Stocks;
go

create table Tipo_fornecedor(
	Codigo INT NOT NULL CHECK(Codigo>0),
	Designacao VARCHAR(25),
	PRIMARY KEY (Codigo)
);

create table Fornecedor(
	Nif INT NOT NULL CHECK(Nif>0),
	Nome VARCHAR(30),
	Fax INT NOT NULL CHECK(Fax>0),
	Endereco VARCHAR(50),
	Condpag INT CHECK(Condpag>0),
	Tipo INT NOT NULL CHECK(Tipo>0),
	PRIMARY KEY (Nif),
	FOREIGN KEY (Tipo) REFERENCES  Tipo_fornecedor(Codigo)
);

create table Produto(
	Codigo INT NOT NULL CHECK(Codigo>0),
	Nome VARCHAR(50) NOT NULL,
	Preco INT NOT NULL CHECK(Preco>=0),
	Iva INT NOT NULL CHECK(Iva>=0 and Iva<=100),
	Unidades INT NOT NULL CHECK(Unidades>0),
	PRIMARY KEY (Codigo)
);

create table Encomenda(
	Numero INT NOT NULL CHECK(Numero>0),
	Dataa DATE,
	Fornecedor INT NOT NULL CHECK(Fornecedor>0),
	PRIMARY KEY (Numero),
	FOREIGN KEY (Fornecedor) REFERENCES  Fornecedor(Nif)
);

create table Item(
	NumEnc INT NOT NULL CHECK(NumEnc>0),
	CodProd INT NOT NULL CHECK(CodProd>0),
	Unidades INT NOT NULL CHECK(Unidades>0),
	PRIMARY KEY (NumEnc,CodProd),
	FOREIGN KEY (NumEnc) REFERENCES  Encomenda(Numero),
	FOREIGN KEY (CodProd) REFERENCES  Produto(Codigo)
);