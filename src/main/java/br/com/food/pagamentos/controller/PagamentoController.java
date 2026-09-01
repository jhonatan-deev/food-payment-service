package br.com.food.pagamentos.controller;

import br.com.food.pagamentos.dto.pagamento.PagamentoRequestDTO;
import br.com.food.pagamentos.dto.pagamento.PagamentoResponseDTO;
import br.com.food.pagamentos.dto.pagamento.PagamentoUpdateStatusDTO;
import br.com.food.pagamentos.service.PagamentoService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/pagamentos")
public class PagamentoController {

    private final PagamentoService pagamentoService;

    public PagamentoController(PagamentoService pagamentoService) {
        this.pagamentoService = pagamentoService;
    }

    @PostMapping
    public ResponseEntity<PagamentoResponseDTO> create(@RequestBody @Valid PagamentoRequestDTO dto, UriComponentsBuilder uriBuilder) {

        PagamentoResponseDTO response = pagamentoService.create(dto);

        URI uri = uriBuilder.path("/pagamentos/{id}").buildAndExpand(
                response.id()).toUri();
        return ResponseEntity.created(uri)
                .body(response);
    }

    @PatchMapping("/{pagamentoId}")
    public ResponseEntity<PagamentoResponseDTO> update(@PathVariable Long pagamentoId, @RequestBody @Valid PagamentoUpdateStatusDTO dto) {
        PagamentoResponseDTO response = pagamentoService.update(dto, pagamentoId);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{pagamentoId}")
    public ResponseEntity<PagamentoResponseDTO> findById(@PathVariable Long pagamentoId) {
        return ResponseEntity.ok(pagamentoService.findById(pagamentoId));
    }

    @GetMapping
    public ResponseEntity<Page<PagamentoResponseDTO>> findAll(Pageable pageable) {
        return ResponseEntity.ok(pagamentoService.findAllPayment(pageable));
    }
}

