package br.com.fiap.produto.core.usecase.produto;

import br.com.fiap.produto.api.dto.response.ProdutoResponse;
import java.util.List;

public interface IBuscarProduto {
    List<ProdutoResponse> buscarTodos();
    List<ProdutoResponse> buscarPorCategoria(Integer id);
    List<ProdutoResponse> buscarPorIds(List<Integer> ids);
}
