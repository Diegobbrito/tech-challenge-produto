package br.com.fiap.pedido.gateway.repository.cliente;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaClienteRepository extends JpaRepository<ClienteEntity, Integer> {
    Optional<ClienteEntity> findByCpf(String cpf);
}
