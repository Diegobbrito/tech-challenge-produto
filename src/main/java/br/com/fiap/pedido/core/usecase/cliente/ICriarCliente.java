package br.com.fiap.pedido.core.usecase.cliente;

import br.com.fiap.pedido.api.dto.request.ClienteRequest;
import br.com.fiap.pedido.api.dto.response.ClienteResponse;

public interface ICriarCliente {
    ClienteResponse criar(ClienteRequest produto);
}
