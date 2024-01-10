package br.com.fiap.pedido.gateway.dataprovider;

import br.com.fiap.pedido.core.entity.Pedido;


public interface IProducaoPedidoDataProvider {
    Pedido criarPedido(Pedido pedido);
}
