package br.com.fiap.produto.gateway.repository.categoria;

import br.com.fiap.produto.core.entity.Categoria;
import br.com.fiap.produto.gateway.repository.produto.ProdutoEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class CategoriaRepositoryTest {


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
        assertThat(categoriasMock.get(0))
                .isInstanceOf(CategoriaEntity.class)
                .extracting(CategoriaEntity::getProdutos)
                .isNotNull();
    }

    private List<CategoriaEntity> gerarCategorias() {
        var categoria = new CategoriaEntity(1);
        categoria.setTitulo("Lanche");
        categoria.setProdutos(List.of(new ProdutoEntity()));
        return List.of(categoria, new CategoriaEntity(1),
                new CategoriaEntity(2),
                new CategoriaEntity(3));
    }
}
