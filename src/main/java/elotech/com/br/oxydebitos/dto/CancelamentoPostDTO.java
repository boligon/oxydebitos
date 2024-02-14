package elotech.com.br.oxydebitos.dto;

import elotech.com.br.oxydebitos.domain.Cancelamento;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class CancelamentoPostDTO {

    private Long id = 0L;
    private Long numeroCancelamento;
    private String motivo;
    private LocalDate dataCancelamento;
    @NotNull
    private List<CancelamentoItemDTO> itens = new ArrayList<>();

    public static Cancelamento newEntity(CancelamentoPostDTO cancelamentoPostDTO) {
        Cancelamento cancelamento = new Cancelamento();
        cancelamento.setNumeroCancelamento(cancelamentoPostDTO.getNumeroCancelamento());
        cancelamento.setMotivo(cancelamentoPostDTO.getMotivo());
        cancelamento.setDataCancelamento(cancelamentoPostDTO.getDataCancelamento());

        return cancelamento;
    }

}
