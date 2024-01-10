package br.com.fiap.pedido.api.adapter;

import br.com.fiap.pedido.api.dto.response.ClienteResponse;
import br.com.fiap.pedido.core.entity.Cliente;
import br.com.fiap.pedido.gateway.repository.cliente.ClienteEntity;

public class ClienteAdapter {

    public static ClienteResponse toResponse(Cliente cliente) {
        return new ClienteResponse(
                cliente.getCpf().getValor(),
                cliente.getNome(),
                cliente.getEmail().getValor());
    }

    public static Cliente toCliente(ClienteEntity clienteEntity){
        final var cliente = new Cliente(clienteEntity.getCpf(), clienteEntity.getNome(), clienteEntity.getEmail());
        cliente.setId(clienteEntity.getId());
        return cliente;
    }
}
