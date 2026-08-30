package br.com.food.pagamentos.exception;

public class TransicaoStatusPagamentoException extends RuntimeException {
    public TransicaoStatusPagamentoException(String message) {
        super(message);
    }
}
