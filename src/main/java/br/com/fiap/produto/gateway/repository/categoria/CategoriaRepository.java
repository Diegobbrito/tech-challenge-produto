package br.com.fiap.produto.gateway.repository.categoria;

import br.com.fiap.produto.api.adapter.ProdutoAdapter;
import br.com.fiap.produto.core.entity.Categoria;
import br.com.fiap.produto.gateway.repository.ICategoriaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoriaRepository implements ICategoriaRepository {

    private final JpaCategoriaRepository repository;

    public CategoriaRepository(JpaCategoriaRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Categoria> buscarTodas() {
        final var categorias = repository.findAll();
        return categorias.stream().map(ProdutoAdapter::toCategoria).collect(Collectors.toList());
    }
}
