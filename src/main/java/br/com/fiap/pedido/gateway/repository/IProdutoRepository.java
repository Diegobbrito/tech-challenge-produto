package br.com.fiap.pedido.gateway.repository;

import br.com.fiap.pedido.core.entity.Produto;
import java.util.List;

public interface IProdutoRepository {
    List<Produto> buscarTodos();

    List<Produto> buscarPorCategoria(Integer id);

    List<Produto> buscarTodosPorIds(List<Integer> produtoIds);
}
