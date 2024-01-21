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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CriarProdutoUseCaseTest {


    private CriarProdutoUseCase useCase;

    @Mock
    private ProdutoRepository repository;

    AutoCloseable openMocks;

    @BeforeEach
    void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
        useCase = new CriarProdutoUseCase(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }


    @Test
    void devePermitirCriarNovoProduto() {
        // Arrange
        var produtosMock = ProdutosHelper.gerarProdutoRequest();
        var lanche = ProdutosHelper.gerarLanche();
        when(repository.salvar(any(Produto.class))).thenReturn(lanche);
        // Act
        var produto = useCase.criar(produtosMock);
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

}
