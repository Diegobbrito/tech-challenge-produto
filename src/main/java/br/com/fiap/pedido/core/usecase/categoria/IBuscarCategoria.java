package br.com.fiap.pedido.core.usecase.categoria;

import br.com.fiap.pedido.api.dto.response.CategoriaResponse;

import java.util.List;

public interface IBuscarCategoria {
    List<CategoriaResponse> buscarTodas();

}
