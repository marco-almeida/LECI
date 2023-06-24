-- quando um mecanico é despedido, eliminar da tabela funcionario
GO --
    CREATE TRIGGER del_mecanico ON mecanico
AFTER DELETE --
    AS --
    BEGIN --
DELETE FROM funcionario
WHERE funcionario.funcionario_id IN (
        SELECT mecanico_id
        FROM deleted
    )
END -- quando um gerente é despedido, eliminar da tabela funcionario
GO --
    CREATE TRIGGER del_gerente ON gerente
AFTER DELETE --
    AS --
    BEGIN --
DELETE FROM funcionario
WHERE funcionario.funcionario_id IN (
        SELECT gerente_id
        FROM deleted
    );
END
GO --
    -- quando um vendedor é despedido, eliminar da tabela funcionario
    CREATE TRIGGER del_vendedor ON vendedor
AFTER DELETE --
    AS --
    BEGIN --
DELETE FROM funcionario
WHERE funcionario.funcionario_id IN (
        SELECT vendedor_id
        FROM deleted
    )
END
GO --
    -- quando uma venda é registada, incrementar o numero de vendas da tabela Vendedor
    CREATE TRIGGER increment_vendas
ON venda
AFTER INSERT --
AS --
BEGIN --
    -- Update the sales number for the associated vendedor
    UPDATE vendedor
    SET num_vendas = num_vendas + 
        (
            SELECT COUNT(*) 
            FROM inserted 
            WHERE vendedor = vendedor_id
        )
    WHERE vendedor_id IN (
        SELECT vendedor 
        FROM inserted
    );
END;
GO --
    -- quando uma venda é registada, incrementar o numero de vendas da tabela Vendedor
    CREATE TRIGGER manager_per_stand
ON gerente
INSTEAD OF INSERT --
AS --
BEGIN --
    -- check what stand manager employee id is in
    DECLARE @manager_id INT;
    SET @manager_id = (
        SELECT gerente_id
        FROM inserted
    );
    DECLARE @stand_id INT;
    SET @stand_id = (
        SELECT stand_id
        FROM funcionario
        WHERE funcionario_id = @manager_id
    );
    -- if that stand already has a manager, raise an error
    IF EXISTS (
    SELECT * FROM funcionario
    JOIN standauto ON funcionario.stand_id = standauto.stand_id
    JOIN gerente ON gerente.gerente_id = funcionario.funcionario_id
    WHERE standauto.stand_id = @stand_id)
    BEGIN
    -- delete the manager from funcionario table
    DELETE FROM funcionario
    WHERE funcionario_id = @manager_id;
    -- raise an error
        RAISERROR('Stand já tem um gerente', 16, 1);
    END
    ELSE
    BEGIN
        -- insert the manager into the gerente table
        INSERT INTO gerente (gerente_id)
        VALUES (@manager_id);
    END
    
END;