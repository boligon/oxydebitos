package elotech.com.br.oxydebitos.service;

import elotech.com.br.oxydebitos.domain.Cancelamento;
import elotech.com.br.oxydebitos.domain.CancelamentoItem;
import elotech.com.br.oxydebitos.domain.Debito;
import elotech.com.br.oxydebitos.dto.CancelamentoDTO;
import elotech.com.br.oxydebitos.dto.CancelamentoItemDTO;
import elotech.com.br.oxydebitos.dto.CancelamentoPostDTO;
import elotech.com.br.oxydebitos.dto.DebitoDTO;
import elotech.com.br.oxydebitos.enums.SituacaoParcela;
import elotech.com.br.oxydebitos.exception.RestException;
import elotech.com.br.oxydebitos.repository.CancelamentoRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Data
@Service
@AllArgsConstructor
public class CancelamentoService {

    private CancelamentoRepository CancelamentoRepository;

    private DebitoParcelaService debitoParcelaService;

    private DebitoService debitoService;

    public Page<CancelamentoDTO> findAll(Pageable pageable){
        return getCancelamentoRepository().findAll(pageable).map(CancelamentoDTO::convertToDTO);
    }

    public CancelamentoDTO findById(Long id){
        Cancelamento found = this.CancelamentoRepository.findById(id)
                .orElseThrow(RestException::notFound);
        return CancelamentoDTO.convertToDTO(found);
    }

    private void InsertItensCancelamento(Cancelamento cancelamentoToSave, CancelamentoPostDTO cancelamentoPostDTO) {
        List<CancelamentoItem> itensCancelamento = cancelamentoToSave.getItensCancelamento();
        for (CancelamentoItemDTO cancelamentoItemDTO : cancelamentoPostDTO.getItens()) {
            Debito debito = DebitoDTO.convertToEntity(getDebitoService().findById(cancelamentoItemDTO.getIdDebito()));
            CancelamentoItem cancelamentoItem = CancelamentoItemDTO.convertToEntity(cancelamentoItemDTO, cancelamentoToSave, debito);
            itensCancelamento.add(cancelamentoItem);
        }
    }

    @Transactional
    public CancelamentoDTO save(CancelamentoPostDTO dto) {
        Cancelamento cancelamentoToSave = CancelamentoPostDTO.newEntity(dto);

        InsertItensCancelamento(cancelamentoToSave, dto);

        Cancelamento saved = this.CancelamentoRepository.save(cancelamentoToSave);

        for (CancelamentoItem item : saved.getItensCancelamento()) {
            debitoParcelaService.updateSituacaoParcela(item.getDebitoParcela().getId(), SituacaoParcela.CANCELADO);
        }

        return CancelamentoDTO.convertToDTO(saved);
    }

}
