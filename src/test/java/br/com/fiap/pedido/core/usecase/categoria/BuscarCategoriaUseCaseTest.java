package br.com.fiap.pedido.core.usecase.categoria;

import br.com.fiap.pedido.api.dto.response.CategoriaResponse;
import br.com.fiap.pedido.core.entity.Categoria;
import br.com.fiap.pedido.gateway.repository.ICategoriaRepository;
import br.com.fiap.pedido.gateway.repository.categoria.CategoriaEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class BuscarCategoriaUseCaseTest {

    private BuscarCategoriaUseCase useCase;
    @Mock
    private ICategoriaRepository repository;
    AutoCloseable openMocks;

    @BeforeEach
    void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
        useCase = new BuscarCategoriaUseCase(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Nested
    class ListarCategorias {

        @Test
        void devePermitirConsultarTodasAsCategorias() {
            var categoriasMock = gerarCategorias();
            when(repository.buscarTodas()).thenReturn(categoriasMock);
            // Act
            var categorias = useCase.buscarTodas();
            // Assert
            verify(repository, times(1)).buscarTodas();
            assertThat(categorias)
                    .isInstanceOf(List.class)
                    .isNotNull();
            assertThat(categorias.get(0))
                    .isInstanceOf(CategoriaResponse.class)
                    .extracting(CategoriaResponse::id)
                    .isEqualTo(1);
        }


    }

    private List<Categoria> gerarCategorias() {
        return List.of(new Categoria(1),
                new Categoria(2),
                new Categoria(3));
    }
}
