package br.com.fiap.pedido.core.usecase.produto;

import br.com.fiap.pedido.api.adapter.ProdutoAdapter;
import br.com.fiap.pedido.api.dto.response.ProdutoResponse;
import br.com.fiap.pedido.config.UseCase;
import br.com.fiap.pedido.gateway.repository.IProdutoRepository;

import java.util.List;
import java.util.stream.Collectors;
@UseCase
public class BuscarProdutoUseCase implements IBuscarProduto {

    private final IProdutoRepository repository;

    public BuscarProdutoUseCase(IProdutoRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<ProdutoResponse> buscarTodos() {
        final var produtos = this.repository.buscarTodos();
        return produtos.stream().map(ProdutoAdapter::toResponse).collect(Collectors.toList());
    }

    @Override
    public List<ProdutoResponse> buscarPorCategoria(Integer id) {
        final var produtos = this.repository.buscarPorCategoria(id);
        return produtos.stream().map(ProdutoAdapter::toResponse).collect(Collectors.toList());
    }

}
