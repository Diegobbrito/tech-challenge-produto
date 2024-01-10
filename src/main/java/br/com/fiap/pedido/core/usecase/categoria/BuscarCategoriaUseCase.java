package br.com.fiap.pedido.core.usecase.categoria;

import br.com.fiap.pedido.api.adapter.ProdutoAdapter;
import br.com.fiap.pedido.api.dto.response.CategoriaResponse;
import br.com.fiap.pedido.config.UseCase;
import br.com.fiap.pedido.gateway.repository.ICategoriaRepository;

import java.util.List;
import java.util.stream.Collectors;
@UseCase
public class BuscarCategoriaUseCase implements IBuscarCategoria {

    private final ICategoriaRepository repository;

    public BuscarCategoriaUseCase(ICategoriaRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<CategoriaResponse> buscarTodas() {
        final var categoria = this.repository.buscarTodas();
        return categoria.stream().map(ProdutoAdapter::toResponse).collect(Collectors.toList());
    }
}
