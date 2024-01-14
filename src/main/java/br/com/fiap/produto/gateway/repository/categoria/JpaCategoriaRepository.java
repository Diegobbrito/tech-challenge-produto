package br.com.fiap.produto.gateway.repository.categoria;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaCategoriaRepository extends JpaRepository<CategoriaEntity, Integer> {
    Optional<CategoriaEntity> findById(Integer id);
}
