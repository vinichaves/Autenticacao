CREATE TABLE usuarios (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(256),
    email VARCHAR(256),
    dataUltimaAlteracao DATE,
    endereco VARCHAR(256),
    login VARCHAR(256),
    senha VARCHAR(256)
);

INSERT INTO usuarios (nome, email, dataUltimaAlteracao, endereco, login, senha)
VALUES
('Vinicius', 'vini@email.com', '2025-01-09', 'Rua minha rua n108', 'viniChaves', 'Senha123');
