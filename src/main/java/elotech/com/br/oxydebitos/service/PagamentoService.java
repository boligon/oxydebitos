package elotech.com.br.oxydebitos.service;

import elotech.com.br.oxydebitos.domain.Debito;
import elotech.com.br.oxydebitos.domain.Pagamento;
import elotech.com.br.oxydebitos.domain.PagamentoItem;
import elotech.com.br.oxydebitos.dto.DebitoDTO;
import elotech.com.br.oxydebitos.dto.PagamentoDTO;
import elotech.com.br.oxydebitos.dto.PagamentoItemDTO;
import elotech.com.br.oxydebitos.dto.PagamentoPostDTO;
import elotech.com.br.oxydebitos.enums.SituacaoParcela;
import elotech.com.br.oxydebitos.exception.RestException;
import elotech.com.br.oxydebitos.repository.PagamentoRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Data
@Component
@AllArgsConstructor
public class PagamentoService {

    private PagamentoRepository pagamentoRepository;
    private DebitoParcelaService debitoParcelaService;
    private DebitoService debitoService;

    public PagamentoDTO findById(Long id){
        Pagamento found = this.pagamentoRepository.findById(id)
                .orElseThrow(RestException::notFound);
        return PagamentoDTO.convertToDTO(found);
    }

    public Page<PagamentoDTO> findAll(Pageable pageable){
        return getPagamentoRepository().findAll(pageable).map(PagamentoDTO::convertToDTO);
    }

    private void InsertItensPagamento(Pagamento pagamentoToSave, PagamentoPostDTO pagamentoPostDTO) {
        List<PagamentoItem> pagamentosCancelamento = pagamentoToSave.getItensPagamento();
        for (PagamentoItemDTO pagamentoItemDTO : pagamentoPostDTO.getItens()) {
            Debito debito = DebitoDTO.convertToEntity(getDebitoService().findById(pagamentoItemDTO.getIdDebito()));
            PagamentoItem pagamentoItem = PagamentoItemDTO.convertToEntity(pagamentoItemDTO, pagamentoToSave, debito);
            pagamentosCancelamento.add(pagamentoItem);
        }
    }

    @Transactional
    public PagamentoDTO save(PagamentoPostDTO dto) {
        Pagamento pagamentoToSave = PagamentoPostDTO.newEntity(dto);

        InsertItensPagamento(pagamentoToSave, dto);

        Pagamento saved = this.pagamentoRepository.save(pagamentoToSave);

        for (PagamentoItem item : saved.getItensPagamento()) {

            debitoParcelaService.updateSituacaoParcela(item.getDebitoParcela().getId(), SituacaoParcela.PAGO);
        }

        return PagamentoDTO.convertToDTO(saved);

    }

}
