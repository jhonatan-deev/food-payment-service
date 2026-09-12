package br.com.food.pagamentos.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "ECOMMERCE-API")
public interface PedidoClient {

    @PutMapping("/api/v1/pedidos/{idPedido}/pago")
    void atualizarPagamento(@PathVariable Long idPedido);
}
