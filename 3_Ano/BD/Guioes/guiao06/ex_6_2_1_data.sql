INSERT INTO Employee VALUES ('Paula','A','Sousa',183623612,'2001-08-11','Rua da FRENTE','F',1450.00,NULL,3),
							('Carlos','D','Gomes',21312332 ,'2000-01-01','Rua XPTO','M',1200.00,NULL,1),
							('Juliana','A','Amaral',321233765,'1980-08-11','Rua BZZZZ','F',1350.00,NULL,3),
							('Maria','I','Pereira',342343434,'2001-05-01','Rua JANOTA','F',1250.00,21312332,2),
							('Joao','G','Costa',41124234 ,'2001-01-01','Rua YGZ','M',1300.00,21312332,2),
							('Ana','L','Silva',12652121 ,'1990-03-03','Rua ZIG ZAG','F',1400.00,21312332,2);

INSERT INTO Department VALUES	('Investigacao',1,21312332 ,'2010-08-02'),
								('Comercial',2,321233765,'2013-05-16'),
								('Logistica',3,41124234 ,'2013-05-16'),
								('Recursos Humanos',4,12652121,'2014-04-02'),
								('Desporto',5,NULL,NULL);

INSERT INTO Dependents VALUES	(21312332 ,'Joana Costa','F','2008-04-01', 'Filho'),
								(21312332 ,'Maria Costa','F','1990-10-05', 'Neto'),
								(21312332 ,'Rui Costa','M','2000-08-04','Neto'),
								(321233765,'Filho Lindo','M','2001-02-22','Filho'),
								(342343434,'Rosa Lima','F','2006-03-11','Filho'),
								(41124234 ,'Ana Sousa','F','2007-04-13','Neto'),
								(41124234 ,'Gaspar Pinto','M','2006-02-08','Sobrinho');


INSERT INTO Dept_Locations VALUES	(2,'Aveiro'),
									(3,'Coimbra');



INSERT INTO Project VALUES  ('Aveiro Digital',1,'Aveiro',3),
							('BD Open Day',2,'Espinho',2),
							('Dicoogle',3,'Aveiro',3),
							('GOPACS',4,'Aveiro',3);



INSERT INTO Works_On VALUES (183623612,1,20.0),
							(183623612,3,10.0),
							(21312332 ,1,20.0),
							(321233765,1,25.0),
							(342343434,1,20.0),
							(342343434,4,25.0),
							(41124234 ,2,20.0),
							(41124234 ,3,30.0);