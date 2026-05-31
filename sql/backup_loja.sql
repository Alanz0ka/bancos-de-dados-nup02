-- ============================================================
-- Backup do banco de dados - NUP 02 (Banco de Dados I)
-- Aplicacao: Cadastro de Produtos (Java + MySQL/JDBC)
-- SGBD.....: MySQL 8.x
--
-- Como restaurar (terminal):
--   mysql -u root -p < backup_loja.sql
-- Ou abra este arquivo no MySQL Workbench / DBeaver e execute.
-- ============================================================

-- Cria o banco caso nao exista e o seleciona para uso
CREATE DATABASE IF NOT EXISTS loja
    DEFAULT CHARACTER SET utf8mb4
    DEFAULT COLLATE utf8mb4_general_ci;

USE loja;

-- Recria a tabela do zero (para reimportar sem conflito)
DROP TABLE IF EXISTS produto;

-- Estrutura da tabela "produto"
CREATE TABLE produto (
    id         INT AUTO_INCREMENT PRIMARY KEY,
    nome       VARCHAR(100)  NOT NULL,
    preco      DECIMAL(10,2) NOT NULL DEFAULT 0.00,
    quantidade INT           NOT NULL DEFAULT 0,
    categoria  VARCHAR(50)
);

-- Dados de exemplo
INSERT INTO produto (nome, preco, quantidade, categoria) VALUES
    ('Teclado Mecanico',     249.90, 15, 'Perifericos'),
    ('Mouse Gamer',          129.90, 30, 'Perifericos'),
    ('Monitor 24 polegadas', 899.00,  8, 'Monitores'),
    ('SSD 1TB',              449.50, 20, 'Armazenamento'),
    ('Headset 7.1',          199.99, 12, 'Audio');
