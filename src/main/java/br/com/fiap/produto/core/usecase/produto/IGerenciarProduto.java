package br.com.fiap.produto.core.usecase.produto;

import br.com.fiap.produto.api.dto.request.ProdutoRequest;
import br.com.fiap.produto.api.dto.response.ProdutoResponse;

public interface IGerenciarProduto {
    void excluirProduto(Integer id);

    ProdutoResponse atualizar(Integer id, ProdutoRequest request);
}
