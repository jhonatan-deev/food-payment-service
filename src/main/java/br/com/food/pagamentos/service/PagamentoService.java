package br.com.food.pagamentos.service;

import br.com.food.pagamentos.client.PedidoClient;
import br.com.food.pagamentos.dto.pagamento.PagamentoRequestDTO;
import br.com.food.pagamentos.dto.pagamento.PagamentoResponseDTO;
import br.com.food.pagamentos.dto.pagamento.PagamentoUpdateStatusDTO;
import br.com.food.pagamentos.enums.STATUS;
import br.com.food.pagamentos.exception.IdPagamentoNotFoundException;
import br.com.food.pagamentos.mapper.PagamentoMapper;
import br.com.food.pagamentos.model.Pagamento;
import br.com.food.pagamentos.repository.PagamentoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PagamentoService {
    private final PagamentoRepository pagamentoRepository;
    private final PagamentoMapper pagamentoMapper;
    private final PedidoClient pedidoClient;

    public PagamentoService(PagamentoRepository pagamentoRepository, PagamentoMapper pagamentoMapper, PedidoClient pedidoClient) {
        this.pagamentoRepository = pagamentoRepository;
        this.pagamentoMapper = pagamentoMapper;
        this.pedidoClient = pedidoClient;
    }

    @Transactional
    public PagamentoResponseDTO create(PagamentoRequestDTO dto) {
        Pagamento pagamento = pagamentoMapper.toEntity(dto);
        pagamentoRepository.save(pagamento);
        return pagamentoMapper.toDTO(pagamento);
    }

    @Transactional
    public PagamentoResponseDTO update(PagamentoUpdateStatusDTO dto, Long pagamentoId) {
        Pagamento pagamento = pagamentoRepository.findById(pagamentoId)
                .orElseThrow(() -> new IdPagamentoNotFoundException("Pagamento não encontrado!"));
        pagamentoMapper.atualizarStatus(pagamento, dto);

        if (pagamento.getStatus() == STATUS.CONFIRMADO) {
            pedidoClient.atualizarPagamento(pagamento.getPedidoId());
        }
        return pagamentoMapper.toDTO(pagamento);
    }

    public PagamentoResponseDTO findById(Long pagamentoId) {
        Pagamento pagamento = pagamentoRepository.findById(pagamentoId)
                .orElseThrow(() -> new IdPagamentoNotFoundException("Pagamento não encontrado!"));
        return pagamentoMapper.toDTO(pagamento);
    }

    public Page<PagamentoResponseDTO> findAllPayment(Pageable pageable) {
        return pagamentoRepository.findAll(pageable).map(pagamentoMapper::toDTO);
    }
}