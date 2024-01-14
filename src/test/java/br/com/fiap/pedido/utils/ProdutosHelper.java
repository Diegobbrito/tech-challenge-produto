package br.com.fiap.pedido.utils;

import br.com.fiap.pedido.core.entity.Categoria;
import br.com.fiap.pedido.core.entity.Produto;
import br.com.fiap.pedido.gateway.repository.produto.ProdutoEntity;

import java.math.BigDecimal;
import java.util.List;

public abstract class ProdutosHelper {

    public static List<ProdutoEntity> gerarListaDeProdutoEntity() {
        return List.of(new ProdutoEntity(gerarLanche()), new ProdutoEntity(gerarBebida()));
    }


    public static List<Produto> gerarProdutos() {
        return List.of(gerarLanche(), gerarBebida());
    }

    private static Produto gerarLanche(){
        Produto lanche = new  Produto(
                "Hamburguer",
                "Hamburguer da casa",
                new BigDecimal("14.90"),
                 new Categoria(1),
                "teste");
        lanche.setId(1);
        return lanche;
    }

    private static Produto gerarBebida(){
        Produto bebida = new Produto(
                "Suco de laranja",
                "Suco de laranja 500ml",
                new BigDecimal("9.90"),
                new Categoria(2),
                "teste");
        bebida.setId(2);
    }

}
