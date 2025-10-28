CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE cars (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    marca VARCHAR(100) NOT NULL,
    modelo VARCHAR(100) NOT NULL,
    ano INT NOT NULL,
    cor VARCHAR(50) NOT NULL,
    preco_diaria DECIMAL(10,2) NOT NULL,
    disponivel BOOLEAN NOT NULL,
    img_url VARCHAR(300) NOT NULL,
    descricao VARCHAR(250) NOT NULL,
    data_cadastro TIMESTAMP NOT NULL
);