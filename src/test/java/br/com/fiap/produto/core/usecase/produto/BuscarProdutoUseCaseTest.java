package br.com.fiap.produto.core.usecase.produto;

import br.com.fiap.produto.api.dto.response.ProdutoResponse;
import br.com.fiap.produto.core.entity.Produto;
import br.com.fiap.produto.gateway.repository.produto.ProdutoRepository;
import br.com.fiap.produto.utils.ProdutosHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class BuscarProdutoUseCaseTest {


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
        var produtosMock = ProdutosHelper.gerarListaDeProdutos();
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
        Produto lanche =ProdutosHelper.gerarLanche();
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

    @Test
    void devePermitirConsultarOsProdutosPorIds() {
        // Arrange
        Produto lanche =ProdutosHelper.gerarLanche();
        var produtosMock = List.of(lanche);
        when(repository.buscarTodosPorIds(anyList())).thenReturn(produtosMock);
        // Act
        var produtos = useCase.buscarPorIds(List.of(1,2));
        // Assert
        verify(repository, times(1))
                .buscarTodosPorIds(anyList());
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
}
