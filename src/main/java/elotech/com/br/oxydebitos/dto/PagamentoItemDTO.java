package elotech.com.br.oxydebitos.dto;

import elotech.com.br.oxydebitos.domain.Debito;
import elotech.com.br.oxydebitos.domain.Pagamento;
import elotech.com.br.oxydebitos.domain.PagamentoItem;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class PagamentoItemDTO {
    private Long id = 0L;
    private Long idDebito;
    @NotNull
    private ParcelaDTO parcela;
    private BigDecimal valorPago;

    public static PagamentoItemDTO convertToDTO(PagamentoItem entity) {
        PagamentoItemDTO dto = new PagamentoItemDTO();
        dto.setId(entity.getId());
        dto.setParcela(ParcelaDTO.convertToDTO(entity.getDebitoParcela()));
        dto.setValorPago(entity.getValorPago());
        return dto;
    }

    public static List<PagamentoItemDTO> convertToListDTO(List<PagamentoItem> itensPagamento) {
        List<PagamentoItemDTO> itensPagamentoDTO = new ArrayList<>();

        for (PagamentoItem entity: itensPagamento) {
            PagamentoItemDTO dto = new PagamentoItemDTO();
            dto.setId(entity.getId());
            dto.setParcela(ParcelaDTO.convertToDTO(entity.getDebitoParcela()));
            dto.setValorPago(entity.getValorPago());
            itensPagamentoDTO.add(dto);
        }
        return itensPagamentoDTO;
    }

    public static PagamentoItem convertToEntity(PagamentoItemDTO dto, Pagamento pagamento, Debito debito) {
        PagamentoItem entity = new PagamentoItem();
        entity.setDebitoParcela(ParcelaDTO.convertToEntity(dto.getParcela(), debito));
        entity.setValorPago(dto.getValorPago());
        entity.setPagamento(pagamento);

        return entity;
    }

    public static List<PagamentoItem> convertToListEntity(List<PagamentoItemDTO> itensPagamentoDTO) {
        List<PagamentoItem> itensPagamento = new ArrayList<>();

        for (PagamentoItemDTO dto: itensPagamentoDTO) {

            PagamentoItem entity = new PagamentoItem();
            entity.setDebitoParcela(ParcelaDTO.convertToEntity(dto.getParcela(), new Debito()));
            entity.setValorPago(dto.getValorPago());
            itensPagamento.add(entity);
        }
        return itensPagamento;
    }
}
