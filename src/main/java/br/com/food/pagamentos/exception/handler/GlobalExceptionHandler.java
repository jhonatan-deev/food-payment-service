package br.com.food.pagamentos.exception.handler;

import br.com.food.pagamentos.dto.ErrorResponseDTO;
import br.com.food.pagamentos.exception.IdPagamentoNotFoundException;
import br.com.food.pagamentos.exception.TransicaoStatusPagamentoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TransicaoStatusPagamentoException.class)
    public ResponseEntity<ErrorResponseDTO> handleTransicaoStatus(
            TransicaoStatusPagamentoException ex
    ) {
        return buildResponse(
                HttpStatus.BAD_REQUEST,
                ex.getMessage()
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDTO> handleValidation(
            MethodArgumentNotValidException ex
    ) {

        String mensagem = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error ->
                        error.getField() + ": " + error.getDefaultMessage()
                )
                .findFirst()
                .orElse("Dados inválidos");

        return buildResponse(
                HttpStatus.BAD_REQUEST,
                mensagem
        );
    }

    @ExceptionHandler(IdPagamentoNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handlePagamentoNotFound(
            IdPagamentoNotFoundException ex
    ) {
        return buildResponse(
                HttpStatus.NOT_FOUND,
                ex.getMessage()
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleException(
            Exception ex
    ) {
        return buildResponse(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Ocorreu um erro interno no servidor"
        );
    }

    private ResponseEntity<ErrorResponseDTO> buildResponse(
            HttpStatus status,
            String message
    ) {
        ErrorResponseDTO response = new ErrorResponseDTO(
                LocalDateTime.now(),
                status.value(),
                status.getReasonPhrase(),
                message
        );
        return ResponseEntity
                .status(status)
                .body(response);
    }
}