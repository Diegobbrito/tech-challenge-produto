package br.com.fiap.pedido.api.controller;

import br.com.fiap.pedido.api.dto.request.ClienteRequest;
import br.com.fiap.pedido.api.dto.response.ClienteResponse;
import br.com.fiap.pedido.core.usecase.cliente.IBuscarCliente;
import br.com.fiap.pedido.core.usecase.cliente.ICriarCliente;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@Tag(name = "Clientes", description = "Controle de clientes")
@RestController
@RequestMapping("/clientes")
public class ClienteController {
    private final ICriarCliente criarClienteUseCase;
    public ClienteController( ICriarCliente criarClienteUseCase) {
        this.criarClienteUseCase = criarClienteUseCase;
    }
    @Operation(summary = "Criação de cliente")
    @PostMapping
    public ResponseEntity<ClienteResponse> cadastrar(@RequestBody ClienteRequest request){
        final var response = criarClienteUseCase.criar(request);
        final var uri = URI.create("/clientes/" + response.cpf());
        return ResponseEntity.created(uri).body(response);
    }
}
