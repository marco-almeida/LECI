CREATE SCHEMA atl;
go
CREATE TABLE atl.turma(
    id INTEGER,
    max_alunos INTEGER,
    ano_letivo INTEGER,
    designacao VARCHAR,
    num_func_professor INTEGER,
    PRIMARY KEY(id)
);
CREATE TABLE atl.professor(
    num_func INTEGER,
    cc INTEGER,
    nome VARCHAR,
    morada VARCHAR,
    data_nasc DATE,
    mail VARCHAR,
    telefone INTEGER,
    PRIMARY KEY(num_func)
);
CREATE TABLE atl.atividade(
    id INTEGER,
    designacao VARCHAR,
    custo MONEY,
    num_prof INTEGER,
    PRIMARY KEY(id)
);
CREATE TABLE atl.participa(
    id_turma INTEGER,
    id_atividade INTEGER,
);
CREATE TABLE atl.tem_participante(
    id_atividade INTEGER,
    cc_aluno INTEGER,
);
CREATE TABLE atl.aluno(
    cc INTEGER,
    morada VARCHAR,
    nome VARCHAR,
    data_nasc DATE,
    PRIMARY KEY(cc)
);
CREATE TABLE atl.autorizacao(
    cc_pessoa INTEGER,
    cc_aluno INTEGER,
);
CREATE TABLE atl.pessoa_autorizada(
    cc INTEGER,
    nome VARCHAR,
    morada VARCHAR,
    email VARCHAR,
    telefone VARCHAR,
    data_nasc DATE,
    PRIMARY KEY(cc)
);
ALTER TABLE atl.turma
ADD CONSTRAINT FK_1 FOREIGN KEY (num_func_professor) REFERENCES atl.professor(num_func);
ALTER TABLE atl.atividade
ADD CONSTRAINT FK_2 FOREIGN KEY (num_prof) REFERENCES atl.professor(num_func);
ALTER TABLE atl.participa
ADD CONSTRAINT FK_3 FOREIGN KEY (id_turma) REFERENCES atl.turma(id);
ALTER TABLE atl.participa
ADD CONSTRAINT FK_4 FOREIGN KEY (id_atividade) REFERENCES atl.atividade(id);
ALTER TABLE atl.tem_participante
ADD CONSTRAINT FK_5 FOREIGN KEY (id_atividade) REFERENCES atl.atividade(id);
ALTER TABLE atl.tem_participante
ADD CONSTRAINT FK_6 FOREIGN KEY (cc_aluno) REFERENCES atl.aluno(cc);
ALTER TABLE atl.autorizacao
ADD CONSTRAINT FK_7 FOREIGN KEY (cc_pessoa) REFERENCES atl.pessoa_autorizada(cc);
ALTER TABLE atl.autorizacao
ADD CONSTRAINT FK_8 FOREIGN KEY (cc_aluno) REFERENCES atl.aluno(cc);