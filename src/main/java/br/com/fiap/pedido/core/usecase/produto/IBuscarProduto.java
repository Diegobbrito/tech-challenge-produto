package br.com.fiap.pedido.core.usecase.produto;

import br.com.fiap.pedido.api.dto.response.ProdutoResponse;

import java.util.List;

public interface IBuscarProduto {
    List<ProdutoResponse> buscarTodos();
    List<ProdutoResponse> buscarPorCategoria(Integer id);
}
