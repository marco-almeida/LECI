CREATE SCHEMA Medicamentos;
GO
CREATE TABLE Farmacia(
    Nome VARCHAR(50) NOT NULL,
    Endereco VARCHAR(100) NOT NULL,
    Telefone INT NOT NULL,
    PRIMARY KEY(Nome)
);
CREATE TABLE Paciente(
    NSNS INT NOT NULL,
    Endereco VARCHAR(100) NOT NULL,
    Nome VARCHAR(50) NOT NULL,
    Data_nasc DATE NOT NULL,
    PRIMARY KEY(NSNS)
);

CREATE TABLE Medico(
    NID INT,
    Nome VARCHAR(50) NOT NULL,
    Especialidade VARCHAR(50) NOT NULL,
    PRIMARY KEY(NID)
);

CREATE TABLE Farmaceutica(
    Nome VARCHAR(50) NOT NULL,
    Endereco VARCHAR(100) NOT NULL,
    Telefone INT,
    Nreg INT NOT NULL,
    PRIMARY KEY(Nreg)
);

CREATE TABLE Farmaco(
    Nome VARCHAR(50) NOT NULL,
    Formula VARCHAR(50) NOT NULL,
    Nreg INT NOT NULL,
    PRIMARY KEY(Nome, Nreg),
    FOREIGN KEY(Nreg) REFERENCES Farmaceutica(Nreg)
);

CREATE TABLE Prescricao(
    Nuni INT NOT NULL,
    Data_proc DATE,
    NID INT,
    NSNS INT NOT NULL,
    Nome_farmacia VARCHAR(50),
    PRIMARY KEY(Nuni),
    FOREIGN KEY(NID) REFERENCES Medico(NID),
    FOREIGN KEY(NSNS) REFERENCES Paciente(NSNS),
    FOREIGN KEY(Nome_farmacia) REFERENCES Farmacia(Nome)
);

CREATE TABLE Presc_farmaco(
    Num_presc INT NOT NULL,
	Num_reg_farm INT NOT NULL,
	Nome_farmaco VARCHAR(50) NOT NULL,
	PRIMARY KEY(Num_presc,Num_reg_farm,Nome_farmaco),
	FOREIGN KEY(Num_presc) REFERENCES Prescricao(Nuni),
	FOREIGN KEY(Nome_farmaco, Num_reg_farm) REFERENCES Farmaco(Nome, Nreg),
);