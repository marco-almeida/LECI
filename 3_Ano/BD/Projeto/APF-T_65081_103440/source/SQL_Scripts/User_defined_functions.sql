CREATE FUNCTION GetAvailableVehiclesInStand(@standauto_id INT) RETURNS TABLE --
AS --
RETURN (
    SELECT A.matricula,
        A.marca,
        A.modelo,
        A.combustivel,
        A.potencia,
        A.cilindrada,
        A.ano,
        A.quilometros,
        A.condicao
    FROM AllAvailableVehicles AS A
    WHERE A.stand_id = @standauto_id
);
-- SELECT * FROM GetAvailableVehiclesInStand(3);
GO --
CREATE FUNCTION GetCarsUnderRepairInStand(@standauto_id INT) RETURNS TABLE --
AS --
RETURN (
    SELECT A.matricula,
        A.marca,
        A.modelo,
        A.combustivel,
        A.potencia,
        A.cilindrada,
        A.ano,
        A.quilometros,
        A.condicao
    FROM carsUnderRepair AS A
    WHERE A.stand_id = @standauto_id
);
-- function that given a seller id, returns the sum of all the sales he made
GO --
    CREATE FUNCTION GetSellerSales(@seller_id INT) RETURNS TABLE --
    AS --
    RETURN (
        SELECT SUM(montante) AS total_vendas
        FROM venda
        WHERE vendedor = @seller_id
    );
-- SELECT * FROM GetSellerSales(1);
GO --
    CREATE FUNCTION GetBestSellersInStand(@id INT) RETURNS TABLE --
    AS --
    RETURN
SELECT vendedor.vendedor_id,
    SUM(venda.montante) AS total_montante
FROM vendedor
    JOIN venda ON vendedor.vendedor_id = venda.vendedor
    JOIN funcionario ON funcionario.funcionario_id = vendedor.vendedor_id
WHERE funcionario.stand_id = @id
GROUP BY vendedor.vendedor_id
HAVING SUM(venda.montante) > (
        SELECT AVG(montante)
        FROM venda
    );
-- SELECT * FROM GetBestSellersInStand(3);