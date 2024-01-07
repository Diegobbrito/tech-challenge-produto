package br.com.fiap.lanchonete.gateway.repository;

import br.com.fiap.lanchonete.core.entity.Cliente;

public interface IClienteRepository {
    Cliente salvar(Cliente produto);

    Cliente buscarClientePorCpf(String cpf);
}
