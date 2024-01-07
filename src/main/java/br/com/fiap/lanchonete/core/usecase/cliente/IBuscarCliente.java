package br.com.fiap.lanchonete.core.usecase.cliente;

import br.com.fiap.lanchonete.api.dto.response.ClienteResponse;

public interface IBuscarCliente {
    ClienteResponse buscarClientePorCpf(String cpf);
}
