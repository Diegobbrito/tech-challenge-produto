package br.com.fiap.produto.core.usecase.produto;

import br.com.fiap.produto.api.dto.response.ProdutoResponse;
import br.com.fiap.produto.core.entity.Produto;
import br.com.fiap.produto.gateway.repository.produto.ProdutoRepository;
import br.com.fiap.produto.utils.ProdutosHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class GerenciarProdutoUseCaseTest {


    private GerenciarProdutoUseCase useCase;

    @Mock
    private ProdutoRepository repository;

    AutoCloseable openMocks;

    @BeforeEach
    void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
        useCase = new GerenciarProdutoUseCase(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Nested
    class AlterarProduto{
        @Test
        void devePermitirAlterarProduto() {
            // Arrange
            var produtoSalvo = ProdutosHelper.gerarProdutoEntity();
            var produtosAlterado = ProdutosHelper.gerarProdutoRequestAlterado();
            var lanche = ProdutosHelper.gerarLanche();
            when(repository.buscarPorId(anyInt())).thenReturn(Optional.of(produtoSalvo));
            when(repository.salvar(any(Produto.class))).thenReturn(lanche);
            // Act
            var produto = useCase.atualizar(1, produtosAlterado);
            // Assert
            verify(repository, times(1)).salvar(any(Produto.class));
            assertThat(produto)
                    .isInstanceOf(ProdutoResponse.class)
                    .isNotNull();
            assertThat(produto)
                    .extracting(ProdutoResponse::id)
                    .isEqualTo(1);
            assertThat(produto)
                    .extracting(ProdutoResponse::nome)
                    .isEqualTo("Hamburguer");
        }

        @Test
        void deveGerarExcesaoAoAlterarProdutoInexistente() {
            var produtosAlterado = ProdutosHelper.gerarProdutoRequestAlterado();
            when(repository.buscarPorId(anyInt())).thenReturn(Optional.empty());
            assertThatThrownBy(() -> useCase.atualizar(1, produtosAlterado))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("Produto não encontrado para atualização");
            verify(repository, times(1)).buscarPorId(anyInt());
        }
    }

    @Nested
    class ExcluirProduto{
        @Test
        void devePermitirExcluirProduto() {
            var produtoSalvo = ProdutosHelper.gerarProdutoEntity();
            when(repository.buscarPorId(anyInt())).thenReturn(Optional.of(produtoSalvo));
            doNothing()
                    .when(repository).excluir(anyInt());
            useCase.excluirProduto(1);
            verify(repository, times(1)).buscarPorId(anyInt());
            verify(repository, times(1)).excluir(anyInt());
        }

        @Test
        void deveGerarExcesaoAoExcluirProdutoInexistente() {

            when(repository.buscarPorId(anyInt())).thenReturn(Optional.empty());
            assertThatThrownBy(() -> useCase.excluirProduto(1))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("Produto não encontrado para exclusão");
            verify(repository, times(1)).buscarPorId(anyInt());
        }
    }


}
