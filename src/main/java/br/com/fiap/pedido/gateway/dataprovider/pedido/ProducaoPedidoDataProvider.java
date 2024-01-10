package br.com.fiap.pedido.gateway.dataprovider.pedido;

import br.com.fiap.pedido.api.adapter.PedidoAdapter;
import br.com.fiap.pedido.core.entity.Pedido;
import br.com.fiap.pedido.gateway.dataprovider.IProducaoPedidoDataProvider;
import org.springframework.stereotype.Component;

@Component
public class ProducaoPedidoDataProvider implements IProducaoPedidoDataProvider {


    private final IProducaoPedidoDataProvider dataProvider;

    public ProducaoPedidoDataProvider(IProducaoPedidoDataProvider dataProvider) {
        this.dataProvider = dataProvider;
    }

    @Override
    public Pedido criarPedido(Pedido pedido) {
        //TODO: Refatorar chamando serviço de produção para criar pedido
        return dataProvider.criarPedido(pedido);
    }
}
