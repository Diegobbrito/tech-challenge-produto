package br.com.fiap.produto.core.usecase.categoria;

import br.com.fiap.produto.api.dto.response.CategoriaResponse;

import java.util.List;

public interface IBuscarCategoria {
    List<CategoriaResponse> buscarTodas();

}
