INSERT INTO products (id, sku, description, price, active)
SELECT 1, 'ART-01', 'Aceite Girasol Cocinero x 1,5 lt', 463.7, true
    WHERE NOT EXISTS (SELECT sku FROM products where sku = 'ART-01');

INSERT INTO products (id, sku, description, price, active)
SELECT 2, 'ART-02', 'Acondicionador Aloe Water Fructis x 350 ml', 543.4, true
    WHERE NOT EXISTS (SELECT sku FROM products where sku = 'ART-02');

INSERT INTO products (id, sku, description, price, active)
SELECT 3, 'ART-03', 'Bizcochos Agridulces 9 De Oro x 215 gr', 193.6, true
    WHERE NOT EXISTS (SELECT sku FROM products where sku = 'ART-03');

INSERT INTO products (id, sku, description, price, active)
SELECT 4, 'ART-04', 'Bolsas de Residuo 60x100 Primer Precio x 10 un', 282.6, true
    WHERE NOT EXISTS (SELECT sku FROM products where sku = 'ART-04');

INSERT INTO products (id, sku, description, price, active)
SELECT 5, 'ART-05', 'Café Instantáneo La Morenita x 100 gr', 408.7, true
    WHERE NOT EXISTS (SELECT sku FROM products where sku = 'ART-05');

INSERT INTO products (id, sku, description, price, active)
SELECT 6, 'ART-06', 'Cepillo Dental Infantil 2Life x 1 un', 141.8, true
    WHERE NOT EXISTS (SELECT sku FROM products where sku = 'ART-06');

INSERT INTO products (id, sku, description, price, active)
SELECT 7, 'ART-07', 'Conserva de Atún en Lomitos en Aceite y Agua La Anónima x 160 gr', 391.3, true
    WHERE NOT EXISTS (SELECT sku FROM products where sku = 'ART-07');

INSERT INTO products (id, sku, description, price, active)
SELECT 8, 'ART-08', 'Croquetas De Espinaca Día x 400 gr', 565.7, true
    WHERE NOT EXISTS (SELECT sku FROM products where sku = 'ART-08');

INSERT INTO products (id, sku, description, price, active)
SELECT 9, 'ART-09', 'Dulce de Leche Tradicional Sancor x 250 gr', 312.7, true
    WHERE NOT EXISTS (SELECT sku FROM products where sku = 'ART-09');

INSERT INTO products (id, sku, description, price, active)
SELECT 10, 'ART-10', 'Escoba LP x 1 un', 622.4, true
    WHERE NOT EXISTS (SELECT sku FROM products where sku = 'ART-10');
