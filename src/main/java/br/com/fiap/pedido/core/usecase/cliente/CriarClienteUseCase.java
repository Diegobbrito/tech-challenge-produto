package br.com.fiap.pedido.core.usecase.cliente;
import br.com.fiap.pedido.api.adapter.ClienteAdapter;
import br.com.fiap.pedido.api.dto.request.ClienteRequest;
import br.com.fiap.pedido.api.dto.response.ClienteResponse;
import br.com.fiap.pedido.config.UseCase;
import br.com.fiap.pedido.core.entity.Cliente;
import br.com.fiap.pedido.gateway.repository.IClienteRepository;
@UseCase
public class CriarClienteUseCase implements ICriarCliente {

    private final IClienteRepository repository;

    public CriarClienteUseCase(IClienteRepository repository) {
        this.repository = repository;
    }

    @Override
    public ClienteResponse criar(ClienteRequest request) {
        final var cliente = new Cliente(request.cpf(), request.nome(), request.email());
        return ClienteAdapter.toResponse(repository.salvar(cliente));
    }
}
