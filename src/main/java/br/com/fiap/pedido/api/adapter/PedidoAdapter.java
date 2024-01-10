package br.com.fiap.pedido.api.adapter;

import br.com.fiap.pedido.api.dto.request.PedidoRequest;
import br.com.fiap.pedido.api.dto.response.PedidoResponse;
import br.com.fiap.pedido.api.dto.response.StatusResponse;
import br.com.fiap.pedido.core.entity.*;
import br.com.fiap.pedido.core.enumerator.StatusEnum;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

public class PedidoAdapter {
    public static PedidoResponse toResponse(Pedido pedido, String qrData) {
        final var status = new StatusResponse(pedido.getStatus().getTipo());
        final var response = new PedidoResponse(pedido.getId(), formatarParaReal(pedido.getValor()), status);
        response.setQrData(qrData);
        return response;
    }
    public static Pedido toPedido(PedidoRequest request, Cliente cliente, List<Produto> produtos, Status status) {
        return new Pedido(getProdutosSelecionados(request, produtos), cliente, status);
    }

    private static List<ProdutoSelecionado> getProdutosSelecionados(PedidoRequest request, List<Produto> produtos) {
        Map<Integer, Integer> produtoQuantidade = new HashMap<>();
        request.produtos().forEach(p ->
                produtoQuantidade.put(p.produtoId(), p.quantidade())
        );
        return produtos.stream().map(p -> new ProdutoSelecionado(p, produtoQuantidade.get(p.getId()))).collect(Collectors.toList());
    }

    private static String formatarParaReal(BigDecimal valor){
        return "R$" + new DecimalFormat("#,###,##0.00", new DecimalFormatSymbols(new Locale("pt", "BR"))).format(valor);
    }
}
