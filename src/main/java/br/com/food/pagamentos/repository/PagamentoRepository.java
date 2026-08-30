package br.com.food.pagamentos.repository;

import br.com.food.pagamentos.model.PagamentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagamentoRepository extends JpaRepository<Long , PagamentoEntity> {
}
