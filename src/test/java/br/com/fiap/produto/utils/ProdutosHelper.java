package br.com.fiap.produto.utils;

import br.com.fiap.produto.api.dto.request.ProdutoRequest;
import br.com.fiap.produto.api.dto.response.ProdutoResponse;
import br.com.fiap.produto.core.entity.Categoria;
import br.com.fiap.produto.core.entity.Produto;
import br.com.fiap.produto.gateway.repository.produto.ProdutoEntity;

import java.math.BigDecimal;
import java.util.List;

public abstract class ProdutosHelper {

    public static List<ProdutoEntity> gerarListaDeProdutoEntity() {
        return List.of(new ProdutoEntity(gerarLanche()), new ProdutoEntity(gerarBebida()));
    }


    public static List<Produto> gerarListaDeProdutos() {
        return List.of(gerarLanche(), gerarBebida());
    }

    public static Produto gerarLanche(){
        Produto lanche = new  Produto(
                "Hamburguer",
                "Hamburguer da casa",
                new BigDecimal("14.90"),
                 new Categoria(1),
                "teste");
        lanche.setId(1);
        return lanche;
    }

    public static ProdutoEntity gerarProdutoEntity(){
        Produto lanche = new  Produto(
                "Hamburguer",
                "Hamburguer da casa",
                new BigDecimal("14.90"),
                new Categoria(1),
                "teste");
        lanche.setId(1);
        return new ProdutoEntity(lanche);
    }

    private static Produto gerarBebida(){
        Produto bebida = new Produto(
                "Suco de laranja",
                "Suco de laranja 500ml",
                new BigDecimal("9.90"),
                new Categoria(2),
                "teste");
        bebida.setId(2);
        return bebida;
    }

    public static ProdutoRequest gerarProdutoRequest() {
        return new ProdutoRequest(
                "Hamburguer",
                "Hamburguer da casa",
                new BigDecimal("14.90"),
                1,
                "teste"
                );
    }

    public static ProdutoResponse gerarProdutoResponse() {
        return new ProdutoResponse(
                1,
                "Hamburguer",
                "Hamburguer da casa",
                "R$ 14.90",
                "teste"
        );
    }

    public static ProdutoRequest gerarProdutoRequestAlterado() {
        return new ProdutoRequest(
                "Hamburguer",
                "Hamburguer tradicional da casa",
                new BigDecimal("19.90"),
                1,
                "teste"
        );
    }
}
