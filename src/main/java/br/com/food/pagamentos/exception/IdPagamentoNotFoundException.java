package br.com.food.pagamentos.exception;

public class IdPagamentoNotFoundException extends RuntimeException {
    public IdPagamentoNotFoundException(String message) {
        super(message);
    }
}
