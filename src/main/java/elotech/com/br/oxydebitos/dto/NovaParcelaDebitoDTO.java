package elotech.com.br.oxydebitos.dto;

import elotech.com.br.oxydebitos.enums.SituacaoParcela;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class NovaParcelaDebitoDTO {

    private Long numeroParcela;
    private LocalDate dataVencimento;
    private SituacaoParcela situacaoParcela;
    private BigDecimal valorParcela;

}
