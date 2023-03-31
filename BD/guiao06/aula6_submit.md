# BD: Guião 6

## Problema 6.1

### *a)* Todos os tuplos da tabela autores (authors);

```
SELECT * FROM authors;
```

### *b)* O primeiro nome, o último nome e o telefone dos autores;

```
SELECT au_fname, au_lname, phone FROM authors;
```

### *c)* Consulta definida em b) mas ordenada pelo primeiro nome (ascendente) e depois o último nome (ascendente); 

```
SELECT au_fname, au_lname, phone FROM authors ORDER BY au_fname, au_lname DESC;
```

### *d)* Consulta definida em c) mas renomeando os atributos para (first_name, last_name, telephone); 

```
SELECT au_fname AS first_name, au_lname AS last_name, phone AS telephone FROM authors ORDER BY au_fname, au_lname DESC;
```

### *e)* Consulta definida em d) mas só os autores da Califórnia (CA) cujo último nome é diferente de ‘Ringer’; 

```
SELECT au_fname AS first_name, au_lname AS last_name, phone AS telephone FROM authors WHERE state='CA' ORDER BY au_fname, au_lname DESC;
```

### *f)* Todas as editoras (publishers) que tenham ‘Bo’ em qualquer parte do nome; 

```
SELECT * FROM publishers WHERE pub_name LIKE '%bo%';
```

### *g)* Nome das editoras que têm pelo menos uma publicação do tipo ‘Business’; 

```
SELECT DISTINCT(pub_name) FROM (publishers JOIN titles ON publishers.pub_id = titles.pub_id) WHERE type = 'business';
```

### *h)* Número total de vendas de cada editora; 

```
SELECT publishers.pub_name, SUM(titles.ytd_sales)
AS Vendas
FROM publishers
JOIN titles
ON publishers.pub_id = titles.pub_id
GROUP BY publishers.pub_name;
```

### *i)* Número total de vendas de cada editora agrupado por título; 

```
SELECT publishers.pub_name, titles.title, SUM(titles.ytd_sales)
AS Vendas
FROM publishers
JOIN titles
ON publishers.pub_id = titles.pub_id
GROUP BY pub_name, title
ORDER BY pub_name;
```

### *j)* Nome dos títulos vendidos pela loja ‘Bookbeat’; 

```
SELECT title FROM ((stores JOIN sales ON stores.stor_id=sales.stor_id) JOIN titles ON titles.title_id=sales.title_id) WHERE stor_name = 'Bookbeat';
```

### *k)* Nome de autores que tenham publicações de tipos diferentes; 

```
SELECT au_fname, au_lname FROM authors
JOIN titleauthor ON authors.au_id=titleauthor.au_id
JOIN titles ON titles.title_id=titleauthor.title_id
GROUP BY au_fname, au_lname HAVING COUNT(DISTINCT(type)) > 1;
```

### *l)* Para os títulos, obter o preço médio e o número total de vendas agrupado por tipo (type) e editora (pub_id);

```
SELECT type, publishers.pub_name, SUM(qty) AS Sales, AVG(price) AS Avg_Price FROM
publishers JOIN titles ON publishers.pub_id=titles.pub_id
JOIN sales ON sales.title_id=titles.title_id
GROUP BY type, publishers.pub_name;
```

### *m)* Obter o(s) tipo(s) de título(s) para o(s) qual(is) o máximo de dinheiro “à cabeça” (advance) é uma vez e meia superior à média do grupo (tipo);

```
SELECT type FROM titles
GROUP BY type
HAVING MAX(advance) >= AVG(advance) * 1.5;
```

### *n)* Obter, para cada título, nome dos autores e valor arrecadado por estes com a sua venda;

```
SELECT title, au_lname, au_fname, royaltyper * (ytd_sales*price) * royalty / 10000 AS earnings
FROM (
SELECT authors.au_id, title_id, royaltyper, au_lname, au_fname
FROM titleauthor
JOIN authors
ON authors.au_id=titleauthor.au_id) AS A
JOIN titles
ON titles.title_id=A.title_id
ORDER BY title;
```

### *o)* Obter uma lista que incluía o número de vendas de um título (ytd_sales), o seu nome, a faturação total, o valor da faturação relativa aos autores e o valor da faturação relativa à editora;

```
SELECT title, ytd_sales, ytd_sales*price AS total_earnings, ytd_sales*price * royalty / 100 AS author_earnings, ytd_sales*price - (ytd_sales*price * royalty / 100) AS publisher_earnings
FROM titles
ORDER BY title;
```

### *p)* Obter uma lista que incluía o número de vendas de um título (ytd_sales), o seu nome, o nome de cada autor, o valor da faturação de cada autor e o valor da faturação relativa à editora;

```
SELECT title, ytd_sales, (au_lname + ' ' + au_fname) AS author_name, ytd_sales*price * royalty * royaltyper / 10000 AS author_earnings, ytd_sales*price - (ytd_sales*price * royalty / 100) AS publisher_earnings
FROM titles
JOIN titleauthor
ON titleauthor.title_id=titles.title_id
JOIN authors
ON authors.au_id=titleauthor.au_id
ORDER BY title;

```

### *q)* Lista de lojas que venderam pelo menos um exemplar de todos os livros;

```
SELECT stor_id
FROM sales
GROUP BY stor_id
HAVING COUNT(DISTINCT(title_id)) >= (SELECT COUNT(title_id) FROM titles);
```

### *r)* Lista de lojas que venderam mais livros do que a média de todas as lojas;

```
SELECT stor_id, SUM(qty) FROM sales
GROUP BY stor_id
HAVING SUM(qty) > (SELECT AVG(sum_qty) AS avg_all_stores FROM (SELECT stor_id, SUM(qty) AS sum_qty FROM sales GROUP BY stor_id) AS A);
```

### *s)* Nome dos títulos que nunca foram vendidos na loja “Bookbeat”;

```
SELECT title FROM titles
WHERE title_id NOT IN
(SELECT title_id FROM sales
FULL OUTER JOIN stores
ON sales.stor_id=stores.stor_id
WHERE stor_name='Bookbeat');
```

### *t)* Para cada editora, a lista de todas as lojas que nunca venderam títulos dessa editora; 

```
(SELECT pub_name, stor_name
FROM stores, publishers )
EXCEPT
(SELECT pub_name, stor_name
FROM publishers JOIN (SELECT pub_id AS ppid, sales.stor_id, stor_name
FROM titles JOIN sales
ON titles.title_id=sales.title_id
JOIN stores
ON sales.stor_id=stores.stor_id) AS T
ON pub_id=ppid);
```

## Problema 6.2

### ​5.1

#### a) SQL DDL Script
 
[a) SQL DDL File](ex_6_2_1_ddl.sql "SQLFileQuestion")

#### b) Data Insertion Script

[b) SQL Data Insertion File](ex_6_2_1_data.sql "SQLFileQuestion")

#### c) Queries

##### *a)*

```
SELECT (Fname + ' ' + Minit + '.' + ' ' + Lname) AS full_name, ssn, Pname
FROM Projects.Employee
JOIN Projects.Works_On
ON Ssn=Essn
JOIN Projects.Project
ON Pno=Pnumber;
```

##### *b)* 

```
SELECT (Fname + ' ' + Minit + '.' + ' ' + Lname) AS full_name FROM Projects.Employee WHERE Super_ssn =(
SELECT Ssn
FROM Projects.Employee
WHERE Fname='Carlos' AND Minit='D' AND Lname='Gomes');
```

##### *c)* 

```
SELECT Pname, SUM(Hourss) AS total_hourss
FROM Projects.Employee
JOIN Projects.Works_ON
ON Ssn=Essn
JOIN Projects.Project
ON Pno=Pnumber
GROUP BY Pname;
```

##### *d)* 

```
SELECT (Fname + ' ' + Minit + '.' + ' ' + Lname) AS full_name
FROM Projects.Employee
JOIN Projects.Works_On
ON Ssn=Essn
JOIN Projects.Project
ON Pno=Pnumber
WHERE Dno=3 AND Pname='Aveiro Digital' AND Hourss > 20;
```

##### *e)* 

```
SELECT (Fname + ' ' + Minit + '.' + ' ' + Lname) AS full_name
FROM Projects.Employee
FULL OUTER JOIN Projects.Works_On
ON Ssn=Essn
WHERE Hourss IS NULL;
```

##### *f)* 

```
SELECT Dname, AVG(Salary)
FROM
(SELECT *
FROM Projects.Department
JOIN Projects.Employee
ON Dnumber=DNo
WHERE Sex='F') AS A
GROUP BY Dname;
```

##### *g)* 

```
SELECT Essn
FROM Projects.Dependents
JOIN Projects.Employee
ON Essn=Ssn
GROUP BY Essn
HAVING COUNT(Essn) > 2;
```

##### *h)* 

```
SELECT Mgr_ssn FROM 
(SELECT *
FROM Projects.Dependents
FULL OUTER JOIN Projects.Department
ON Essn=Mgr_ssn) AS E
WHERE E.Essn IS NULL;
```

##### *i)* 

```
SELECT DISTINCT (Fname + ' ' + Minit + '.' + ' ' + Lname) AS full_name, Addresss
FROM Projects.Employee
JOIN Projects.Works_On
ON Ssn=Essn
JOIN Projects.Project
ON Pno=Pnumber
JOIN Projects.Dept_Locations
ON Dno=Dnumber
WHERE Plocation='Aveiro' AND Dlocation!='Aveiro';
```

### 5.2

#### a) SQL DDL Script
 
[a) SQL DDL File](ex_6_2_2_ddl.sql "SQLFileQuestion")

#### b) Data Insertion Script

[b) SQL Data Insertion File](ex_6_2_2_data.sql "SQLFileQuestion")

#### c) Queries

##### *a)*

```
SELECT *
FROM Fornecedor
FULL OUTER JOIN Encomenda
ON Fornecedor=Nif
WHERE Numero IS NULL;
```

##### *b)* 

```
SELECT Nome, AVG(item.Unidades) AS avg_units_order FROM item
JOIN Produto
ON Codigo=CodProd
GROUP BY Nome;
```


##### *c)* 

```
SELECT AVG(Aa) AS avg_number_diff_items_per_order
FROM
(SELECT CAST(COUNT(*) AS FLOAT) AS Aa FROM item
GROUP BY NumEnc) AS a;
```


##### *d)* 

```
SELECT Fornecedor.Nome, Produto.Nome, SUM(Item.Unidades) FROM Encomenda
JOIN Fornecedor
ON Fornecedor=Nif
JOIN Item
ON NumEnc=Numero
JOIN Produto
ON Codigo=CodProd
GROUP BY Fornecedor.Nome, Produto.Nome;
```

### 5.3

#### a) SQL DDL Script
 
[a) SQL DDL File](ex_6_2_3_ddl.sql "SQLFileQuestion")

#### b) Data Insertion Script

[b) SQL Data Insertion File](ex_6_2_3_data.sql "SQLFileQuestion")

#### c) Queries

##### *a)*

```
SELECT *
FROM Paciente
WHERE NSNS
NOT IN (SELECT DISTINCT NSNS FROM Prescricao);
```

##### *b)* 

```
SELECT Especialidade, COUNT(*) AS prescricoes FROM medico
JOIN Prescricao
ON medico.nid=Prescricao.nid
GROUP BY Especialidade;
```


##### *c)* 

```
SELECT COUNT(*) AS processed_no FROM Prescricao
WHERE Data_proc IS NOT NULL;
```


##### *d)* 

```
SELECT * FROM Presc_farmaco
FULL OUTER JOIN Farmaco
ON Nome=Nome_farmaco
WHERE Nreg=906
AND Num_presc IS NULL;
```

##### *e)* 

```
SELECT Nome_farmacia, Num_reg_farm, COUNT(*) AS sold_farma FROM Prescricao
JOIN Presc_farmaco
ON Num_presc=Nuni
WHERE Nome_farmacia IS NOT NULL
GROUP BY Nome_farmacia, Num_reg_farm
;
```

##### *f)* 

```
SELECT NSNS FROM prescricao
GROUP BY NSNS
HAVING COUNT(DISTINCT NID) > 1;
```
