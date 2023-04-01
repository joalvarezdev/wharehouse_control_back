CREATE TABLE IF NOT EXISTS products (
    id SERIAL,
    sku VARCHAR(30) NOT NULL,
    description VARCHAR(100) NOT NULL,
    price DECIMAL(9,4) NOT NULL,
    CONSTRAINT "pk_products" PRIMARY KEY(id)
);