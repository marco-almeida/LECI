CREATE SCHEMA rentacar;
go
CREATE TABLE rentacar.aluguer(
numero	INTEGER,
dataa	DATE,
duracao	TIME,
balcao_id INTEGER,
cliente_id INTEGER,
veiculo_matricula CHAR(6),
PRIMARY KEY(numero)
);

CREATE TABLE rentacar.balcao(
numero INTEGER,
nome VARCHAR,
endereco VARCHAR
PRIMARY KEY(numero)
);

CREATE TABLE rentacar.cliente(
nif INTEGER,
nome VARCHAR,
endereco VARCHAR,
num_carta VARCHAR,
UNIQUE(num_carta),
PRIMARY KEY(nif)
);

CREATE TABLE rentacar.veiculo(
matricula CHAR(6),
marca VARCHAR,
ano DATE,
tipo_codigo INTEGER,
PRIMARY KEY(matricula)
);

CREATE TABLE rentacar.tipoveiculo(
codigo INTEGER,
designacao VARCHAR,
arcondicionado BIT,
PRIMARY KEY(codigo)
);

CREATE TABLE rentacar.similaridade(
tipo1 INTEGER,
tipo2 INTEGER,
PRIMARY KEY(tipo1, tipo2)
);

CREATE TABLE rentacar.ligeiro(
tipo_codigo INTEGER,
num_lugares INTEGER,
portas INTEGER,
combustivel VARCHAR,
PRIMARY KEY(tipo_codigo)
);

CREATE TABLE rentacar.pesado(
tipo_codigo INTEGER,
peso INTEGER,
passageiros INTEGER,
PRIMARY KEY(tipo_codigo)
);

ALTER TABLE rentacar.aluguer ADD CONSTRAINT FK_VEI_MATR FOREIGN KEY (veiculo_matricula) REFERENCES rentacar.veiculo(matricula);
ALTER TABLE rentacar.aluguer ADD CONSTRAINT FK_NUM_BAL FOREIGN KEY (balcao_id) REFERENCES rentacar.balcao(numero);
ALTER TABLE rentacar.aluguer ADD CONSTRAINT FK_ID_CLI FOREIGN KEY (cliente_id) REFERENCES rentacar.cliente(nif);
ALTER TABLE rentacar.veiculo ADD CONSTRAINT FK_TIP_COD FOREIGN KEY (tipo_codigo) REFERENCES rentacar.tipoveiculo(codigo);
ALTER TABLE rentacar.similaridade ADD CONSTRAINT FK_SIM_T1 FOREIGN KEY (tipo1) REFERENCES rentacar.tipoveiculo(codigo);
ALTER TABLE rentacar.similaridade ADD CONSTRAINT FK_SIM_T2 FOREIGN KEY (tipo2) REFERENCES rentacar.tipoveiculo(codigo);
ALTER TABLE rentacar.ligeiro ADD CONSTRAINT FK_LIG FOREIGN KEY(tipo_codigo) REFERENCES rentacar.tipoveiculo(codigo);
ALTER TABLE rentacar.pesado ADD CONSTRAINT FK_PES FOREIGN KEY(tipo_codigo) REFERENCES rentacar.tipoveiculo(codigo);