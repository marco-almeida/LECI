insert into Medico values
	(101,'Joao Pires Lima', 'Cardiologia'),
	(102,'Manuel Jose Rosa', 'Cardiologia'),
	(103,'Rui Luis Caraca', 'Pneumologia'),
	(104,'Sofia Sousa Silva', 'Radiologia'),
	(105,'Ana Barbosa', 'Neurologia');


insert into Paciente(
	NSNS, Nome, Data_nasc, Endereco
)
values
	(1,'Renato Manuel Cavaco','1980-01-03','Rua Nova do Pilar 35'),
	(2,'Paula Vasco Silva','1972-10-30','Rua Direita 43'),
	(3,'Ines Couto Souto','1985-05-12','Rua de Cima 144'),
	(4,'Rui Moreira Porto','1970-12-12','Rua Zig Zag 235'),
	(5,'Manuel Zeferico Polaco','1990-06-05','Rua da Baira Rio 1135');



insert into Farmacia(
	Nome, Telefone, Endereco
)
values
	('Farmacia BelaVista',221234567,'Avenida Principal 973'),
	('Farmacia Central',234370500,'Avenida da Liberdade 33'),
	('Farmacia Peixoto',234375111,'Largo da Vila 523'),
	('Farmacia Vitalis',229876543,'Rua Visconde Salgado 263');



insert into Farmaceutica(
	Nreg, Nome, Endereco
)
values
	(905,'Roche','Estrada Nacional 249'),
	(906,'Bayer','Rua da Quinta do Pinheiro 5'),
	(907,'Pfizer','Empreendimento Lagoas Park - Edificio 7'),
	(908,'Merck', 'Alameda Fern�o Lopes 12');


insert into Farmaco(
	Nreg, Nome, Formula
)
values
	(905,'Boa Saude em 3 Dias','XZT9'),
	(906,'Voltaren Spray','PLTZ32'),
	(906,'Xelopironi 350','FRR-34'),
	(906,'Gucolan 1000','VFR-750'),
	(907,'GEROaero Rapid','DDFS-XEN9'),
	(908,'Aspirina 1000','BIOZZ02');



insert into Prescricao(
	Nuni, NSNS, NID, Nome_farmacia, Data_proc
)
values
	(10001,1,105,'Farmacia Central','2015-03-03'),
	(10002,1,105,NULL,NULL),
	(10003,3,102,'Farmacia Central','2015-01-17'),
	(10004,3,101,'Farmacia BelaVista','2015-02-09'),
	(10005,3,102,'Farmacia Central','2015-01-17'),
	(10006,4,102,'Farmacia Vitalis','2015-02-22'),
	(10007,5,103,NULL,NULL),
	(10008,1,103,'Farmacia Central','2015-01-02'),
	(10009,3,102,'Farmacia Peixoto','2015-02-02');


insert into Presc_farmaco(
	Num_presc, Num_reg_farm, Nome_farmaco
)
values
	(10001,905,'Boa Saude em 3 Dias'),
	(10002,907,'GEROaero Rapid'),
	(10003,906,'Voltaren Spray'),
	(10003,906,'Xelopironi 350'),
	(10003,908,'Aspirina 1000'),
	(10004,905,'Boa Saude em 3 Dias'),
	(10004,908,'Aspirina 1000'),
	(10005,906,'Voltaren Spray'),
	(10006,905,'Boa Saude em 3 Dias'),
	(10006,906,'Voltaren Spray'),
	(10006,906,'Xelopironi 350'),
	(10006,908,'Aspirina 1000'),
	(10007,906,'Voltaren Spray'),
	(10008,905,'Boa Saude em 3 Dias'),
	(10008,908,'Aspirina 1000'),
	(10009,905,'Boa Saude em 3 Dias'),
	(10009,906,'Voltaren Spray'),
	(10009,908,'Aspirina 1000');