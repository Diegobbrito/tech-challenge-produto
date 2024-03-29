package br.com.fiap.produto.api.controller;

import br.com.fiap.produto.api.dto.request.ProdutoRequest;
import br.com.fiap.produto.api.dto.response.CategoriaResponse;
import br.com.fiap.produto.api.dto.response.ProdutoResponse;
import br.com.fiap.produto.core.usecase.categoria.IBuscarCategoria;

import br.com.fiap.produto.core.usecase.produto.IBuscarProduto;
import br.com.fiap.produto.core.usecase.produto.ICriarProduto;
import br.com.fiap.produto.core.usecase.produto.IGerenciarProduto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Tag(name = "Produtos", description = "Listagem e controle de produtos")
@RestController
public class ProdutoController {

    private final IBuscarProduto buscarProdutoUseCase;
    private final ICriarProduto criarProdutoUseCase;
    private final IGerenciarProduto gerenciarProdutoUseCase;
    private final IBuscarCategoria buscarCategoriaUseCase;
    public ProdutoController(IBuscarProduto buscarProdutoUseCase,
                             ICriarProduto criarProdutoUseCase,
                             IGerenciarProduto gerenciarProdutoUseCase,
                             IBuscarCategoria buscarCategoriaUseCase) {
        this.buscarProdutoUseCase = buscarProdutoUseCase;
        this.criarProdutoUseCase = criarProdutoUseCase;
        this.gerenciarProdutoUseCase = gerenciarProdutoUseCase;
        this.buscarCategoriaUseCase = buscarCategoriaUseCase;
    }
    @Operation(summary = "Lista todas as categorias com suas descrições")
    @GetMapping("/categorias")
    public ResponseEntity<List<CategoriaResponse>> listarCategorias(){
        return ResponseEntity.ok(buscarCategoriaUseCase.buscarTodas());
    }
    @Operation(summary = "Listagem de todos os produtos")
    @GetMapping("/produtos")
    public ResponseEntity<List<ProdutoResponse>> listarTodos(@RequestParam(required = false) List<Integer> ids){
        if(ids !=null)
            return ResponseEntity.ok(buscarProdutoUseCase.buscarPorIds(ids));
        return ResponseEntity.ok(buscarProdutoUseCase.buscarTodos());
    }

    @Operation(summary = "Listagem de produtos por categoria")
    @GetMapping("/produtos/{categoriaId}")
    public ResponseEntity<List<ProdutoResponse>> listarPorCategoria(@Parameter(example = "1") @PathVariable Integer categoriaId){
        return ResponseEntity.ok(buscarProdutoUseCase.buscarPorCategoria(categoriaId));
    }

    @Operation(summary = "Criação de produto")
    @PostMapping("/produtos")
    public ResponseEntity<ProdutoResponse> criar(@RequestBody ProdutoRequest request){
        final var response = criarProdutoUseCase.criar(request);
        final var uri = URI.create("/produtos/" + response.id());
        return ResponseEntity.created(uri).body(response);
    }

    @Operation(summary = "Alteração de produto")
    @PutMapping("/produtos/{id}")
    public ResponseEntity<ProdutoResponse> editar(@Parameter(example = "1") @PathVariable Integer id, @RequestBody ProdutoRequest request){
        return ResponseEntity.ok(gerenciarProdutoUseCase.atualizar(id, request));
    }
    @Operation(summary = "Deleção de produto")
    @DeleteMapping("/produtos/{id}")
    public ResponseEntity<Void> remover(@Parameter(example = "1") @PathVariable Integer id){
        gerenciarProdutoUseCase.excluirProduto(id);
        return ResponseEntity.noContent().build();
    }
}
