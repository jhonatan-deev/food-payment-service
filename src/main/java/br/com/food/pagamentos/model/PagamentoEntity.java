package br.com.food.pagamentos.model;

import br.com.food.pagamentos.enuns.STATUS;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "pagamentos")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PagamentoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Positive
    private BigDecimal valor;

    @NotBlank
    @Size(max = 100)
    private String nome;

    @NotBlank
    @Size(max = 19)
    private String numero;

    @NotBlank
    @Size(max = 7)
    private String expiracao;

    @NotNull
    @Enumerated(EnumType.STRING)
    private STATUS status;

    @NotNull
    @Column(name = "pedido_id")
    private Long pedidoId;

    @NotNull
    @Column(name = "forma_pagamento_id")
    private Long formaDePagamentoId;

    public PagamentoEntity(
            BigDecimal valor,
            String nome,
            String numero,
            String expiracao,
            Long pedidoId,
            Long formaDePagamentoId
    ) {
        this.valor = valor;
        this.nome = nome;
        this.numero = numero;
        this.expiracao = expiracao;
        this.pedidoId = pedidoId;
        this.formaDePagamentoId = formaDePagamentoId;
        this.status = STATUS.PENDENTE;
    }

    public void confirmar() {
        alterarStatus(STATUS.CONFIRMADO);
    }

    public void recusar() {
        alterarStatus(STATUS.RECUSADO);
    }

    public void cancelar() {
        alterarStatus(STATUS.CANCELADO);
    }

    private void alterarStatus(STATUS novoStatus) {
        if (!this.status.podeIrPara(novoStatus)) {
            throw new IllegalStateException(
                    "Não é possível alterar o status de "
                            + this.status
                            + " para "
                            + novoStatus
            );
        }

        this.status = novoStatus;
    }
}