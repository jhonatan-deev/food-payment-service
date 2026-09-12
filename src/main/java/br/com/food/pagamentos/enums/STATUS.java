package br.com.food.pagamentos.enums;

public enum STATUS {

    PENDENTE,
    CONFIRMADO,
    RECUSADO,
    CANCELADO;

    public boolean podeIrPara(STATUS novoStatus) {
        return switch (this) {
            case PENDENTE ->
                    novoStatus == CONFIRMADO ||
                            novoStatus == RECUSADO ||
                            novoStatus == CANCELADO;

            case CONFIRMADO ->
                    novoStatus == CANCELADO;

            case RECUSADO, CANCELADO ->
                    false;
        };
    }
}