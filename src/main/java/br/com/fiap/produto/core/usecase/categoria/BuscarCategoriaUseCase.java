package br.com.fiap.produto.core.usecase.categoria;

import br.com.fiap.produto.api.adapter.ProdutoAdapter;
import br.com.fiap.produto.api.dto.response.CategoriaResponse;
import br.com.fiap.produto.config.UseCase;
import br.com.fiap.produto.gateway.repository.ICategoriaRepository;

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
