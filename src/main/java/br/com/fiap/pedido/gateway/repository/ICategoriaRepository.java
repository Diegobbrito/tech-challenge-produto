package br.com.fiap.pedido.gateway.repository;

import br.com.fiap.pedido.core.entity.Categoria;

import java.util.List;

public interface ICategoriaRepository {
    List<Categoria> buscarTodas();
}
