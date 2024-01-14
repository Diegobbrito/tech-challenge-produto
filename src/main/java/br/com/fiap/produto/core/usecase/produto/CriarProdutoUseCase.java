package br.com.fiap.produto.core.usecase.produto;


import br.com.fiap.produto.api.adapter.ProdutoAdapter;
import br.com.fiap.produto.api.dto.request.ProdutoRequest;
import br.com.fiap.produto.api.dto.response.ProdutoResponse;
import br.com.fiap.produto.config.UseCase;
import br.com.fiap.produto.core.entity.Categoria;
import br.com.fiap.produto.core.entity.Produto;
import br.com.fiap.produto.core.enumerator.CategoriaEnum;
import br.com.fiap.produto.gateway.repository.IProdutoRepository;

@UseCase
public class CriarProdutoUseCase implements ICriarProduto {

    private final IProdutoRepository repository;

    public CriarProdutoUseCase(IProdutoRepository repository) {
        this.repository = repository;
    }

    @Override
    public ProdutoResponse criar(ProdutoRequest request) {
        final var checkCategoria = CategoriaEnum.from(request.categoriaId());
        if(checkCategoria == null){
            throw new IllegalArgumentException("Categoria Invalida");
        }
        final var categoria = new Categoria(request.categoriaId());
        final var produto = new Produto(request.nome(), request.descricao(), request.valor(), categoria, request.imagemUrl());
        final var entity = this.repository.salvar(produto);
        return ProdutoAdapter.toResponse(entity);
    }
}
