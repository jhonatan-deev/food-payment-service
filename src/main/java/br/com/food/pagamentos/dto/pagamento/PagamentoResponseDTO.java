package br.com.food.pagamentos.dto.pagamento;

import br.com.food.pagamentos.enuns.STATUS;

import java.math.BigDecimal;

public record PagamentoResponseDTO(
        Long id,
        BigDecimal valor,
        String nome,
        String numero,
        String expiracao,
        STATUS status,
        Long pedidoId,
        Long formaDePagamentoId
) {
}
