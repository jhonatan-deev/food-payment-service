package br.com.food.pagamentos.enuns;

public enum STATUS {

    PENDENTE,
    CONFIRMADO,
    RECUSADO,
    CANCELADO;

    public boolean podeIrPara(STATUS novoStatus) {
        return switch (this) {
            case PENDENTE ->
                    novoStatus == CONFIRMADO ||
                            novoStatus == RECUSADO;

            case CONFIRMADO ->
                    novoStatus == CANCELADO;

            case RECUSADO, CANCELADO ->
                    false;
        };
    }
}