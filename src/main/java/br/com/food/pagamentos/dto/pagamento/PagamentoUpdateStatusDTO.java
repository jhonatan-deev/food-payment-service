package br.com.food.pagamentos.dto.pagamento;

import br.com.food.pagamentos.enuns.STATUS;
import jakarta.validation.constraints.NotNull;

public record PagamentoUpdateStatusDTO(
        @NotNull
        STATUS status
) {
}