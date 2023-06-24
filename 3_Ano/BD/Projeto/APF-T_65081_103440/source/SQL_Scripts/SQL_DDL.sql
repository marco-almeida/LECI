-- standauto--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
CREATE TYPE NO_TELEFONE
FROM VARCHAR(20) NOT NULL;
GO -- nova batch senao da erro
    CREATE TYPE EMAILADDRESS
FROM VARCHAR(255) NOT NULL;
GO -- nova batch senao da erro
    CREATE TABLE standauto (
        stand_id INTEGER IDENTITY(1, 1) PRIMARY KEY,
        pais VARCHAR(100) NOT NULL,
        endereco VARCHAR(255) NOT NULL,
        email EMAILADDRESS UNIQUE CHECK (
            RIGHT(email, LEN('@standauto.com')) = '@standauto.com' -- validar email
            AND email LIKE '%@%.%'
            AND email NOT LIKE '%@%@%'
        ),
        telefone NO_TELEFONE UNIQUE,
        codigo_postal VARCHAR(20) NOT NULL
    );
-- funcionario--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
CREATE TABLE funcionario(
    funcionario_id INTEGER IDENTITY(1, 1) PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    email EMAILADDRESS UNIQUE CHECK (
        RIGHT(email, LEN('@standautostaff.com')) = '@standautostaff.com' -- validar email
        AND email LIKE '%@%.%'
        AND email NOT LIKE '%@%@%'
    ),
    telefone NO_TELEFONE UNIQUE,
    endereco VARCHAR(255) NOT NULL,
    stand_id INTEGER REFERENCES standauto(stand_id) ON DELETE SET NULL
);
CREATE TABLE vendedor(
    vendedor_id INTEGER REFERENCES funcionario(funcionario_id) ON DELETE CASCADE,
    PRIMARY KEY(vendedor_id),
    num_vendas INTEGER CHECK (num_vendas > -1) DEFAULT 0
);
CREATE TABLE mecanico(
    mecanico_id INTEGER REFERENCES funcionario(funcionario_id) ON DELETE CASCADE,
    PRIMARY KEY(mecanico_id)
);
CREATE TABLE gerente(
    gerente_id INTEGER REFERENCES funcionario(funcionario_id) ON DELETE CASCADE,
    PRIMARY KEY(gerente_id)
);
CREATE TABLE certificacoes(
    certificacoes VARCHAR(255) CHECK (
        certificacoes IN (
            'Mecanica',
            'Mecatronica',
            'Eletronica',
            'Informatica',
            'Gestao'
        )
    ) NOT NULL,
    PRIMARY KEY(certificacoes)
);
CREATE TABLE gerente_certificacoes(
    gerente INTEGER REFERENCES gerente(gerente_id) ON DELETE CASCADE,
    certs VARCHAR(255) REFERENCES certificacoes(certificacoes) ON DELETE CASCADE,
    PRIMARY KEY(gerente, certs)
);
-- veiculo---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
CREATE TABLE veiculo(
    matricula VARCHAR(20),
    PRIMARY KEY (matricula),
    marca VARCHAR(30) NOT NULL,
    modelo VARCHAR(255) NOT NULL,
    combustivel VARCHAR(30) CHECK (
        combustivel IN (
            'Gasolina',
            'Diesel',
            'Eletrico',
            'Hibrido',
            'Hidrogenio'
        )
    ) NOT NULL,
    potencia INTEGER CHECK (potencia > 0) NOT NULL,
    cilindrada INTEGER CHECK (cilindrada > 0) NOT NULL,
    ano INTEGER CHECK (ano > 0) NOT NULL,
    quilometros INTEGER CHECK (quilometros > -1) NOT NULL,
    condicao VARCHAR(100) CHECK (
        condicao IN (
            'Novo',
            'Usado',
            'Semi-novo'
        )
    ) NOT NULL,
    stand_id INTEGER REFERENCES standauto(stand_id) ON DELETE CASCADE
);
CREATE TABLE carro(
    matricula VARCHAR(20) REFERENCES veiculo(matricula) ON DELETE CASCADE,
    PRIMARY KEY(matricula),
    portas INTEGER CHECK (portas > 0) NOT NULL,
    lugares INTEGER CHECK (lugares > 0) NOT NULL,
    caixa VARCHAR(30) CHECK (
        caixa IN (
            'Manual',
            'Automatica'
        )
    ) NOT NULL,
);
CREATE TABLE mota(
    matricula VARCHAR(20) REFERENCES veiculo(matricula) ON DELETE CASCADE,
    PRIMARY KEY(matricula)
);
-- cliente--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
CREATE TABLE cliente(
    nif INTEGER CHECK (nif < 500000000) NOT NULL,
    PRIMARY KEY(nif),
    nome VARCHAR(255) NOT NULL,
    telefone NO_TELEFONE UNIQUE,
    email EMAILADDRESS UNIQUE CHECK (
        email LIKE '%@%.%'
        AND email NOT LIKE '%@%@%'
    )
);
--OPEREACOES cliente--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
CREATE TABLE aluguer(
    id INTEGER IDENTITY(1, 1) PRIMARY KEY,
    cliente INTEGER REFERENCES cliente(nif) ON DELETE SET NULL,
    veiculo VARCHAR(20) REFERENCES veiculo(matricula) ON DELETE SET NULL,
    data_inicio DATE DEFAULT GETDATE() NOT NULL,
    data_fim DATE NOT NULL,
    montante MONEY CHECK (montante >= 0) NOT NULL,
);
CREATE TABLE venda(
    id INTEGER IDENTITY(1, 1) PRIMARY KEY,
    cliente INTEGER REFERENCES cliente(nif) ON DELETE SET NULL,
    veiculo VARCHAR(20) REFERENCES veiculo(matricula) ON DELETE SET NULL,
    data_venda DATE NOT NULL DEFAULT GETDATE(),
    montante MONEY CHECK (montante >= 0) NOT NULL,
    opcoes_pagamento VARCHAR(255) CHECK (
        opcoes_pagamento IN (
            'Dinheiro',
            'Cheque',
            'Transferencia',
            'Cartao de Credito'
        )
    ) NOT NULL,
    vendedor INTEGER REFERENCES vendedor(vendedor_id) ON DELETE
    SET NULL
);
CREATE TABLE compra_clientes(
    id INTEGER IDENTITY(1, 1) PRIMARY KEY,
    cliente INTEGER REFERENCES cliente(nif) ON DELETE SET NULL,
    veiculo VARCHAR(20) REFERENCES veiculo(matricula) ON DELETE SET NULL,
    data_compra DATE DEFAULT GETDATE() NOT NULL,
    montante MONEY CHECK (montante >= 0) NOT NULL
);
CREATE TABLE reparacao(
    id INTEGER IDENTITY(1, 1) PRIMARY KEY,
    cliente INTEGER REFERENCES cliente(nif) ON DELETE SET NULL,
    veiculo VARCHAR(20) REFERENCES veiculo(matricula) ON DELETE SET NULL,
    data_inicio DATE DEFAULT GETDATE() NOT NULL,
    data_fim DATE,
    montante MONEY CHECK (montante >= 0) NOT NULL,
    mecanico INTEGER REFERENCES mecanico(mecanico_id) ON DELETE SET NULL
);
-- peca--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
CREATE TABLE peca(
    designacao VARCHAR(255),
    PRIMARY KEY(designacao),
    categoria VARCHAR(100) NOT NULL,
    sub_categoria VARCHAR(100),
    standauto Integer REFERENCES standauto(stand_id) ON DELETE CASCADE
);
CREATE TABLE venda_peca(
    id INTEGER IDENTITY(1, 1) PRIMARY KEY,
    peca VARCHAR(255) REFERENCES peca(designacao) ON DELETE SET NULL,
    cliente Integer REFERENCES cliente(nif) ON DELETE SET NULL,
    data_venda DATE DEFAULT GETDATE() NOT NULL,
    montante MONEY CHECK (montante >= 0) NOT NULL,
    quantidade INTEGER CHECK (quantidade > 0) NOT NULL
);
CREATE TABLE fornecedor(
    nif INTEGER CHECK (nif >= 500000000) NOT NULL,
    PRIMARY KEY(nif),
    nome VARCHAR(255) NOT NULL,
    morada VARCHAR(255) NOT NULL,
    telefone NO_TELEFONE UNIQUE,
    email EMAILADDRESS UNIQUE CHECK (
        email LIKE '%@%.%'
        AND email NOT LIKE '%@%@%'
    )
);
CREATE TABLE encomenda_peca(
    id INTEGER IDENTITY(1, 1) PRIMARY KEY,
    peca VARCHAR(255) REFERENCES peca(designacao) ON DELETE SET NULL,
    fornecedor INT REFERENCES fornecedor(nif) ON DELETE SET NULL,
    data_encomenda DATE DEFAULT GETDATE() NOT NULL,
    montante MONEY CHECK (montante >= 0) NOT NULL,
    quantidade INTEGER CHECK (quantidade > 0) NOT NULL
);
-- importador--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
CREATE TABLE importador(
    nif INTEGER,
    PRIMARY KEY(nif),
    nome VARCHAR(255) NOT NULL,
    morada VARCHAR(255) NOT NULL,
    telefone NO_TELEFONE UNIQUE,
    email EMAILADDRESS UNIQUE CHECK (
        email LIKE '%@%.%'
        AND email NOT LIKE '%@%@%'
    )
);
CREATE TABLE compra_importador(
    id INTEGER IDENTITY(1, 1) PRIMARY KEY,
    importador INTEGER REFERENCES importador(nif) ON DELETE SET NULL,
    veiculo VARCHAR(20) REFERENCES veiculo(matricula) ON DELETE SET NULL,
    data_compra DATE DEFAULT GETDATE() NOT NULL,
    montante MONEY CHECK (montante >= 0) NOT NULL
);
CREATE TABLE utilizador_bd(
    nome VARCHAR(32) PRIMARY KEY CHECK (LEN(nome) > 3),
    password_hash VARBINARY(64) NOT NULL
);