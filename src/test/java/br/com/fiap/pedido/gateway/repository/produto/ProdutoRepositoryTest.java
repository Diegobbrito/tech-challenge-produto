package br.com.fiap.pedido.gateway.repository.produto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;


import br.com.fiap.pedido.core.entity.Categoria;
import br.com.fiap.pedido.core.entity.Produto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.List;

public class ProdutoRepositoryTest {


    private ProdutoRepository produtoRepository;

    @Mock
    private JpaProdutoRepository jpaRepository;

    AutoCloseable openMocks;

    @BeforeEach
    void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
        produtoRepository = new ProdutoRepository(jpaRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }


    @Test
    void devePermitirConsultarTodosOsProdutos() {
        // Arrange
        var produtosMock = gerarProdutos();
        when(jpaRepository.findAll()).thenReturn(produtosMock);
        // Act
        var produtos = produtoRepository.buscarTodos();
        // Assert
        verify(jpaRepository, times(1)).findAll();
        assertThat(produtos)
                .isInstanceOf(List.class)
                .isNotNull();
        assertThat(produtos.get(0))
                .extracting(Produto::getId)
                .isEqualTo(1);
        assertThat(produtos.get(0))
                .extracting(Produto::getNome)
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
        var produtosMock = List.of(new ProdutoEntity(lanche));
        when(jpaRepository.findAllByCategoriaId(any(Integer.class))).thenReturn(produtosMock);
        // Act
        var produtos = produtoRepository.buscarPorCategoria(1);
        // Assert
        verify(jpaRepository, times(1))
                .findAllByCategoriaId(any(Integer.class));
        assertThat(produtos)
                .isInstanceOf(List.class)
                .isNotNull();
        assertThat(produtos.get(0))
                .extracting(Produto::getId)
                .isEqualTo(1);
        assertThat(produtos.get(0))
                .extracting(Produto::getNome)
                .isEqualTo("Hamburguer");
        assertThat(produtos.get(0))
                .extracting(Produto::getCategoria)
                .isNotNull()
                .isInstanceOf(Categoria.class);

    }

    @Test
    void devePermitirConsultarOsProdutosPorIds() {
        // Arrange
        var produtosMock = gerarProdutos();
        when(jpaRepository.findByIdIn(any())).thenReturn(produtosMock);
        // Act
        var produtos = produtoRepository.buscarTodosPorIds(List.of(1,2));
        // Assert
        verify(jpaRepository, times(1))
                .findByIdIn(any());
        assertThat(produtos)
                .isInstanceOf(List.class)
                .isNotNull();
        assertThat(produtos.get(0))
                .extracting(Produto::getId)
                .isEqualTo(1);
        assertThat(produtos.get(0))
                .extracting(Produto::getNome)
                .isEqualTo("Hamburguer");
        assertThat(produtos.get(1))
                .extracting(Produto::getId)
                .isEqualTo(2);
        assertThat(produtos.get(2))
                .extracting(Produto::getNome)
                .isEqualTo("Suco de laranja");
    }

    private List<ProdutoEntity> gerarProdutos() {
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

        return List.of(new ProdutoEntity(lanche), new ProdutoEntity(bebida));
    }
}
