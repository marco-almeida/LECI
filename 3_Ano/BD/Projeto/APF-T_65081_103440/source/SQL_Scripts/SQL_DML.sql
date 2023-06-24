-- standauto
INSERT INTO standauto
VALUES  ('Portugal','Rua dos Stands, 123','stpt@standauto.com','+351123456789','1234-567'),
        ('Espanha','Calle de los Stands, 456','stes@standauto.com','+34392048374','5678-901'),
        ('França','Rue des Stands, 789','stfr@standauto.com','+33111222333','8901-234');

-- funcionario
INSERT INTO funcionario
VALUES  ('Joao Silva','joao.silva@standautostaff.com','+351392048374','Rua dos Funcionarios, 1',1),
        ('Pedro Almeida','pedro.almeida@standautostaff.com','+351222333444','Rua das Oliveiras, 3',1),
        ('Miguel Costa','miguel.costa@standautostaff.com','+351888999000','Praça Central, 5',1),
        ('Manuel Santos','manuel.santos@standautostaff.com','+351222330444','Rua dos Funcionarios, 12',1),
        ('Juan Garcia','juan.garcia@standautostaff.com','+34111202333','Calle de los Empleados, 6',2),
        ('Maria Lopez','maria.lopez@standautostaff.com','+34444555666','Avenida de los Empleados, 7',2),
        ('Carlos Hernandez','carlos.hernandez@standautostaff.com','+34999888777','Calle de los Empleados, 9',2),
        ('Pierre Martin','pierre.martin@standautostaff.com','+33111221333','Rue des Employes, 10',3),
        ('Sophie Dubois','sophie.dubois@standautostaff.com','+33444555666','Avenue des Employes, 11',3),
        ('Jean Dupont','jean.dupont@standautostaff.com','+33777888999','Rue des Employes, 8',3);

-- vendedor
INSERT INTO vendedor
VALUES  (1, 0),
        (4, 0),
        (7, 0),
        (10, 0);

-- mecanico
INSERT INTO mecanico
VALUES  (2),
        (5),
        (8);

-- gerente
INSERT INTO gerente
VALUES  (3);
INSERT INTO gerente
VALUES  (6);
INSERT INTO gerente
VALUES  (9);

-- certificacoes
INSERT INTO certificacoes
VALUES  ('Mecanica'),
        ('Mecatronica'),
        ('Eletronica'),
        ('Informatica'),
        ('Gestao');

-- gerente_certificacoes
INSERT INTO gerente_certificacoes
VALUES  (9, 'Gestao'),
        (3, 'Informatica'),
        (3, 'Gestao'),
        (9, 'Eletronica');

-- veiculo
INSERT INTO veiculo
VALUES  ('AB-123-CD','Volkswagen','Golf','Gasolina',150,1600,2018,50000,'Usado',1),
        ('BC-234-DE','BMW','Série 3','Diesel',190,2000,2019,40000,'Usado',1),
        ('CD-345-EF','Audi','A4','Gasolina',180,1800,2020,20000,'Semi-novo',1),
        ('DE-456-FG','Mercedes-Benz','Classe C','Diesel',170,2200,2017,80000,'Usado',2),
        ('EF-567-GH','Renault','Clio','Gasolina',90,1200,2016,60000,'Usado',2),
        ('FG-678-HI','Peugeot','308','Diesel',130,1600,2018,40000,'Usado',2),
        ('GH-789-IJ','Volkswagen','Polo','Gasolina',95,1000,2020,10000,'Semi-novo',3),
        ('HI-890-JK','Ford','Focus','Diesel',120,1600,2019,30000,'Usado',3),
        ('IJ-901-KL','Mercedes-Benz','Classe A','Gasolina',150,1800,2021,5000,'Semi-novo',3),
        ('JK-012-LM','BMW','Série 1','Diesel',140,2000,2017,70000,'Usado',1),
        ('KL-123-MN','Audi','A3','Gasolina',110,1600,2018,40000,'Usado',2),
        ('LM-234-NO','Volkswagen','Passat','Diesel',190,2000,2020,20000,'Semi-novo',3),
        ('MN-345-OP','Honda','CBR600RR','Gasolina',120,600,2019,10000,'Semi-novo',2),
        ('NO-456-PQ','Yamaha','YZF-R1','Gasolina',200,1000,2020,8000,'Usado',2),
        ('OP-567-QR','Kawasaki','Ninja ZX-10R','Gasolina',185,1000,2018,12000,'Usado',3),
        ('XY-321-YZ','Ford','Fiesta','Diesel',65,1200,1993,284628,'Usado',1),

        ('UV-901-WX','Volkswagen','Tiguan','Gasolina',150,1600,2019,20000,'Usado', null),
        ('WX-012-XY','Ford','Mustang','Gasolina',450,5000,2020,10000,'Semi-novo',null),
        ('XY-123-YZ','BMW','R 1250 GS','Gasolina',134,1250,2021,5000,'Semi-novo',null),
        ('YZ-234-ZA','Audi','A5','Diesel',190,2000,2020,30000,'Usado',null),
        ('ZA-345-AB','Honda','CBR1000RR','Gasolina',189,1000,2018,8000,'Usado',null),
        ('AB-456-BC','Mercedes-Benz','GLC','Diesel',170,2200,2019,40000,'Usado',null),
        ('BC-567-CD','Kawasaki','Ninja 650','Gasolina',67,650,2020,10000,'Semi-novo',null),
        ('CD-678-DE','Toyota','Corolla','Gasolina',140,1600,2021,1000,'Novo',null),
        ('DE-789-EF','Harley-Davidson','Sportster Iron 883','Gasolina',50,883,2018,5000,'Usado',null),
        ('EF-890-FG','Chevrolet','Camaro','Gasolina',455,6200,2022,500,'Novo',null),
        ('FG-901-GH','Suzuki','GSX-R750','Gasolina',148,750,2020,6000,'Usado',null),
        ('GH-012-HI','Volvo','XC60','Diesel',190,2000,2021,15000,'Semi-novo',null),
        ('HI-123-IJ','Triumph','Street Triple RS','Gasolina',123,765,2019,4000,'Usado',null),
        ('IJ-234-JK','Nissan','Qashqai','Gasolina',140,1600,2020,20000,'Semi-novo',null),
        ('JK-345-KL','KTM','Duke 390','Gasolina',43,390,2018,10000,'Usado',null),
        ('KL-456-LM','Hyundai','Tucson','Gasolina',150,1600,2019,15000,'Usado',null),
        ('LM-567-MN','Ducati','Monster 821','Gasolina',109,821,2020,3000,'Usado',null),
        ('MN-678-NO','Mazda','CX-5','Gasolina',165,2000,2021,8000,'Semi-novo',null),
        ('NO-789-OP','Kawasaki','Versys 650','Gasolina',68,650,2018,12000,'Usado',null),
        ('OP-890-PQ','Jeep','Wrangler','Gasolina',270,3600,2022,2000,'Novo',null),
        ('PQ-901-RS','Ford','Explorer','Gasolina',300,3500,2022,5000,'Semi-novo',null),
        ('RS-012-ST','Chevrolet','Equinox','Diesel',170,2000,2021,10000,'Usado',null),
        ('ST-123-TU','Yamaha','YZF-R6','Gasolina',120,600,2018,8000,'Usado',null),
        ('TU-234-UV','Volkswagen','T-Cross','Gasolina',115,1000,2020,15000,'Semi-novo',null),
        ('UV-345-VW','Kawasaki','Ninja H2','Gasolina',231,1000,2020,5000,'Semi-novo',null),
        ('VW-456-WX','Audi','A3 Sedan','Diesel',150,2000,2019,20000,'Usado',null),
        ('WX-567-XY','Honda','CB500X','Gasolina',47,500,2018,10000,'Usado',null);


-- carro
INSERT INTO carro
VALUES  ('AB-123-CD', 5, 5, 'Manual'),
        ('BC-234-DE', 3, 2, 'Automatica'),
        ('CD-345-EF', 5, 5, 'Manual'),
        ('DE-456-FG', 5, 5, 'Automatica'),
        ('EF-567-GH', 3, 2, 'Manual'),
        ('FG-678-HI', 5, 5, 'Automatica'),
        ('GH-789-IJ', 3, 2, 'Manual'),
        ('HI-890-JK', 5, 5, 'Automatica'),
        ('IJ-901-KL', 3, 2, 'Manual'),
        ('JK-012-LM', 5, 5, 'Automatica'),
        ('KL-123-MN', 3, 2, 'Manual'),
        ('LM-234-NO', 5, 5, 'Automatica'),
        ('XY-321-YZ', 5, 5, 'Manual'),

        ('UV-901-WX', 5, 5, 'Automatica'),
        ('YZ-234-ZA', 5, 5, 'Automatica'),
        ('AB-456-BC', 5, 5, 'Automatica'),
        ('CD-678-DE', 5, 5, 'Automatica'),
        ('GH-012-HI', 5, 5, 'Automatica'),
        ('IJ-234-JK', 5, 5, 'Automatica'),
        ('KL-456-LM', 5, 5, 'Automatica'),
        ('MN-678-NO', 5, 5, 'Automatica'),
        ('NO-789-OP', 5, 5, 'Automatica'),
        ('PQ-901-RS', 5, 7, 'Automatica'),
        ('RS-012-ST', 5, 5, 'Automatica'),
        ('TU-234-UV', 5, 5, 'Manual'),
        ('VW-456-WX', 5, 5, 'Automatica'),
        ('WX-012-XY', 5, 5, 'Manual');



-- mota
INSERT INTO mota (matricula)
VALUES  ('MN-345-OP'),
        ('NO-456-PQ'),
        ('OP-567-QR'),

        ('XY-123-YZ'),
        ('ZA-345-AB'),
        ('BC-567-CD'),
        ('FG-901-GH'),
        ('EF-890-FG'),
        ('HI-123-IJ'),
        ('JK-345-KL'),
        ('LM-567-MN'),
        ('OP-890-PQ'),
        ('ST-123-TU'),
        ('UV-345-VW'),
        ('WX-567-XY'),
        ('DE-789-EF');

-- cliente
INSERT INTO cliente
VALUES  (123456789,'João Silva','392048374','joao.silva@hotmail.com'),
        (392048374,'Maria Santos','123456779','maria.santos@hotmail.com'),
        (456789123,'Pedro Oliveira','789456123','pedro.oliveira@hotmail.com'),
        (294729102,'Ana Rodrigues','456789123','ana.rodrigues@hotmail.com'),
        (321654987,'Carlos Ferreira','954987321','carlos.ferreira@hotmail.com'),
        (194026837,'Sofia Almeida','321654987','sofia.almeida@hotmail.com'),
        (147258369,'Ricardo Costa','952363741','ricardo.costa@hotmail.com'),
        (258369147,'António Sousa','741852963','antonio.sousa@hotmail.com'),
        (153149190,'Inês Santos','369258147','ines.santos@hotmail.com'),
        (293612532,'Laura Silva','258369147','laura.silva@hotmail.com'),
        (157421963, 'Daniel Pereira', '852963741', 'daniel.pereira@hotmail.com'),
        (241852963, 'Beatriz Santos', '369258148', 'beatriz.santos@hotmail.com'),
        (387654321, 'Rui Martins', '123456789', 'rui.martins@hotmail.com'),
        (154321987, 'Ingrid Silva', '456789124', 'ingrid.silva@hotmail.com'),
        (221987654, 'Hugo Santos', '963852741', 'hugo.santos@hotmail.com'),
        (323456790, 'Sophie Martin', '392048375', 'sophie.martin@hotmail.com'),
        (492048375, 'Lucas Dubois', '123456790', 'lucas.dubois@hotmail.com'),
        (156789124, 'Emma Leroy', '789456124', 'emma.leroy@hotmail.com'),
        (258369148, 'Nicolas Laurent', '741852964', 'nicolas.laurent@hotmail.com'),
        (353149191, 'Emma Rousseau', '369258149', 'emma.rousseau@hotmail.com'),
        (493612533, 'Louis Petit', '258369148', 'louis.petit@hotmail.com'),
        (257421964, 'Mathilde Dupont', '852963742', 'mathilde.dupont@hotmail.com'),
        (141852964, 'Gabriel Mercier', '369258158', 'gabriel.mercier@hotmail.com'),
        (487654322, 'Léna Roy', '123456791', 'lena.roy@hotmail.com'),
        (444321988, 'Romain Simon', '456789125', 'romain.simon@hotmail.com'),
        (121987655, 'Manon Lemoine', '963852742', 'manon.lemoine@hotmail.com'),
        (223456791, 'Hugo Gauthier', '392048376', 'hugo.gauthier@hotmail.com'),
        (193612534, 'Carlos López', '258369149', 'carlos.lopez@hotmail.com'),
        (157421965, 'Laura Martínez', '852963743', 'laura.martinez@hotmail.com'),
        (241852965, 'José Rodríguez', '369258150', 'jose.rodriguez@hotmail.com'),
        (387654323, 'Sofía Fernández', '123456792', 'sofia.fernandez@hotmail.com'),
        (454321989, 'Manuel Torres', '456789126', 'manuel.torres@hotmail.com'),
        (154321990, 'Víctor Molina', '456789127', 'victor.molina@hotmail.com'),
        (221987657, 'María José Delgado', '963852744', 'mariajose.delgado@hotmail.com'),
        (194026841, 'Sara Peña', '321654991', 'sara.pena@hotmail.com'),
        (147258373, 'Héctor Ruiz', '952363745', 'hector.ruiz@hotmail.com'),
        (369258151, 'Ana María Castillo', '963741854', 'anamaria.castillo@hotmail.com');

-- venda
INSERT INTO venda
VALUES  (444321988,'VW-456-WX','2023-05-10',22500,'Transferencia',1),
        (157421965,'WX-567-XY','2023-04-10',57600,'Cheque',4),
        (492048375,'UV-901-WX','2023-05-10',22500,'Transferencia',1),
        
        (387654321,'WX-012-XY','2023-04-10',57600,'Cheque',4),
        (157421963,'XY-123-YZ','2023-05-15',18000,'Dinheiro',4),
        (194026841,'YZ-234-ZA','2023-06-02',35000,'Transferencia',7),
        (123456789,'ZA-345-AB','2023-05-25',42000,'Cheque',10),
        (392048374,'AB-456-BC','2023-05-10',28000,'Cartao de Credito',7),
        (321654987,'BC-567-CD','2023-04-10',65000,'Transferencia',1),
        (154321990,'CD-678-DE','2023-05-15',22000,'Dinheiro',7),
        (323456790,'DE-789-EF','2023-06-02',41000,'Cheque',4),
        (153149190,'EF-890-FG','2023-05-25',38000,'Transferencia',10),
        (194026837,'FG-901-GH','2023-04-10',55000,'Dinheiro',10),
        (387654323,'GH-012-HI','2023-05-15',27000,'Cartao de Credito',1),
        (141852964,'HI-123-IJ','2023-06-02',32000,'Transferencia',4),
        (294729102,'IJ-234-JK','2023-05-25',47000,'Cheque',7),
        (487654322,'JK-345-KL','2023-04-10',42000,'Dinheiro',1),
        (241852963,'KL-456-LM','2023-05-15',31000,'Cartao de Credito',1),
        (147258369,'LM-567-MN','2023-06-02',59000,'Transferencia',4),
        (258369148,'MN-678-NO','2023-05-25',48000,'Dinheiro',7),
        (221987654,'NO-789-OP','2023-04-10',42000,'Cheque',10),
        (293612532,'OP-890-PQ','2023-05-15',33000,'Transferencia',1),
        (369258151,'PQ-901-RS','2023-06-02',25000,'Dinheiro',4),
        (321654987,'RS-012-ST','2023-05-25',44000,'Cartao de Credito',7),
        (387654323,'ST-123-TU','2023-04-10',39000,'Transferencia',10),
        (492048375,'TU-234-UV','2023-05-15',54000,'Dinheiro',10),
        (154321987,'UV-345-VW','2023-06-02',46000,'Cheque',4);

-- aluguer
INSERT INTO aluguer
VALUES  (123456789,'AB-123-CD','2023-05-01',CAST(DATEADD(day, -5, GETDATE()) AS DATE),200),
        (392048374,'BC-234-DE','2023-05-02',CAST(DATEADD(day, -1, GETDATE()) AS DATE),150),
        (456789123, 'CD-345-EF', '2023-05-03', CAST(DATEADD(day, 4, GETDATE()) AS DATE), 250),
        (294729102,'DE-456-FG','2023-05-04',CAST(DATEADD(day, -6, GETDATE()) AS DATE),180),
        (321654987, 'EF-567-GH', '2023-05-05', CAST(DATEADD(day, 5, GETDATE()) AS DATE), 220),
        (194026837,'FG-678-HI','2023-05-06',CAST(DATEADD(day, -2, GETDATE()) AS DATE),190),
        (123456789, 'GH-789-IJ', '2023-05-07', CAST(DATEADD(day, 3, GETDATE()) AS DATE), 210),
        (123456789,'GH-789-IJ','2023-04-20',CAST(DATEADD(day, -4, GETDATE()) AS DATE),300),
        (392048374,'HI-890-JK','2023-05-08',CAST(DATEADD(day, -3, GETDATE()) AS DATE),170),
        (456789123, 'MN-345-OP', '2023-05-09', CAST(DATEADD(day, 1, GETDATE()) AS DATE), 230);



-- compra_clientes
INSERT INTO compra_clientes
VALUES (194026837,'XY-321-YZ','2023-03-12',2500);

-- reparacao
INSERT INTO reparacao
VALUES (321654987,'NO-456-PQ','2023-05-15','2023-05-18',0, 2),
       (123456789, 'AB-123-CD', '2023-05-16', NULL, 700, 5);

-- peca
INSERT INTO peca
VALUES  ('Filtro de ar', 'Filtro', NULL, 1),
        ('Anticongelante', 'Fluido', NULL, 2),
        ('Liquido dos travoes','Fluido','Travoes',3),
        ('Alternador', 'Sistema eletrico', NULL, 1);

-- venda_peca
INSERT INTO venda_peca
VALUES  ('Filtro de ar', 147258369, '2023-04-12', 55, 1),
        ('Alternador', 147258369, '2023-04-12', 60, 1),
        ('Liquido dos travoes',258369147,'2023-05-12',34,1);
        
-- fornecedor
INSERT INTO fornecedor
VALUES  (511223093,'Autodoc','Rua A, 123','111111111','autodoc@company.com'),
        (534567890,'Autopeças Online','Rua B, 456','222222222','autoonline@company.com'),
        (745678901,'Mega Auto Parts','Rua C, 789','333333333','megaparts@company.com'),
        (591740273,'Carros e acessórios Lda.','Rua D, 012','444444444','carrosaccess@company.com'),
        (567890123,'Componentes Auto','Rua E, 345','555555555','compauto@company.com'),
        (678901234,'Peças Express','Rua F, 678','666666666','expresspecas@company.com'),
        (789012345,'Fornecedor 7','Rua G, 901','777777777','fornecedor7@company.com'),
        (890123456,'Fornecedor 8','Rua H, 234','888888888','fornecedor8@company.com'),
        (901234567,'Fornecedor 9','Rua I, 567','999999999','fornecedor9@company.com'),
        (523456780,'Fornecedor 10','Rua J, 890','1010101010','fornecedor10@company.com'),
        (823456891,'Fornecedor 11','Rua K, 011','1111111111','fornecedor11@company.com');

-- encomenda_peca
INSERT INTO encomenda_peca
VALUES  ('Filtro de ar',678901234,'2023-01-17',1500,50),
        ('Anticongelante',678901234,'2023-01-18',3000,150),
        ('Alternador',567890123,'2023-01-10',400,10),
        ('Liquido dos travoes',511223093,'2023-01-09',450,30);

-- importador
INSERT INTO importador
VALUES  (591740273,'Carros e acessorios Lda.','Rua D, 012','444444444','carrosaccess@company.com'),
        (836482938,'Auto Importadora A','Rua dos Importadores, 123','1234567890','info@autoimportadoraA.com'),
        (281649364,'Importadora de Veiculos B','Avenida dos Importadores, 456','2345678901','contacto@importadorB.com'),
        (385637192,'Moto Import','Rua das Motos, 789','3591740273','vendas@motoimport.com'),
        (183548294,'Moto Importadora C','Avenida das Importacoes, 012','5917402733','contacto@motoimportadoraC.com');

-- compra_importador
INSERT INTO compra_importador
VALUES  (591740273,'AB-123-CD','2022-12-15',15000.00),
        (836482938,'BC-234-DE','2022-12-20',20000.00),
        (281649364,'CD-345-EF','2022-12-28',18000.00),
        (385637192,'DE-456-FG','2023-01-02',22000.00),
        (591740273,'EF-567-GH','2023-01-05',10000.00),
        (836482938,'FG-678-HI','2023-01-10',15000.00),
        (281649364,'GH-789-IJ','2023-01-15',12000.00),
        (385637192,'HI-890-JK','2023-01-18',18000.00),
        (591740273,'IJ-901-KL','2023-01-23',20000.00),
        (836482938,'JK-012-LM','2023-01-27',15000.00),
        (281649364,'KL-123-MN','2023-01-30',12000.00),
        (281649364,'LM-234-NO','2023-01-05',18000.00),
        (385637192,'MN-345-OP','2022-12-18',10000.00),
        (385637192,'NO-456-PQ','2023-01-22',15000.00),
        (385637192,'OP-567-QR','2023-01-29',12000.00);

INSERT INTO utilizador_bd
VALUES  ('admin', HASHBYTES('SHA2_512', 'admin')),
        ('user', HASHBYTES('SHA2_512', 'user'));