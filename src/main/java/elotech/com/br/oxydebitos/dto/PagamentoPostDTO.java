package elotech.com.br.oxydebitos.dto;

import elotech.com.br.oxydebitos.domain.Pagamento;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class PagamentoPostDTO {

    private Long id = 0L;
    private Long numeroPagamento;
    private LocalDate dataPagamento;
    @NotNull
    private List<PagamentoItemDTO> itens = new ArrayList<>();

    public static Pagamento newEntity(PagamentoPostDTO pagamentoPostDTO) {
        Pagamento pagamento = new Pagamento();
        pagamento.setNumeroPagamento(pagamentoPostDTO.getNumeroPagamento());
        pagamento.setDataPagamento(pagamentoPostDTO.getDataPagamento());

        return pagamento;
    }

}
