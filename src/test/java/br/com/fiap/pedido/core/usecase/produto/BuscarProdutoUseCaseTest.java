package br.com.fiap.pedido.core.usecase.produto;

import br.com.fiap.pedido.api.dto.response.ProdutoResponse;
import br.com.fiap.pedido.core.entity.Categoria;
import br.com.fiap.pedido.core.entity.Produto;
import br.com.fiap.pedido.gateway.repository.produto.JpaProdutoRepository;
import br.com.fiap.pedido.gateway.repository.produto.ProdutoEntity;
import br.com.fiap.pedido.gateway.repository.produto.ProdutoRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class BuscarProdutoUseCaseTest {


    private BuscarProdutoUseCase useCase;

    @Mock
    private ProdutoRepository repository;

    AutoCloseable openMocks;

    @BeforeEach
    void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
        useCase = new BuscarProdutoUseCase(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }


    @Test
    void devePermitirConsultarTodosOsProdutos() {
        // Arrange
        var produtosMock = gerarProdutos();
        when(repository.buscarTodos()).thenReturn(produtosMock);
        // Act
        var produtos = useCase.buscarTodos();
        // Assert
        verify(repository, times(1)).buscarTodos();
        assertThat(produtos)
                .isInstanceOf(List.class)
                .isNotNull();
        assertThat(produtos.get(0))
                .extracting(ProdutoResponse::id)
                .isEqualTo(1);
        assertThat(produtos.get(0))
                .extracting(ProdutoResponse::nome)
                .isEqualTo("Hamburguer");
        assertThat(produtos)
                .asList()
                .size()
                .isEqualTo(2);
    }

    @Test
    void devePermitirConsultarOsProdutosPorCategoria() {
        // Arrange
        Produto lanche = new Produto(
                "Hamburguer",
                "Hamburguer da casa",
                new BigDecimal("14.90"),
                new Categoria(1),
                "teste");
        lanche.setId(1);
        var produtosMock = List.of(lanche);
        when(repository.buscarPorCategoria(any(Integer.class))).thenReturn(produtosMock);
        // Act
        var produtos = useCase.buscarPorCategoria(1);
        // Assert
        verify(repository, times(1))
                .buscarPorCategoria(any(Integer.class));
        assertThat(produtos)
                .isInstanceOf(List.class)
                .isNotNull();
        assertThat(produtos.get(0))
                .extracting(ProdutoResponse::id)
                .isEqualTo(1);
        assertThat(produtos.get(0))
                .extracting(ProdutoResponse::nome)
                .isEqualTo("Hamburguer");
    }

    private List<Produto> gerarProdutos() {
        Produto lanche = new Produto(
                "Hamburguer",
                "Hamburguer da casa",
                new BigDecimal("14.90"),
                new Categoria(1),
                "teste");
        lanche.setId(1);
        Produto bebida = new Produto(
                "Suco de laranja",
                "Suco de laranja 500ml",
                new BigDecimal("9.90"),
                new Categoria(2),
                "teste");
        bebida.setId(2);

        return List.of(lanche, bebida);
    }
}
