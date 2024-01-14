package br.com.fiap.produto.core.usecase.produto;

import br.com.fiap.produto.api.dto.request.ProdutoRequest;
import br.com.fiap.produto.api.dto.response.ProdutoResponse;

public interface ICriarProduto {
    ProdutoResponse criar(ProdutoRequest produto);
}
