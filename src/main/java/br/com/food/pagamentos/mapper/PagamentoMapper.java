package br.com.food.pagamentos.mapper;

import br.com.food.pagamentos.dto.pagamento.PagamentoRequestDTO;
import br.com.food.pagamentos.dto.pagamento.PagamentoResponseDTO;
import br.com.food.pagamentos.dto.pagamento.PagamentoUpdateStatusDTO;
import br.com.food.pagamentos.model.Pagamento;
import org.springframework.stereotype.Component;

@Component
public class PagamentoMapper {

    public Pagamento toEntity(PagamentoRequestDTO dto) {
        return new Pagamento(
                dto.valor(),
                dto.nome(),
                dto.numero(),
                dto.expiracao(),
                dto.pedidoId(),
                dto.formaDePagamentoId()
        );
    }

    public PagamentoResponseDTO toDTO(Pagamento entity) {
        return new PagamentoResponseDTO(
                entity.getId(),
                entity.getValor(),
                entity.getNome(),
                entity.getNumero(),
                entity.getExpiracao(),
                entity.getStatus(),
                entity.getPedidoId(),
                entity.getFormaDePagamentoId()
        );
    }

    public void atualizarStatus(Pagamento entity, PagamentoUpdateStatusDTO dto) {
        switch (dto.status()) {
            case CONFIRMADO -> entity.confirmar();
            case RECUSADO -> entity.recusar();
            case CANCELADO -> entity.cancelar();
            case PENDENTE -> throw new IllegalArgumentException("Não é possível alterar um pagamento para PENDENTE");
        }
    }

}
