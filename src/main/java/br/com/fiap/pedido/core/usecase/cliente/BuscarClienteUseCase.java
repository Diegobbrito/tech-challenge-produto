package br.com.fiap.pedido.core.usecase.cliente;

import br.com.fiap.pedido.api.adapter.ClienteAdapter;
import br.com.fiap.pedido.api.dto.response.ClienteResponse;
import br.com.fiap.pedido.config.UseCase;
import br.com.fiap.pedido.gateway.repository.IClienteRepository;

@UseCase
public class BuscarClienteUseCase implements IBuscarCliente {

    private final IClienteRepository repository;

    public BuscarClienteUseCase(IClienteRepository repository) {
        this.repository = repository;
    }


    @Override
    public ClienteResponse buscarClientePorCpf(String cpf) {
        final var cpfFormatado = cpf.trim().replaceAll("\\.", "").replaceAll("-", "");
        final var cliente = this.repository.buscarClientePorCpf(cpfFormatado);

        return ClienteAdapter.toResponse(cliente);
    }
}
