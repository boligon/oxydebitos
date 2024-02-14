package elotech.com.br.oxydebitos.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;

@Data
public class VencimentoParcelaDTO {

    @NotBlank
    private Long numeroParcela;
    @NotBlank
    private LocalDate dataVencimento;
}