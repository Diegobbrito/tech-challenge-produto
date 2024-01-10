package br.com.fiap.pedido.core.usecase.pedido;

import br.com.fiap.pedido.api.adapter.PedidoAdapter;
import br.com.fiap.pedido.api.dto.request.PedidoRequest;
import br.com.fiap.pedido.api.dto.request.ProdutoSelecionadoRequest;
import br.com.fiap.pedido.api.dto.response.PedidoResponse;
import br.com.fiap.pedido.config.UseCase;
import br.com.fiap.pedido.core.entity.Cliente;
import br.com.fiap.pedido.core.entity.Pedido;
import br.com.fiap.pedido.core.entity.Status;
import br.com.fiap.pedido.core.enumerator.StatusEnum;
import br.com.fiap.pedido.gateway.dataprovider.IPagamentoDataProvider;
import br.com.fiap.pedido.gateway.repository.IClienteRepository;
import br.com.fiap.pedido.gateway.dataprovider.IProducaoPedidoDataProvider;
import br.com.fiap.pedido.gateway.repository.IProdutoRepository;

import java.util.stream.Collectors;
@UseCase
public class CriarPedidoUseCase implements ICriarPedido {

    private final IProducaoPedidoDataProvider pedidoDataProvider;
    private final IClienteRepository clienteRepository;
    private final IProdutoRepository produtoRepository;
    private final IPagamentoDataProvider pagamentoDataProvider;

    public CriarPedidoUseCase(IProducaoPedidoDataProvider pedidoDataProvider, IClienteRepository clienteRepository, IProdutoRepository produtoRepository, IPagamentoDataProvider pagamentoDataProvider) {
        this.pedidoDataProvider = pedidoDataProvider;
        this.clienteRepository = clienteRepository;
        this.produtoRepository = produtoRepository;
        this.pagamentoDataProvider = pagamentoDataProvider;
    }

    @Override
    public PedidoResponse criar(PedidoRequest request) {
        Pedido pedido;

        final var ids = request.produtos().stream().map(ProdutoSelecionadoRequest::produtoId).collect(Collectors.toList());
        final var produtos = this.produtoRepository.buscarTodosPorIds(ids);

        final var status = new Status(StatusEnum.PAGAMENTOPENDENTE);
        Cliente cliente = null;
        if(request.cpf() != null && !request.cpf().isBlank()){
            final var cpfFormatado = request.cpf().trim().replaceAll("\\.", "").replaceAll("-", "");
            cliente = this.clienteRepository.buscarClientePorCpf(cpfFormatado);
        }
        pedido = PedidoAdapter.toPedido(request, cliente, produtos, status);

        final var entity = pedidoDataProvider.criarPedido(pedido);

        final var qrData = pagamentoDataProvider.criarPagamento(entity);

        return PedidoAdapter.toResponse(entity, qrData);
    }
}
