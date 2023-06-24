CREATE PROCEDURE RegisterVehicle -- only register
@matricula VARCHAR(20),
@marca VARCHAR(30),
@modelo VARCHAR(255),
@combustivel VARCHAR(30),
@potencia INTEGER,
@cilindrada INTEGER,
@ano INTEGER,
@quilometros INTEGER,
@condicao VARCHAR(100),
@stand_id INTEGER --
AS --
BEGIN -- 
INSERT INTO veiculo (
    matricula,
    marca,
    modelo,
    combustivel,
    potencia,
    cilindrada,
    ano,
    quilometros,
    condicao,
    stand_id
  )
VALUES (
    @matricula,
    @marca,
    @modelo,
    @combustivel,
    @potencia,
    @cilindrada,
    @ano,
    @quilometros,
    @condicao,
    @stand_id
  );
END -- Create the stored procedure
GO --
  CREATE PROCEDURE RegisterCar -- only register
  @matricula VARCHAR(20),
  @portas INTEGER,
  @lugares INTEGER,
  @caixa VARCHAR(30) -- 
  AS --
  BEGIN --
INSERT INTO carro (matricula, portas, lugares, caixa)
VALUES (@matricula, @portas, @lugares, @caixa);
END -- Create the stored procedure
GO --
  CREATE PROCEDURE RegisterMoto -- only register
  @matricula VARCHAR(20) AS --
  BEGIN --
INSERT INTO mota (matricula)
VALUES (@matricula);
END -- Create the stored procedure
GO --
  CREATE PROCEDURE BuyClientVeiculo
  @matricula VARCHAR(20),
  @marca VARCHAR(30),
  @modelo VARCHAR(255),
  @combustivel VARCHAR(30),
  @potencia INTEGER,
  @cilindrada INTEGER,
  @ano INTEGER,
  @quilometros INTEGER,
  @condicao VARCHAR(100),
  @stand_id INTEGER,
  @portas INTEGER,
  @lugares INTEGER,
  @caixa VARCHAR(30),
  @client_nif INTEGER,
  @data_compra DATE,
  @montante MONEY,
  @client_phone VARCHAR(20),
  @tipo VARCHAR(10)
AS
BEGIN
  BEGIN TRANSACTION;

  BEGIN TRY
    UPDATE Cliente
    SET telefone = @client_phone
    WHERE nif = @client_nif;

    IF NOT EXISTS (
        SELECT *
        FROM cliente
        WHERE nif = @client_nif
    )
    BEGIN
      RAISERROR('Client does not exist', 16, 1);
      ROLLBACK;
      RETURN;
    END

    IF EXISTS (
      SELECT *
      FROM veiculo
      WHERE matricula = @matricula
    )
    BEGIN
      RAISERROR('Vehicle already exists', 16, 1);
      ROLLBACK;
      RETURN;
    END

    IF @tipo = 'Carro'
    BEGIN
      INSERT INTO veiculo (
          matricula,
          marca,
          modelo,
          combustivel,
          potencia,
          cilindrada,
          ano,
          quilometros,
          condicao,
          stand_id
        )
      VALUES (
          @matricula,
          @marca,
          @modelo,
          @combustivel,
          @potencia,
          @cilindrada,
          @ano,
          @quilometros,
          @condicao,
          @stand_id
        );

      INSERT INTO carro (matricula, portas, lugares, caixa)
      VALUES (@matricula, @portas, @lugares, @caixa);

      INSERT INTO compra_clientes
      VALUES (@client_nif, @matricula, @data_compra, @montante);
    END

    IF @tipo = 'Mota'
    BEGIN
      INSERT INTO veiculo (
          matricula,
          marca,
          modelo,
          combustivel,
          potencia,
          cilindrada,
          ano,
          quilometros,
          condicao,
          stand_id
        )
      VALUES (
          @matricula,
          @marca,
          @modelo,
          @combustivel,
          @potencia,
          @cilindrada,
          @ano,
          @quilometros,
          @condicao,
          @stand_id
        );

      INSERT INTO mota (matricula)
      VALUES (@matricula);

      INSERT INTO compra_clientes
      VALUES (@client_nif, @matricula, @data_compra, @montante);
    END

    COMMIT;
  END TRY
  BEGIN CATCH
    ROLLBACK;
    
  END CATCH
END

GO --
  CREATE PROCEDURE RepairClientVeiculo -- assumes car is registered in the db client is
  @matricula VARCHAR(20),
  @client_nif INTEGER,
  @data_inicio DATE,
  @data_fim DATE,
  @montante MONEY,
  @client_phone VARCHAR(20),
  @mecanico INTEGER AS --
  BEGIN --
UPDATE Cliente
SET telefone = @client_phone
WHERE nif = @client_nif IF NOT EXISTS (
    SELECT *
    FROM cliente
    WHERE nif = @client_nif
  ) BEGIN RAISERROR('Client does not exist', 16, 1);
RETURN;
END --
IF NOT EXISTS (
  SELECT *
  FROM veiculo
  WHERE matricula = @matricula
) BEGIN RAISERROR('Vehicle does not exists', 16, 1);
RETURN;
END
INSERT INTO reparacao (
    cliente,
    veiculo,
    data_inicio,
    data_fim,
    montante,
    mecanico
  )
VALUES (
    @client_nif,
    @matricula,
    @data_inicio,
    @data_fim,
    @montante,
    @mecanico
  );
END -- Create the stored procedure
-- Future Work for Repair: Let add reparaçao without data_fim and montante and update those values later
GO --
  CREATE PROCEDURE SellVehicle
  @cliente INTEGER,
  @matricula VARCHAR(20),
  @data_venda DATE,
  @montante MONEY,
  @opcoes_pagamento VARCHAR(255),
  @vendedor_id INTEGER
AS
BEGIN
  BEGIN TRANSACTION;

  BEGIN TRY
    UPDATE veiculo
    SET stand_id = NULL
    WHERE matricula = @matricula;

    INSERT INTO venda
    VALUES (@cliente, @matricula, @data_venda, @montante, @opcoes_pagamento, @vendedor_id);

    COMMIT; -- Commit the transaction if both update and insert are successful
  END TRY
  BEGIN CATCH
    ROLLBACK; -- Rollback the transaction if an error occurs
    THROW; -- Rethrow the error to the caller
  END CATCH;
END;

-- EXEC SellVehicle @cliente = 123456789,
-- @matricula = 'AB-123-CD',
-- @data_venda = '2023-05-20',
-- @montante = 5000.00,
-- @opcoes_pagamento = 'Dinheiro',
-- @vendedor_id = 1;
GO --
  CREATE PROCEDURE AddEmployee @nome VARCHAR(20),
  @email VARCHAR(30),
  @telefone VARCHAR(255),
  @endereco VARCHAR(30),
  @stand INTEGER,
  @role VARCHAR(30),
  @ID INT OUTPUT AS BEGIN
INSERT INTO funcionario (nome, email, telefone, endereco, stand_id)
VALUES (@nome, @email, @telefone, @endereco, @stand);
SET @ID = SCOPE_IDENTITY();
IF @role = 'Gerente' BEGIN
INSERT INTO Gerente (gerente_id)
VALUES (@ID);
END IF @role = 'Vendedor' BEGIN
INSERT INTO Vendedor (vendedor_id)
VALUES (@ID);
END IF @role = 'Mecanico' BEGIN
INSERT INTO Mecanico (mecanico_id)
VALUES (@ID);
END
END
GO --
  CREATE PROCEDURE AddDatabaseUser @username VARCHAR(32),
  @password VARCHAR(32) AS BEGIN IF EXISTS (
    SELECT *
    FROM utilizador_bd
    WHERE nome = @username
  ) BEGIN RAISERROR('Username already exists', 16, 1);
END
ELSE BEGIN
INSERT INTO utilizador_bd (nome, password_hash)
VALUES (@username, HASHBYTES ('SHA2_512', @password));
END
END --
-- Exec AddDatabaseUser @username = 'user1', @password = 'password1';
GO --
  CREATE PROCEDURE verifyDatabaseUserCredentials @p_username VARCHAR(32),
  @p_password VARCHAR(64),
  @p_is_valid BIT OUTPUT AS BEGIN
DECLARE @p_password_hash VARBINARY(64);
-- Get the password hash for the given username
SELECT @p_password_hash = password_hash
FROM utilizador_bd
WHERE nome = @p_username;
IF @p_password_hash IS NULL BEGIN
SET @p_is_valid = 0;
-- User does not exist
END
ELSE BEGIN -- Compare the provided password with the stored hash
IF (
  CONVERT(
    VARBINARY(64),
    HASHBYTES('SHA2_512', @p_password),
    2
  ) = @p_password_hash
)
SET @p_is_valid = 1;
-- Credentials are valid
ELSE
SET @p_is_valid = 0;
-- Credentials are invalid
END
END