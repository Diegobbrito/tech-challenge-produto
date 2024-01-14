package br.com.fiap.produto.core.usecase.produto;


import br.com.fiap.produto.api.adapter.ProdutoAdapter;
import br.com.fiap.produto.api.dto.request.ProdutoRequest;
import br.com.fiap.produto.api.dto.response.ProdutoResponse;
import br.com.fiap.produto.config.UseCase;
import br.com.fiap.produto.gateway.repository.IProdutoRepository;

@UseCase
public class GerenciarProdutoUseCase implements IGerenciarProduto {

    private final IProdutoRepository repository;

    public GerenciarProdutoUseCase(IProdutoRepository repository) {
        this.repository = repository;
    }

    @Override
    public ProdutoResponse atualizar(Integer id, ProdutoRequest request) {
        final var produto = this.repository.buscarPorId(id);
        if(produto.isEmpty()){
            throw new IllegalArgumentException("Produto não encontrado para atualização");
        }

        final var entity = repository.salvar(ProdutoAdapter.toProduto(produto.get()));

        return ProdutoAdapter.toResponse(entity);
    }

    @Override
    public void excluirProduto(Integer id) {
        final var produto = this.repository.buscarPorId(id);
        if(produto.isEmpty()){
            throw new IllegalArgumentException("Produto não encontrado para exclusão");
        }
        repository.excluir(id);
    }
}
