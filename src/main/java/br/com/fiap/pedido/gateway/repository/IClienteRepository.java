package br.com.fiap.pedido.gateway.repository;

import br.com.fiap.pedido.core.entity.Cliente;

public interface IClienteRepository {
    Cliente salvar(Cliente produto);

    Cliente buscarClientePorCpf(String cpf);
}
