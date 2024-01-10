package br.com.fiap.pedido.core.usecase.cliente;

import br.com.fiap.pedido.api.dto.response.ClienteResponse;

public interface IBuscarCliente {
    ClienteResponse buscarClientePorCpf(String cpf);
}
