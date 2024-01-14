package br.com.fiap.pedido.api.controller;

import br.com.fiap.pedido.api.dto.response.CategoriaResponse;
import br.com.fiap.pedido.api.dto.response.ProdutoResponse;
import br.com.fiap.pedido.api.handler.RestExceptionHandler;
import br.com.fiap.pedido.core.usecase.categoria.IBuscarCategoria;
import br.com.fiap.pedido.core.usecase.produto.IBuscarProduto;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

class ProdutoControllerTest {
    private MockMvc mockMvc;

    @Mock
    private IBuscarProduto buscarProdutoUseCase;
    @Mock
    private IBuscarCategoria buscarCategoriaUseCase;

    AutoCloseable openMocks;

    @BeforeEach
    void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
        ProdutoController mensagemController = new ProdutoController(buscarProdutoUseCase, buscarCategoriaUseCase);
        mockMvc = MockMvcBuilders.standaloneSetup(mensagemController)
                .setControllerAdvice(new RestExceptionHandler())
                .addFilter((request, response, chain) -> {
                    response.setCharacterEncoding("UTF-8");
                    chain.doFilter(request, response);
                }, "/*")
                .build();
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Test
    void devePermitirConsultarTodosOsProdutos() throws Exception {
        var produtos = gerarListaDeProdutos();
        when(buscarProdutoUseCase.buscarTodos())
                .thenReturn(produtos);

        mockMvc.perform(get("/produtos")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(produtos.get(0).id()))
                .andExpect(jsonPath("$[0].descricao").value(produtos.get(0).descricao()))
                .andExpect(jsonPath("$[0].nome").value(produtos.get(0).nome()))
                .andExpect(jsonPath("$[0].valor").value(produtos.get(0).valor()))
                .andExpect(jsonPath("$[1].id").value(produtos.get(1).id()))
                .andExpect(jsonPath("$[1].descricao").value(produtos.get(1).descricao()))
                .andExpect(jsonPath("$[1].nome").value(produtos.get(1).nome()))
                .andExpect(jsonPath("$[1].valor").value(produtos.get(1).valor()));
        verify(buscarProdutoUseCase, times(1)).buscarTodos();
    }

    @Test
    void devePermitirConsultarOsProdutosPorCategoria() throws Exception {
        var produtos = gerarListaDeProdutosComUmaCategoria();
        when(buscarProdutoUseCase.buscarPorCategoria(1))
                .thenReturn(produtos);

        mockMvc.perform(get("/produtos/{idCategoria}", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(produtos.get(0).id()))
                .andExpect(jsonPath("$[0].descricao").value(produtos.get(0).descricao()))
                .andExpect(jsonPath("$[0].nome").value(produtos.get(0).nome()))
                .andExpect(jsonPath("$[0].valor").value(produtos.get(0).valor()))
                .andExpect(jsonPath("$[1].id").value(produtos.get(1).id()))
                .andExpect(jsonPath("$[1].descricao").value(produtos.get(1).descricao()))
                .andExpect(jsonPath("$[1].nome").value(produtos.get(1).nome()))
                .andExpect(jsonPath("$[1].valor").value(produtos.get(1).valor()));
        verify(buscarProdutoUseCase, times(1)).buscarPorCategoria(any(Integer.class));
    }

    @Test
    void devePermitirConsultarTodasAsCategorias() throws Exception {
        var categorias = gerarListaCategorias();
        when(buscarCategoriaUseCase.buscarTodas())
                .thenReturn(categorias);

        mockMvc.perform(get("/categorias")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(categorias.get(0).id()))
                .andExpect(jsonPath("$[0].descricao").value(categorias.get(0).descricao()))
                .andExpect(jsonPath("$[0].tipo").value(categorias.get(0).tipo()))
                .andExpect(jsonPath("$[1].id").value(categorias.get(1).id()))
                .andExpect(jsonPath("$[1].descricao").value(categorias.get(1).descricao()))
                .andExpect(jsonPath("$[1].tipo").value(categorias.get(1).tipo()));
        verify(buscarCategoriaUseCase, times(1)).buscarTodas();
    }

    private List<CategoriaResponse> gerarListaCategorias() {
        var lanche = new CategoriaResponse(1, "Lanche", "Lanche");
        var bebida = new CategoriaResponse(2, "Bebida", "Bebida");
        var sobremesa = new CategoriaResponse(3, "Sobremesa", "Sobremesa");
        return List.of(lanche, bebida, sobremesa);
    }

    private List<ProdutoResponse> gerarListaDeProdutos() {
        var lanche = new ProdutoResponse(1, "Hamburguer", "Hambuguer", "R$ 14,90", "");
        var suco = new ProdutoResponse(2, "Suco", "Suco de laranja", "R$ 9,90", "");
        return List.of(lanche, suco);
    }

    private List<ProdutoResponse> gerarListaDeProdutosComUmaCategoria() {
        var lanche = new ProdutoResponse(1, "Hamburguer", "Hambuguer simples", "R$ 14,90", "");
        var lancheDaCasa = new ProdutoResponse(2, "Hamburguer da casa", "Hambuguer especial", "R$ 19,90", "");
        return List.of(lanche, lancheDaCasa);
    }

}
