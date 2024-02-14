package elotech.com.br.oxydebitos.dto;

import elotech.com.br.oxydebitos.domain.Pagamento;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class PagamentoDTO {

    private Long id;
    private Long numeroPagamento;
    private LocalDate dataPagamento;
    private List<PagamentoItemDTO> itensPagamento;

    public static PagamentoDTO convertToDTO(Pagamento entity) {

        PagamentoDTO dto = new PagamentoDTO();
        dto.setId(entity.getId());
        dto.setDataPagamento(entity.getDataPagamento());
        dto.setNumeroPagamento(entity.getNumeroPagamento());
        dto.setItensPagamento(PagamentoItemDTO.convertToListDTO(entity.getItensPagamento()));

        return dto;
    }

    public static Pagamento convertToEntity(PagamentoDTO dto) {

        Pagamento entity = new Pagamento();

        entity.setDataPagamento(dto.getDataPagamento());
        entity.setNumeroPagamento(dto.getNumeroPagamento());
        entity.setItensPagamento(PagamentoItemDTO.convertToListEntity(dto.getItensPagamento()));

        return entity;
    }

}
