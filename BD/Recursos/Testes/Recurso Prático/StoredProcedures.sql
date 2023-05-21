--SP que lista os IDs dos autores para o id do Artigo inserido
CREATE PROCEDURE searchAutoresByArtigo @artigo int
AS
	SELECT DISTINCT id_autor FROM Artigo_Autor WHERE n_registo=@artigo
GO

--SP que lista os IDS dos livros para o id do Autor inserido
CREATE PROCEDURE searchArtigosByAutor @autor int
AS
	SELECT DISTINCT n_registo FROM Artigo_Autor WHERE id_autor=@autor
GO

--SP que lista os IDS dos participantes do congresso para o id da Instituicao inserido
CREATE PROCEDURE searchParticipantesByInstituicao @instituicao int
AS
	SELECT DISTINCT id_participante FROM Participante WHERE id_instituicao=@instituicao
GO

--SP que lista os IDS dos Artigos para o id da Sessao inserido
CREATE PROCEDURE searchArtigosBySessao @sessao int
AS
	SELECT DISTINCT n_registo FROM Apresentacao WHERE id_sessao=@sessao
GO

--SP para pesquisar o número médio de autores por artigo (parâmetro de entrada irrelevante)
CREATE PROCEDURE searchMediaAutorByArtigo @artigo int
AS
	SELECT COUNT(DISTINCT id_autor)/COUNT(DISTINCT n_registo) AS avg FROM Artigo_Autor
GO
