package elotech.com.br.oxydebitos.dto;

import elotech.com.br.oxydebitos.domain.Debito;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class DebitoPostDTO {


    private Long id = 0L;
    private LocalDate dataLancamento;

    private Long pessoa;
    @NotNull
    private List<ParcelaDTO> parcelas = new ArrayList<>();

    public static Debito newEntity(DebitoPostDTO debitoPostDTO) {
        Debito debito = new Debito();
        debito.setDataLancamento(debitoPostDTO.getDataLancamento());

        return debito;
    }
}
