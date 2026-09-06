SELECT s.price,s.year,p.product_name
FROM Sales  S
JOIN Product P
ON s.product_id = p.product_id