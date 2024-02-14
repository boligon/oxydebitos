package elotech.com.br.oxydebitos.service;

import elotech.com.br.oxydebitos.domain.DebitoParcela;
import elotech.com.br.oxydebitos.dto.ParcelaDTO;
import elotech.com.br.oxydebitos.dto.VencimentoParcelaDTO;
import elotech.com.br.oxydebitos.enums.SituacaoParcela;
import elotech.com.br.oxydebitos.exception.RestException;
import elotech.com.br.oxydebitos.repository.DebitoParcelaRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor
public class DebitoParcelaService {

    @Autowired
    DebitoParcelaRepository debitoParcelaRepository;


    public ParcelaDTO updateVencimentoParcela(Long idDebito, VencimentoParcelaDTO vencimentoParcelaDTO) {
        DebitoParcela parcela = findParcelaByDebitoAndNumeroParcela(idDebito, vencimentoParcelaDTO.getNumeroParcela());

        if (Objects.isNull(parcela)) {
            throw new RuntimeException("Não foi possível encontrar a parcela para atualização");
        }

        parcela.setDataVencimento(vencimentoParcelaDTO.getDataVencimento());

        return ParcelaDTO.convertToDTO(this.debitoParcelaRepository.save(parcela));
    }

    public void updateSituacaoParcela(Long idParcela, SituacaoParcela situacaoParcela) {
        DebitoParcela parcela = this.debitoParcelaRepository.findById(idParcela).orElseThrow(RestException::notFound);

        parcela.setSituacaoParcela(situacaoParcela);

        this.debitoParcelaRepository.save(parcela);
    }

    private DebitoParcela findParcelaByDebitoAndNumeroParcela(Long idDebito, Long numeroParcela) {
        return this.debitoParcelaRepository.findParcelaByIdAndNumeroParcela(idDebito, numeroParcela);
    }

 }

