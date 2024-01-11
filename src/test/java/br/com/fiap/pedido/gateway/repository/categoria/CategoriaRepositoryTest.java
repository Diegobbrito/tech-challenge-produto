package br.com.fiap.pedido.gateway.repository.categoria;

import br.com.fiap.pedido.core.entity.Categoria;
import br.com.fiap.pedido.core.entity.Produto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CategoriaRepositoryTest {


    private CategoriaRepository categoriaRepository;

    @Mock
    private JpaCategoriaRepository jpaRepository;

    AutoCloseable openMocks;

    @BeforeEach
    void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
        categoriaRepository = new CategoriaRepository(jpaRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }


    @Test
    void devePermitirConsultarTodasAsCategorias() {
        // Arrange
        var categoriasMock = gerarCategorias();
        when(jpaRepository.findAll()).thenReturn(categoriasMock);
        // Act
        var categorias = categoriaRepository.buscarTodas();
        // Assert
        verify(jpaRepository, times(1)).findAll();
        assertThat(categorias)
                .isInstanceOf(List.class)
                .isNotNull();
        assertThat(categorias.get(0))
                .isInstanceOf(Categoria.class)
                .extracting(Categoria::getId)
                .isEqualTo(1);
    }

    private List<CategoriaEntity> gerarCategorias() {
        return List.of(new CategoriaEntity(1),
                new CategoriaEntity(2),
                new CategoriaEntity(3));
    }
}
