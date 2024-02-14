package elotech.com.br.oxydebitos.dto;

import elotech.com.br.oxydebitos.domain.Cancelamento;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class CancelamentoDTO {
    private Long id;
    private Long numeroCancelamento;
    private LocalDate dataCancelamento;
    private String motivo;
    private List<CancelamentoItemDTO> itensCancelamento;

    public static CancelamentoDTO convertToDTO(Cancelamento entity) {

        CancelamentoDTO dto = new CancelamentoDTO();
        dto.setId(entity.getId());
        dto.setDataCancelamento(entity.getDataCancelamento());
        dto.setNumeroCancelamento(entity.getNumeroCancelamento());
        dto.setMotivo(entity.getMotivo());
        dto.setItensCancelamento(CancelamentoItemDTO.convertToListDTO(entity.getItensCancelamento()));

        return dto;
    }

    public static Cancelamento convertToEntity(CancelamentoDTO dto) {

        Cancelamento entity = new Cancelamento();

        entity.setDataCancelamento(dto.getDataCancelamento());
        entity.setNumeroCancelamento(dto.getNumeroCancelamento());
        entity.setMotivo((dto.getMotivo()));
        entity.setItensCancelamento(CancelamentoItemDTO.convertToListEntity(dto.getItensCancelamento()));

        return entity;
    }

}
