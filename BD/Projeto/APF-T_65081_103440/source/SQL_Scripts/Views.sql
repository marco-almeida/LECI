GO --
    CREATE VIEW carsUnderRepair --
    AS
SELECT veiculo.matricula,
    veiculo.marca,
    veiculo.modelo,
    veiculo.combustivel,
    veiculo.potencia,
    veiculo.cilindrada,
    veiculo.ano,
    veiculo.quilometros,
    veiculo.condicao,
    veiculo.stand_id
FROM (
        SELECT veiculo
        FROM reparacao
        GROUP BY veiculo
        HAVING (
                MAX(data_inicio) IS NOT NULL
                AND MAX(data_fim) IS NULL
            ) -- under repair
            OR (MAX(data_fim) > GETDATE())
    ) AS A
    JOIN veiculo ON veiculo.matricula = A.veiculo;
-- SELECT * FROM carsUnderRepair;

GO --
    CREATE VIEW AllAvailableVehicles --
    AS
SELECT veiculo.matricula,
    veiculo.marca,
    veiculo.modelo,
    veiculo.combustivel,
    veiculo.potencia,
    veiculo.cilindrada,
    veiculo.ano,
    veiculo.quilometros,
    veiculo.condicao,
    veiculo.stand_id
FROM (
        SELECT veiculo.matricula
        FROM veiculo
            FULL OUTER JOIN dbo.aluguer ON veiculo.matricula = aluguer.veiculo
        WHERE veiculo.stand_id IS NOT NULL
        GROUP BY veiculo.matricula
        HAVING MAX(aluguer.data_fim) < GETDATE()
            OR MAX(aluguer.data_fim) IS NULL
    ) AS A
    JOIN veiculo ON veiculo.matricula = A.matricula
WHERE A.matricula NOT IN (
        SELECT matricula
        FROM carsUnderRepair
    ) -- SELECT * FROM AllAvailableVehicles;

GO --
    CREATE VIEW GetBestSellers --
    AS
SELECT vendedor.vendedor_id,
    SUM(venda.montante) AS total_montante
FROM vendedor
    JOIN venda ON vendedor.vendedor_id = venda.vendedor
GROUP BY vendedor.vendedor_id
HAVING SUM(venda.montante) > (
        SELECT AVG(montante)
        FROM venda
    );
-- SELECT * FROM GetBestSellers;

GO --
    CREATE VIEW GetEmployeeRoles --
    AS
SELECT f.*,
    CASE
        WHEN v.vendedor_id IS NOT NULL THEN 'Vendedor'
        WHEN m.mecanico_id IS NOT NULL THEN 'Mecanico'
        WHEN g.gerente_id IS NOT NULL THEN 'Gerente'
        ELSE 'Unknown Role'
    END AS role
FROM funcionario f
    LEFT JOIN vendedor v ON f.funcionario_id = v.vendedor_id
    LEFT JOIN mecanico m ON f.funcionario_id = m.mecanico_id
    LEFT JOIN gerente g ON f.funcionario_id = g.gerente_id;
-- SELECT * FROM GetEmployeeRoles;

GO --
    CREATE VIEW GetRevenueByDate --
    AS
SELECT venda.data_venda,
    SUM(venda.montante) AS total_montante
FROM venda
GROUP BY venda.data_venda -- SELECT * FROM GetRevenueByDate;

GO --
    CREATE VIEW GetRevenueByStand --
    AS
SELECT funcionario.stand_id,
    SUM(venda.montante) AS total_montante
FROM funcionario
    JOIN venda ON funcionario.funcionario_id = venda.vendedor
GROUP BY funcionario.stand_id -- SELECT * FROM GetRevenueByStand;

GO --
    CREATE VIEW GetSalesByStand --
    AS
SELECT funcionario.stand_id,
    COUNT(funcionario.stand_id) AS total_vendas
FROM funcionario
    JOIN venda ON funcionario.funcionario_id = venda.vendedor
GROUP BY funcionario.stand_id -- SELECT * FROM GetSalesByStand;

GO --
    CREATE VIEW GetSalesByDate --
    AS
SELECT venda.data_venda,
    COUNT(venda.data_venda) AS total_vendas
FROM venda
GROUP BY venda.data_venda -- SELECT * FROM GetSalesBySeller;

GO--
    CREATE VIEW RevenueByMonth --
    AS
SELECT DATENAME(MONTH, venda.data_venda) AS month_name,
	MONTH(venda.data_venda) AS month_number,
    SUM(venda.montante) AS total_montante
FROM venda
GROUP BY DATENAME(MONTH, venda.data_venda), MONTH(venda.data_venda); -- SELECT * FROM RevenueByMonth ORDER by month_number

GO --
    CREATE OR ALTER VIEW SalesByMonth --
    AS
SELECT DATENAME(MONTH, venda.data_venda) AS month_name,
    MONTH(venda.data_venda) AS month_number,
    COUNT(venda.montante) AS total_vendas
FROM venda
GROUP BY DATENAME(MONTH, venda.data_venda), MONTH(venda.data_venda); -- SELECT * FROM SalesByMonth ORDER BY month_number