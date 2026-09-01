CREATE TABLE pagamentos (
    id BIGINT NOT NULL AUTO_INCREMENT,
    valor DECIMAL(19, 2) NOT NULL,
    nome VARCHAR(100) NOT NULL,
    numero VARCHAR(19) NOT NULL,
    expiracao VARCHAR(7) NOT NULL,
    status VARCHAR(20) NOT NULL,
    pedido_id BIGINT NOT NULL,
    forma_pagamento_id BIGINT NOT NULL,

    CONSTRAINT pk_pagamentos PRIMARY KEY (id)
);

