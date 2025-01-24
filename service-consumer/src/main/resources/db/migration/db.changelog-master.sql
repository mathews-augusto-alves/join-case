-- Liquibase changelog master file


-- ChangeSet id="1" autor="Mathews"
CREATE TABLE IF NOT EXISTS categorias (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL UNIQUE,
    descricao TEXT,
    criado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    atualizado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- ChangeSet id="2" autor="Mathews"
CREATE TABLE IF NOT EXISTS produtos (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    descricao TEXT,
    preco DECIMAL(10, 2) NOT NULL,
    categoria_id INT NOT NULL,
    criado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    atualizado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_categoria FOREIGN KEY (categoria_id) REFERENCES categorias(id)
);

-- ChangeSet id="3" autor="Mathews"
CREATE TABLE IF NOT EXISTS usuarios (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    role VARCHAR(30) NOT NULL,
    criado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    atualizado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- ChangeSet id="4" autor="Mathews"
CREATE TABLE IF NOT EXISTS status_pedido (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL UNIQUE,
    descricao VARCHAR(255) NOT NULL UNIQUE
);

-- ChangeSet id="5" autor="Mathews"
CREATE TABLE IF NOT EXISTS pedidos (
    id SERIAL PRIMARY KEY,
    usuario_id INT NOT NULL,
    total DECIMAL(10, 2) NOT NULL,
    status_id INT NOT NULL,
    criado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    atualizado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_usuario_pedido FOREIGN KEY (usuario_id) REFERENCES usuarios(id),
    CONSTRAINT fk_status_pedido FOREIGN KEY (status_id) REFERENCES status_pedido(id)
);

-- ChangeSet id="6" autor="Mathews"
CREATE TABLE IF NOT EXISTS pedido_produto (
    id SERIAL PRIMARY KEY,
    pedido_id INT NOT NULL,
    produto_id INT NOT NULL,
    quantidade INT NOT NULL,
    preco_unitario DECIMAL(10, 2) NOT NULL,
    criado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    atualizado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_pedido FOREIGN KEY (pedido_id) REFERENCES pedidos(id),
    CONSTRAINT fk_produto FOREIGN KEY (produto_id) REFERENCES produtos(id)
);

-- ChangeSet id="7" autor="Mathews"
INSERT INTO categorias (nome, descricao) VALUES
('Eletrônicos', 'Dispositivos eletrônicos e acessórios.'),
('Móveis', 'Mobília e itens de decoração.'),
('Roupas', 'Vestuário masculino, feminino e infantil.'),
('Alimentos', 'Produtos alimentícios e bebidas.'),
('Esportes', 'Equipamentos esportivos e acessórios.'),
('Livros', 'Livros e revistas de diversos gêneros.'),
('Brinquedos', 'Brinquedos e jogos infantis.'),
('Ferramentas', 'Ferramentas e equipamentos de construção.')
ON CONFLICT (nome) DO NOTHING;

-- ChangeSet id="8" autor="Mathews"
INSERT INTO produtos (nome, descricao, preco, categoria_id) VALUES
('Smartphone Pro Max', 'Smartphone de última geração com recursos avançados.', 3499.99, 1),
('Tablet Ultra HD', 'Tablet com tela de alta definição e grande autonomia.', 1599.90, 1),
('Monitor Curvo 27"', 'Monitor curvo Full HD com alta taxa de atualização.', 1299.90, 1),
('Headset Gamer RGB', 'Headset gamer com som surround e microfone.', 499.99, 1),
('Teclado Mecânico', 'Teclado mecânico com iluminação LED personalizável.', 349.90, 1),
('Mouse Sem Fio', 'Mouse óptico sem fio, ergonômico.', 129.90, 1),
('Notebook Gamer', 'Notebook potente para jogos e trabalho.', 7499.99, 1),
('Sofá Retrátil', 'Sofá retrátil e reclinável com tecido de alta qualidade.', 1999.90, 2),
('Cadeira de Escritório', 'Cadeira ergonômica com suporte lombar.', 799.90, 2),
('Mesa Gamer', 'Mesa projetada para setups gamers.', 1199.90, 2),
('Cama Box Queen', 'Cama box com colchão ortopédico.', 2499.90, 2),
('Estante Modular', 'Estante com módulos ajustáveis.', 499.90, 2),
('Rack para TV', 'Rack moderno para TVs de até 60 polegadas.', 699.90, 2),
('Camiseta Polo', 'Camiseta polo de algodão premium.', 79.90, 3),
('Jaqueta de Couro', 'Jaqueta de couro legítimo.', 499.90, 3),
('Vestido Floral', 'Vestido feminino com estampa floral.', 149.90, 3),
('Tênis Casual', 'Tênis casual confortável e estiloso.', 199.90, 3),
('Moletom Masculino', 'Moletom com capuz, confortável.', 119.90, 3),
('Blusa de Tricô', 'Blusa de tricô feminina.', 149.90, 3),
('Calça Jeans Skinny', 'Calça jeans estilo skinny.', 129.90, 3),
('Arroz Integral 5kg', 'Pacote de 5kg de arroz integral.', 29.90, 4),
('Feijão Preto 1kg', 'Feijão preto de alta qualidade.', 7.90, 4),
('Molho de Tomate', 'Molho de tomate caseiro 340g.', 5.90, 4),
('Suco Natural 1L', 'Suco natural sem conservantes.', 8.90, 4),
('Café Moído 500g', 'Café moído de torra média.', 15.90, 4),
('Biscoito Recheado', 'Pacote de biscoito recheado 140g.', 3.90, 4),
('Bebida Isotônica 500ml', 'Bebida isotônica para reposição.', 6.90, 4),
('Bicicleta Aro 29', 'Bicicleta de alta performance para trilhas.', 2999.90, 5),
('Halteres Ajustáveis', 'Halteres com peso ajustável de até 20kg.', 399.90, 5),
('Colchonete para Yoga', 'Colchonete antideslizante para práticas de yoga.', 99.90, 5),
('Patins Inline', 'Patins inline com rodas de alta resistência.', 499.90, 5),
('Tênis para Corrida', 'Tênis específico para corrida.', 299.90, 5),
('Mochila de Hidratação', 'Mochila para atletas com reservatório de água.', 159.90, 5),
('Livro de Fantasia Épico', 'Um livro envolvente com histórias épicas.', 59.90, 6),
('Enciclopédia de Ciência', 'Enciclopédia com artigos científicos.', 149.90, 6),
('Manual de Sobrevivência', 'Manual completo com dicas práticas.', 89.90, 6),
('Romance Bestseller', 'Romance de sucesso mundial.', 49.90, 6),
('História em Quadrinhos', 'Revista de HQ para fãs de ação.', 39.90, 6),
('Quebra-Cabeça 500 Peças', 'Quebra-cabeça divertido de 500 peças.', 39.90, 7),
('Boneca Interativa', 'Boneca com sons e movimentos.', 189.90, 7),
('Jogo de Tabuleiro Estratégico', 'Jogo com desafios estratégicos.', 129.90, 7),
('Carro de Controle Remoto', 'Carrinho de controle remoto recarregável.', 249.90, 7),
('Urso de Pelúcia', 'Urso de pelúcia macio e fofinho.', 59.90, 7),
('Parafusadeira Sem Fio', 'Parafusadeira potente com bateria de longa duração.', 299.90, 8),
('Marreta de Demolição', 'Marreta resistente para uso pesado.', 89.90, 8),
('Alicate Universal', 'Alicate multiuso de aço inoxidável.', 39.90, 8),
('Chave Inglesa', 'Chave inglesa ajustável.', 49.90, 8),
('Kit de Brocas', 'Conjunto de brocas para perfurações diversas.', 79.90, 8);

-- ChangeSet id="9" autor="Mathews"
INSERT INTO public.status_pedido(
	nome, descricao)
	VALUES ('EM_PROCESSAMENTO', 'Em processamento')
ON CONFLICT (nome) DO NOTHING;

-- ChangeSet id="10" autor="Mathews"
INSERT INTO public.status_pedido(
	nome, descricao)
	VALUES ('APROVADO', 'Aprovado')
ON CONFLICT (nome) DO NOTHING;

-- ChangeSet id="11" autor="Mathews"
INSERT INTO public.status_pedido(
	nome, descricao)
	VALUES ('CANCELADO', 'Cancelado')
ON CONFLICT (nome) DO NOTHING;
