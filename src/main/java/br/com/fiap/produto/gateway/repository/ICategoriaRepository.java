package br.com.fiap.produto.gateway.repository;

import br.com.fiap.produto.core.entity.Categoria;
import java.util.List;

public interface ICategoriaRepository {
    List<Categoria> buscarTodas();
}
