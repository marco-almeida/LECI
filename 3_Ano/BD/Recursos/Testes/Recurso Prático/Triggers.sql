--Trigger para verificar se o autor que apresenta um artigo é autor desse artigo

CREATE TRIGGER checkIsAutor ON Apresentacao
AFTER INSERT, UPDATE
AS
	DECLARE @autor int
	SELECT @autor=inserted.id_autor FROM inserted
	DECLARE @id_artigo int
	SELECT @id_artigo=inserted.n_registo FROM inserted
	IF @autor NOT IN (SELECT id_autor FROM (Artigo JOIN Artigo_Autor ON Artigo.n_registo=Artigo_Autor.n_registo)
	WHERE Artigo.n_registo = @id_artigo)
	BEGIN
		RAISERROR('O Autor que apresenta deve ser autor do artigo', 16, 1)
		ROLLBACK
	END
GO

--Trigger para verificar se o autor está inscrito como participante

CREATE TRIGGER checkAutorIsParticipante ON Apresentacao
AFTER INSERT, UPDATE
AS
	DECLARE @autor int
	SELECT @autor=inserted.id_autor FROM inserted
	DECLARE @id_artigo int
	SELECT @id_artigo=inserted.n_registo FROM inserted
	IF (SELECT id_participante FROM (Inserted JOIN Autor ON Inserted.id_autor=Autor.id_autor)) IS NULL
	BEGIN
		RAISERROR('O Autor deve que apresenta deve estar inscrito como participante', 16, 1)
		ROLLBACK
	END
GO

--Trigger para verificar se uma sessão só tem no máximo 2 moderadores
CREATE TRIGGER checkNumModsInSessao ON Moderador_Sessao
AFTER INSERT, UPDATE
AS
	IF (SELECT COUNT(DISTINCT Moderador_Sessao.id_moderador) FROM (Inserted JOIN Moderador_Sessao ON Inserted.id_sessao=Moderador_Sessao.id_sessao)) > 1
	BEGIN
		RAISERROR('A sessao so pode ter no máximo 2 moderadores', 16, 1)
		ROLLBACK
	END
GO

--Trigger para verificar se o moderador é autor de algum artigo da sessao
CREATE TRIGGER checkModIsNotautor ON Moderador_Sessao
AFTER INSERT, UPDATE
AS
	DECLARE @moderador int
	SELECT @moderador=inserted.id_moderador FROM inserted
	IF (SELECT Moderador.id_particpante FROM Moderador WHERE id_moderador=@moderador) IN (SELECT Autor.id_participante FROM ((Inserted JOIN Apresentacao ON Inserted.id_sessao=Apresentacao.id_sessao) JOIN Artigo_Autor ON Apresentacao.n_registo = Artigo_Autor.n_registo) JOIN Autor ON Autor.id_autor=Artigo_Autor.id_autor)
	BEGIN
		RAISERROR('O moderador nao pode ser autor de artigos desta sessao', 16, 1)
		ROLLBACK
	END
GO