USE [rec_g1]
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Apresentacao](
	[id_apresentacao] [int] NOT NULL,
	[id_sessao] [int] NOT NULL,
	[id_autor] [int] NOT NULL,
	[tempo] [time](7) NOT NULL,
	[n_registo] [int] NOT NULL,
 CONSTRAINT [PK_Apresentacao] PRIMARY KEY CLUSTERED 
(
	[id_apresentacao] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Artigo](
	[titulo] [varchar](50) NOT NULL,
	[n_registo] [int] NOT NULL,
 CONSTRAINT [PK_Artigo] PRIMARY KEY CLUSTERED 
(
	[n_registo] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Artigo_Autor](
	[n_registo] [int] NOT NULL,
	[id_autor] [int] NOT NULL,
 CONSTRAINT [PK_Artigo_Autor] PRIMARY KEY CLUSTERED 
(
	[n_registo] ASC,
	[id_autor] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Autor](
	[id_autor] [int] NOT NULL,
	[email] [varchar](50) NOT NULL,
	[id_instituicao] [int] NOT NULL,
	[id_participante] [int] NULL,
	[nome] [varchar](50) NOT NULL,
 CONSTRAINT [PK_Autor] PRIMARY KEY CLUSTERED 
(
	[id_autor] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Comprovativo](
	[id_comprovativo] [int] NOT NULL,
	[id_instituicao] [int] NOT NULL,
	[localizacao] [varchar](50) NOT NULL,
 CONSTRAINT [PK_Comprovativo] PRIMARY KEY CLUSTERED 
(
	[id_comprovativo] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Estudante](
	[id_estudante] [int] NOT NULL,
	[id_participante] [int] NOT NULL,
	[id_comprovativo] [int] NOT NULL,
 CONSTRAINT [PK_Estudante] PRIMARY KEY CLUSTERED 
(
	[id_estudante] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Instituicao](
	[id_instituicao] [int] NOT NULL,
	[nome] [varchar](50) NOT NULL,
	[endereco] [varchar](50) NOT NULL,
 CONSTRAINT [PK_Instituicao] PRIMARY KEY CLUSTERED 
(
	[id_instituicao] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Moderador](
	[id_moderador] [int] NOT NULL,
	[id_particpante] [int] NOT NULL,
 CONSTRAINT [PK_Moderador] PRIMARY KEY CLUSTERED 
(
	[id_moderador] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Moderador_Sessao](
	[id_sessao] [int] NOT NULL,
	[id_moderador] [int] NOT NULL,
 CONSTRAINT [PK_Moderador_Sessao] PRIMARY KEY CLUSTERED 
(
	[id_sessao] ASC,
	[id_moderador] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NaoEstudante](
	[id_naoestudante] [int] NOT NULL,
	[id_participante] [int] NOT NULL,
	[referencia] [varchar](50) NOT NULL,
 CONSTRAINT [PK_NaoEstudante] PRIMARY KEY CLUSTERED 
(
	[id_naoestudante] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Participante](
	[id_participante] [int] NOT NULL,
	[nome] [varchar](50) NOT NULL,
	[morada] [varchar](50) NOT NULL,
	[email] [varchar](50) NOT NULL,
	[id_instituicao] [int] NOT NULL,
	[data_inscricao] [date] NOT NULL,
	[valor_inscricao] [float] NOT NULL,
 CONSTRAINT [PK_Participante] PRIMARY KEY CLUSTERED 
(
	[id_participante] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Sala](
	[desig] [varchar](50) NOT NULL,
	[id_sala] [int] NOT NULL,
	[edificio] [varchar](50) NOT NULL,
	[piso] [int] NOT NULL,
	[n_lugares] [int] NOT NULL,
 CONSTRAINT [PK_Sala] PRIMARY KEY CLUSTERED 
(
	[id_sala] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Sessao](
	[desig] [varchar](50) NOT NULL,
	[dia] [date] NOT NULL,
	[hora] [time](7) NOT NULL,
	[id_sala] [int] NOT NULL,
	[duracao] [time](7) NOT NULL,
	[id_sessao] [int] NOT NULL,
 CONSTRAINT [PK_Sessao] PRIMARY KEY CLUSTERED 
(
	[id_sessao] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[Apresentacao]  WITH CHECK ADD  CONSTRAINT [FK_Apresentacao_Artigo] FOREIGN KEY([id_autor])
REFERENCES [dbo].[Artigo] ([n_registo])
GO
ALTER TABLE [dbo].[Apresentacao] CHECK CONSTRAINT [FK_Apresentacao_Artigo]
GO
ALTER TABLE [dbo].[Apresentacao]  WITH CHECK ADD  CONSTRAINT [FK_Apresentacao_Autor2] FOREIGN KEY([id_autor])
REFERENCES [dbo].[Autor] ([id_autor])
GO
ALTER TABLE [dbo].[Apresentacao] CHECK CONSTRAINT [FK_Apresentacao_Autor2]
GO
ALTER TABLE [dbo].[Apresentacao]  WITH CHECK ADD  CONSTRAINT [FK_Apresentacao_Sessao] FOREIGN KEY([id_sessao])
REFERENCES [dbo].[Sessao] ([id_sessao])
GO
ALTER TABLE [dbo].[Apresentacao] CHECK CONSTRAINT [FK_Apresentacao_Sessao]
GO
ALTER TABLE [dbo].[Artigo_Autor]  WITH CHECK ADD  CONSTRAINT [FK_Artigo_Autor_Artigo] FOREIGN KEY([n_registo])
REFERENCES [dbo].[Artigo] ([n_registo])
GO
ALTER TABLE [dbo].[Artigo_Autor] CHECK CONSTRAINT [FK_Artigo_Autor_Artigo]
GO
ALTER TABLE [dbo].[Artigo_Autor]  WITH CHECK ADD  CONSTRAINT [FK_Artigo_Autor_Autor] FOREIGN KEY([id_autor])
REFERENCES [dbo].[Autor] ([id_autor])
GO
ALTER TABLE [dbo].[Artigo_Autor] CHECK CONSTRAINT [FK_Artigo_Autor_Autor]
GO
ALTER TABLE [dbo].[Autor]  WITH CHECK ADD  CONSTRAINT [FK_Autor_Instituicao] FOREIGN KEY([id_instituicao])
REFERENCES [dbo].[Instituicao] ([id_instituicao])
GO
ALTER TABLE [dbo].[Autor] CHECK CONSTRAINT [FK_Autor_Instituicao]
GO
ALTER TABLE [dbo].[Autor]  WITH CHECK ADD  CONSTRAINT [FK_Autor_Participante] FOREIGN KEY([id_participante])
REFERENCES [dbo].[Participante] ([id_participante])
GO
ALTER TABLE [dbo].[Autor] CHECK CONSTRAINT [FK_Autor_Participante]
GO
ALTER TABLE [dbo].[Comprovativo]  WITH CHECK ADD  CONSTRAINT [FK_Comprovativo_Instituicao] FOREIGN KEY([id_instituicao])
REFERENCES [dbo].[Instituicao] ([id_instituicao])
GO
ALTER TABLE [dbo].[Comprovativo] CHECK CONSTRAINT [FK_Comprovativo_Instituicao]
GO
ALTER TABLE [dbo].[Estudante]  WITH CHECK ADD  CONSTRAINT [FK_Estudante_Comprovativo] FOREIGN KEY([id_comprovativo])
REFERENCES [dbo].[Comprovativo] ([id_comprovativo])
GO
ALTER TABLE [dbo].[Estudante] CHECK CONSTRAINT [FK_Estudante_Comprovativo]
GO
ALTER TABLE [dbo].[Estudante]  WITH CHECK ADD  CONSTRAINT [FK_Estudante_Participante] FOREIGN KEY([id_participante])
REFERENCES [dbo].[Participante] ([id_participante])
GO
ALTER TABLE [dbo].[Estudante] CHECK CONSTRAINT [FK_Estudante_Participante]
GO
ALTER TABLE [dbo].[Moderador]  WITH CHECK ADD  CONSTRAINT [FK_Moderador_Participante] FOREIGN KEY([id_particpante])
REFERENCES [dbo].[Participante] ([id_participante])
GO
ALTER TABLE [dbo].[Moderador] CHECK CONSTRAINT [FK_Moderador_Participante]
GO
ALTER TABLE [dbo].[Moderador_Sessao]  WITH CHECK ADD  CONSTRAINT [FK_Moderador_Sessao_Moderador] FOREIGN KEY([id_moderador])
REFERENCES [dbo].[Moderador] ([id_moderador])
GO
ALTER TABLE [dbo].[Moderador_Sessao] CHECK CONSTRAINT [FK_Moderador_Sessao_Moderador]
GO
ALTER TABLE [dbo].[Moderador_Sessao]  WITH CHECK ADD  CONSTRAINT [FK_Moderador_Sessao_Sessao] FOREIGN KEY([id_sessao])
REFERENCES [dbo].[Sessao] ([id_sessao])
GO
ALTER TABLE [dbo].[Moderador_Sessao] CHECK CONSTRAINT [FK_Moderador_Sessao_Sessao]
GO
ALTER TABLE [dbo].[NaoEstudante]  WITH CHECK ADD  CONSTRAINT [FK_NaoEstudante_Participante] FOREIGN KEY([id_participante])
REFERENCES [dbo].[Participante] ([id_participante])
GO
ALTER TABLE [dbo].[NaoEstudante] CHECK CONSTRAINT [FK_NaoEstudante_Participante]
GO
ALTER TABLE [dbo].[Participante]  WITH CHECK ADD  CONSTRAINT [FK_Participante_Instituicao] FOREIGN KEY([id_instituicao])
REFERENCES [dbo].[Instituicao] ([id_instituicao])
GO
ALTER TABLE [dbo].[Participante] CHECK CONSTRAINT [FK_Participante_Instituicao]
GO
ALTER TABLE [dbo].[Sessao]  WITH CHECK ADD  CONSTRAINT [FK_Sessao_Sala] FOREIGN KEY([id_sala])
REFERENCES [dbo].[Sala] ([id_sala])
GO
ALTER TABLE [dbo].[Sessao] CHECK CONSTRAINT [FK_Sessao_Sala]
GO


INSERT INTO Instituicao VALUES(0, 'Universidade de Aveiro', 'Aveiro')
INSERT INTO Instituicao VALUES(1, 'Universidade da Beira Interior', 'Covilha')
INSERT INTO Instituicao VALUES(2, 'Instituto Politecnico de Castelo Branco', 'Castelo Branco')

INSERT INTO Participante VALUES(0, 'Guilherme Antunes', 'Tramagal', 'guilhermeantunes@gmail.com', 0, '12/7/2022', 15.30)
INSERT INTO Participante VALUES(1, 'Rafael Jesus', 'Castelo Branco', 'rafaeljesus@gamil.com', 1, '12/7/2022', 15.30)
INSERT INTO Participante VALUES(2, 'Francisco Reis', 'Castelo Branco', 'franciscoreis@gmail.com', 1, '12/7/2022', 15.30)
INSERT INTO Participante VALUES(3, 'João Esteves', 'Arronches', 'joaomaria@gmail.com', 2, '12/7/2002', 15.30)
INSERT INTO Participante VALUES(4, 'Daniel Ferreira', 'Boliqueime', 'danielferreira@gmail.com', 0, '12/7/2022', 15.30)
INSERT INTO Participante VALUES(5, 'Goncalo Silva', 'Coimbra', 'goncalo&sara@gmail.com', 0, '11/7/2022', 12.00)
INSERT INTO Participante VALUES(6, 'Vicente Barros', 'São João da Pesqueira', 'bishente@gmail.com', 0, '11/7/2022', 12.00)

INSERT INTO NaoEstudante VALUES(0, 4, '3333-4444-5555')

INSERT INTO Comprovativo VALUES(0,0,'www.comprovativo0.com')
INSERT INTO Comprovativo VALUES(1,1,'www.comprovativo1.com')
INSERT INTO Comprovativo VALUES(2,2,'www.comprovativo2.com')

INSERT INTO Estudante VALUES(0,0,0)
INSERT INTO Estudante VALUES(1,1,1)
INSERT INTO Estudante VALUES(2,2,1)
INSERT INTO Estudante VALUES(3,3,2)
INSERT INTO Estudante VALUES(4,5,0)
INSERT INTO Estudante VALUES(5,6,0)

INSERT INTO Autor VALUES(0, 'danielferreira@pe.com', 0, 4, 'Daniel Ferreira')
INSERT INTO Autor VALUES(1, 'joaomaria@raiz.com', 2, 3, 'Joao Maria')
INSERT INTO Autor VALUES(2, 'reis@pe.com', 1, 2, 'Francisco Reis')

INSERT INTO Artigo VALUES('A Arte da Guerra', 0)
INSERT INTO Artigo VALUES('Porque a America nao e o melhor pais do Mundo', 1)
INSERT INTO Artigo VALUES('Evolucao Tecnologica', 2)
INSERT INTO Artigo VALUES('Legado Hindu', 3)

INSERT INTO Artigo_Autor VALUES(0,0)
INSERT INTO Artigo_Autor VALUES(1,0)
INSERT INTO Artigo_Autor VALUES(2,1)
INSERT INTO Artigo_Autor VALUES(3,2)

INSERT INTO Sala VALUES('Sala José Meazes', 0, 'DETI', 2, 80)
INSERT INTO Sala VALUES('Sala Artur Mata', 1, 'DETI', 2, 90)

INSERT INTO Sessao VALUES('O Mundo', '12/7/2022', '10:00', 0, '03:00', 0)
INSERT INTO Sessao VALUES('Novo Mundo', '11/7/2022', '10:00', 1, '03:00', 1)

INSERT INTO Apresentacao VALUES(0, 0, 0, '00:20', 0)
INSERT INTO Apresentacao VALUES(1, 1, 1, '00:10', 2)
INSERT INTO Apresentacao VALUES(2, 0, 2, '00:05', 3)

INSERT INTO Moderador VALUES(0,5)
INSERT INTO Moderador VALUES(1,6)
INSERT INTO Moderador VALUES(2,0)
INSERT INTO Moderador VALUES(3,3)

INSERT INTO Moderador_Sessao VALUES(0,0)
INSERT INTO Moderador_Sessao VALUES(0,1)
INSERT INTO Moderador_Sessao VALUES(1,0)
