package elotech.com.br.oxydebitos.dto;

import elotech.com.br.oxydebitos.domain.Debito;
import elotech.com.br.oxydebitos.domain.DebitoParcela;
import elotech.com.br.oxydebitos.enums.SituacaoParcela;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Data
public class ParcelaDTO {

    private Long id;
    private Long numeroParcela;
    private LocalDate dataVencimento;
    private SituacaoParcela situacaoParcela;
    private BigDecimal valorParcela;

    public static ParcelaDTO newEntity (Long numeroParcela, LocalDate dataVencimento, BigDecimal valorParcela) {
        ParcelaDTO parcelaDTO = new ParcelaDTO();
        parcelaDTO.setNumeroParcela(numeroParcela);
        parcelaDTO.setDataVencimento(dataVencimento);
        parcelaDTO.setSituacaoParcela(SituacaoParcela.ABERTO);
        parcelaDTO.setValorParcela(valorParcela);

        return parcelaDTO;
    }

    public static ParcelaDTO convertToDTO(DebitoParcela entity) {
            ParcelaDTO dto = new ParcelaDTO();
            dto.setId(entity.getId());
            dto.setDataVencimento(entity.getDataVencimento());
            dto.setNumeroParcela(entity.getNumeroParcela());
            dto.setValorParcela(entity.getValorParcela());
            dto.setSituacaoParcela(entity.getSituacaoParcela());
        return dto;
    }

    public static List<ParcelaDTO> convertToListDTO(List<DebitoParcela> debitosParcela) {
        List<ParcelaDTO> parcelasDTO = new ArrayList<>();

        for (DebitoParcela entity: debitosParcela) {
            ParcelaDTO dto = new ParcelaDTO();
            dto.setId(entity.getId());
            dto.setNumeroParcela(entity.getNumeroParcela());
            dto.setSituacaoParcela(entity.getSituacaoParcela());
            dto.setDataVencimento(entity.getDataVencimento());
            dto.setValorParcela(entity.getValorParcela());

            parcelasDTO.add(dto);
        }
        return parcelasDTO;
    }


    public static DebitoParcela convertToEntity(ParcelaDTO dto, Debito debito) {
        DebitoParcela entity = new DebitoParcela();
        entity.setId(dto.getId());
        entity.setDataVencimento(dto.getDataVencimento());
        entity.setNumeroParcela(dto.getNumeroParcela());
        entity.setValorParcela(dto.getValorParcela());
        entity.setSituacaoParcela(dto.getSituacaoParcela());
        entity.setDebito(debito);

        return entity;
    }

    public static List<DebitoParcela> convertToListEntity(List<ParcelaDTO> parcelasDTO) {
        List<DebitoParcela> debitosParcela = new ArrayList<>();

        for (ParcelaDTO dto: parcelasDTO) {
            DebitoParcela entity = new DebitoParcela();
            entity.setId(dto.getId());
            entity.setNumeroParcela(dto.getNumeroParcela());
            entity.setSituacaoParcela(dto.getSituacaoParcela());
            entity.setDataVencimento(dto.getDataVencimento());
            entity.setValorParcela(dto.getValorParcela());

            debitosParcela.add(entity);
        }
        return debitosParcela;
    }

}
