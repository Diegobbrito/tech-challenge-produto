CREATE DATABASE IF NOT EXISTS lanchonete;
USE lanchonete;

CREATE TABLE produtos (
    id INT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(200) NOT NULL,
    descricao VARCHAR(200) NOT NULL,
    valor DECIMAL(10,2) NOT NULL,
    imagem_url VARCHAR(200),
    categoria_id INT NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE categorias (
    id INT NOT NULL AUTO_INCREMENT,
    titulo VARCHAR(200) NOT NULL,
    descricao VARCHAR(200),
    PRIMARY KEY (id)
);

INSERT INTO categorias (titulo, descricao) VALUES ("Lanches", "Lanches são compostos por pão, hambúrguer e outros ingredientes como alface, tomate, queijo, bacon, entre outros");
INSERT INTO categorias (titulo, descricao) VALUES ("Bebidas", "Bebidas como água, refrigerantes, sucos e cervejas");
INSERT INTO categorias (titulo, descricao) VALUES ("Acompanhamentos", "Batatas fritas, batatas fritas com cheddar e bacon ou onion rings");
INSERT INTO categorias (titulo, descricao) VALUES ("Sobremesas", "Cookie e Brownie");
INSERT INTO categorias (titulo, descricao) VALUES ("Combos", "Combinação de lanche, bebida e acompanhamento");

#Lanches
INSERT INTO produtos (nome, descricao, valor, imagem_url, categoria_id) VALUES ("Hambúrguer", "Pão de brioche, hambúrguer Angus de 150g e queijo mussarela", 24.90, "https://storage.googleapis.com/grandchef-apps/gc11924/images/products/62e82a54605bc.jpg", 1);
INSERT INTO produtos (nome, descricao, valor, imagem_url, categoria_id) VALUES ("Hambúrguer MELT", "Pão australiano, hambúrguer Angus de 150g, queijo cheddar cremoso, cebola caramelizada, bacon super crocante e molho barbecue.", 29.90, "https://storage.googleapis.com/grandchef-apps/gc11924/images/products/62e83306d9650.jpg", 1);
INSERT INTO produtos (nome, descricao, valor, imagem_url, categoria_id) VALUES ("Hambúrguer GOUDA", "Pão brioche tostado na manteiga, maionese artesanal, hambúrguer Angus de 150g, queijo mussarela, almofadinhas de queijo Gouda e molho de pimenta agridoce.", 29.90, "https://storage.googleapis.com/grandchef-apps/gc11924/images/products/62e8341c19eb3.png", 1);
INSERT INTO produtos (nome, descricao, valor, imagem_url, categoria_id) VALUES ("Hambúrguer BACON", "Pão brioche tostado na manteiga, hambúrguer Angus de 150g, queijo cheddar, bacon, maionese artesanal e cebola caramelizada.", 29.90, "https://storage.googleapis.com/grandchef-apps/gc11924/images/products/62e833670fb01.png", 1);
#Bebidas
INSERT INTO produtos (nome, descricao, valor, imagem_url, categoria_id) VALUES ("Água", "Garrafa de água sem gás 500ml", 4.90, "https://storage.googleapis.com/grandchef-apps/gc11924/images/products/62e96fc6864d3.png", 2);
INSERT INTO produtos (nome, descricao, valor, imagem_url, categoria_id) VALUES ("Corona", "Cerveja Corona Extra", 9.90, "https://storage.googleapis.com/grandchef-apps/gc11924/images/products/638a76170d265.jpg", 2);
INSERT INTO produtos (nome, descricao, valor, imagem_url, categoria_id) VALUES ("Heineken", "Cerveja Heineken 330ml", 29.90, "https://storage.googleapis.com/grandchef-apps/gc11924/images/products/62e97027e6dc9.png", 2);
INSERT INTO produtos (nome, descricao, valor, imagem_url, categoria_id) VALUES ("Suco de Pêssego", "Suco Del Valle Néctar Pêssego 290ml", 7.90, "https://static.saipos.com/saipos-estatico/img-items/17862/store_item/6d611d7bf52515414f323596967f4fd0.jpeg?d=x250", 2);
INSERT INTO produtos (nome, descricao, valor, imagem_url, categoria_id) VALUES ("Coca Cola", "Refrigerante Coca Cola Lt 350ml", 7.90, "https://static.saipos.com/saipos-estatico/img-items/17862/store_item/8634e622569ba9abc2cfcb2390cc9d3a.jpeg?d=x250", 2);
#Acompanhamentos
INSERT INTO produtos (nome, descricao, valor, imagem_url, categoria_id) VALUES ("Fritas Individual", "Porção de fritas crocantes.(120g)", 9.90, "https://static.saipos.com/saipos-estatico/img-items/17862/store_item/201685d776c3ebe3fd7c2613726c46ab.jpeg?d=x250", 3);
INSERT INTO produtos (nome, descricao, valor, imagem_url, categoria_id) VALUES ("Fritas Grande", "Porção de fritas crocantes.(400g)", 19.90, "https://static.saipos.com/saipos-estatico/img-items/17862/store_item/2eb1b9362be7462146fd69b987b05738.jpeg?d=x250", 3);
INSERT INTO produtos (nome, descricao, valor, imagem_url, categoria_id) VALUES ("Fritas com cheddar e bacon", "Porção grande de fritas com molho de cheddar e bacon(400g) ", 24.90, "https://static.saipos.com/saipos-estatico/img-items/17862/store_item/cde1d2f0b7d67e7fb8794730d9811ca9.jpeg?d=x250", 3);
INSERT INTO produtos (nome, descricao, valor, imagem_url, categoria_id) VALUES ("Onion Rings", "Deliciosa porção individual de Onion Rings (anéis de cebola).(150g) ", 14.90, "https://static.saipos.com/saipos-estatico/img-items/17862/store_item/74437fbde55a7e180440d6bb35627c15.jpeg?d=x250", 3);
#Sobremesas
INSERT INTO produtos (nome, descricao, valor, imagem_url, categoria_id) VALUES ("Cookie", "Delicioso cookie com gotas de chocolate", 7.90, "https://static.saipos.com/saipos-estatico/img-items/17862/store_item/0305b366a7335d889b3e92c2f15ca2ed.jpeg?d=x250", 4);
INSERT INTO produtos (nome, descricao, valor, imagem_url, categoria_id) VALUES ("Brownie de Nutella e Ninho", "Delicioso brownie coberto com ganache de Nutella e leite Ninho em pó.", 9.90, "https://static.saipos.com/saipos-estatico/img-items/17862/store_item/e57fd6908610d97f0156b3676d67164e.jpeg?d=x250", 4);
#Combo
INSERT INTO produtos (nome, descricao, valor, imagem_url, categoria_id) VALUES ("Combo MELT + Coca + Fritas", "Pão australiano, hambúrguer Angus de 150g, queijo cheddar cremoso, cebola caramelizada, bacon super crocante e molho barbecue. + Coca Cola Lt 350ml + Porção de fritas crocantes.(120g)", 39.90, "https://static.ifood-static.com.br/image/upload/t_medium/pratos/3c25c30b-c376-4ccb-99a4-4df645726a84/202208241937_IEC0_i.jpg", 5);

GRANT ALL PRIVILEGES ON *.* TO 'pedido'@'%' WITH GRANT OPTION;
flush privileges;