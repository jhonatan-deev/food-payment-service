package br.com.food.pagamentos.dto;

import java.time.LocalDateTime;

public record ExceptionResponseDTO(
        LocalDateTime timestamp,
        int status,
        String error,
        String message
) {
}
