package elotech.com.br.oxydebitos.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class CancelamentoPessoaDTO {

    private Long idCancelamento;
    private Long numeroCancelamento;
    private LocalDate dataCancelamento;
    private Long idDebitoParcela;
    private BigDecimal valorCancelado;
    private Long IdDebito;
    private Long numeroParcela;
    private BigDecimal valorParcela;
    private Long idPessoa;
    private String nome;
    private String cnpjCpf;

}
