package br.com.fiap.produto.gateway.repository.produto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import br.com.fiap.produto.core.entity.Categoria;
import br.com.fiap.produto.core.entity.Produto;
import br.com.fiap.produto.utils.ProdutosHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.List;

class ProdutoRepositoryTest {


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

    @Nested
    class ListarProdutos {
        @Test
        void devePermitirConsultarTodosOsProdutos() {
            // Arrange
            var produtosMock = ProdutosHelper.gerarListaDeProdutoEntity();
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
            assertThat(produtos.get(0).getCategoria())
                    .extracting(Categoria::getId)
                    .isEqualTo(1)
                    .isNotNull();

        }

        @Test
        void devePermitirConsultarOsProdutosPorIds() {
            // Arrange
            var produtosMock = ProdutosHelper.gerarListaDeProdutoEntity();
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
            assertThat(produtos.get(1))
                    .extracting(Produto::getNome)
                    .isEqualTo("Suco de laranja");
        }
    }

    @Nested
    class CriarProduto {
        @Test
        void devePermitirRegistrarProduto() {
            // Arrange
            var produtoEntity = ProdutosHelper.gerarProdutoEntity();
            var produto = ProdutosHelper.gerarLanche();
            when(jpaRepository.save(any(ProdutoEntity.class))).thenReturn(produtoEntity);
            // Act
            var produtoSalvo = produtoRepository.salvar(produto);
            // Assert
            verify(jpaRepository, times(1)).save(produtoEntity);
            assertThat(produtoSalvo)
                    .isInstanceOf(Produto.class)
                    .isNotNull();
            assertThat(produtoSalvo)
                    .extracting(Produto::getId)
                    .isEqualTo(produto.getId());
            assertThat(produtoSalvo)
                    .extracting(Produto::getNome)
                    .isEqualTo(produto.getNome());
            assertThat(produtoSalvo)
                    .extracting(Produto::getDescricao)
                    .isEqualTo(produto.getDescricao());

        }
    }
    @Nested
    class DeletarProduto {
        @Test
        void devePermitirApagarProduto() {
            // Arrange
            var id = 1;
            doNothing().when(jpaRepository).deleteById(id);
            // Act
            produtoRepository.excluir(id);
            // Assert
            verify(jpaRepository, times(1)).deleteById(id);
        }
    }


}
