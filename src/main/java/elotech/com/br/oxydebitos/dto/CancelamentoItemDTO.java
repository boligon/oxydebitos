package elotech.com.br.oxydebitos.dto;

import elotech.com.br.oxydebitos.domain.Cancelamento;
import elotech.com.br.oxydebitos.domain.CancelamentoItem;
import elotech.com.br.oxydebitos.domain.Debito;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class CancelamentoItemDTO {
    private Long id = 0L;
    private Long idDebito;
    @NotNull
    private ParcelaDTO parcela;
    private BigDecimal valorCancelado;

    public static CancelamentoItemDTO convertToDTO(CancelamentoItem entity) {
        CancelamentoItemDTO dto = new CancelamentoItemDTO();
        dto.setId(entity.getId());
        dto.setParcela(ParcelaDTO.convertToDTO(entity.getDebitoParcela()));
        dto.setValorCancelado(entity.getValorCancelado());
        return dto;
    }

    public static List<CancelamentoItemDTO> convertToListDTO(List<CancelamentoItem> itensCancelamento) {
        List<CancelamentoItemDTO> itensCancelamentoDTO = new ArrayList<>();

        for (CancelamentoItem entity: itensCancelamento) {
            CancelamentoItemDTO dto = new CancelamentoItemDTO();
            dto.setId(entity.getId());
            dto.setParcela(ParcelaDTO.convertToDTO(entity.getDebitoParcela()));
            dto.setValorCancelado(entity.getValorCancelado());
            itensCancelamentoDTO.add(dto);
        }
        return itensCancelamentoDTO;
    }

    public static CancelamentoItem convertToEntity(CancelamentoItemDTO dto, Cancelamento cancelamento, Debito debito) {
        CancelamentoItem entity = new CancelamentoItem();
        entity.setDebitoParcela(ParcelaDTO.convertToEntity(dto.getParcela(), debito));
        entity.setValorCancelado(dto.getValorCancelado());
        entity.setCancelamento(cancelamento);

        return entity;
    }

    public static List<CancelamentoItem> convertToListEntity(List<CancelamentoItemDTO> itensCancelamentoDTO) {
        List<CancelamentoItem> itensCancelamento = new ArrayList<>();

        for (CancelamentoItemDTO dto: itensCancelamentoDTO) {
            CancelamentoItem entity = new CancelamentoItem();


            entity.setDebitoParcela(ParcelaDTO.convertToEntity(dto.getParcela(), new Debito()));
            entity.setValorCancelado(dto.getValorCancelado());
            itensCancelamento.add(entity);
        }
        return itensCancelamento;
    }
}
