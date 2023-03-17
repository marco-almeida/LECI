CREATE SCHEMA medicamentos;
go
CREATE TABLE medicamentos.farmaceutica(
    numero_registo INTEGER,
    telefone INTEGER,
    nome VARCHAR,
    endereco VARCHAR,
    PRIMARY KEY(numero_registo)
);
CREATE TABLE medicamentos.farmaco(
    formula VARCHAR,
    nome_comercial VARCHAR,
    farmaceutica INTEGER,
    PRIMARY KEY(nome_comercial)
);
CREATE TABLE medicamentos.tem(
    num_prescricao INTEGER,
    farmaco VARCHAR,
    PRIMARY KEY(num_prescricao)
);
CREATE TABLE medicamentos.farmacia(
    nif INTEGER,
    nome VARCHAR,
    endereco VARCHAR,
    telefone INTEGER,
    PRIMARY KEY(nif)
);
CREATE TABLE medicamentos.paciente(
    utente INTEGER,
    nome VARCHAR,
    endereco VARCHAR,
    data_nasc DATE,
    PRIMARY KEY(utente)
);
CREATE TABLE medicamentos.prescricao(
    numero INTEGER,
    adata DATE,
    sns_medico INTEGER,
    utente_paciente INTEGER,
    nif_farmacia INTEGER,
    PRIMARY KEY(numero),
);
CREATE TABLE medicamentos.medico(
    sns INTEGER,
    especialidade VARCHAR,
    nome VARCHAR,
    PRIMARY KEY(sns)
);


ALTER TABLE medicamentos.farmaco
ADD CONSTRAINT FK_1 FOREIGN KEY (farmaceutica) REFERENCES medicamentos.farmaceutica(numero_registo);
ALTER TABLE medicamentos.tem
ADD CONSTRAINT FK_2 FOREIGN KEY (num_prescricao) REFERENCES medicamentos.prescricao(numero);
ALTER TABLE medicamentos.prescricao
ADD CONSTRAINT FK_3 FOREIGN KEY (sns_medico) REFERENCES medicamentos.medico(sns);
ALTER TABLE medicamentos.prescricao
ADD CONSTRAINT FK_4 FOREIGN KEY (utente_paciente) REFERENCES medicamentos.paciente(utente);
ALTER TABLE medicamentos.prescricao
ADD CONSTRAINT FK_5 FOREIGN KEY (nif_farmacia) REFERENCES medicamentos.farmacia(nif);
ALTER TABLE medicamentos.tem
ADD CONSTRAINT FK_6 FOREIGN KEY (farmaco) REFERENCES medicamentos.farmaco(nome_comercial);