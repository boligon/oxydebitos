package elotech.com.br.oxydebitos.validator;

import elotech.com.br.oxydebitos.dto.DebitoPostDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;

@Component
public class DebitoValidator {

    private void validaDataLancamentoMenorAtual(DebitoPostDTO debito) throws Exception {
        LocalDateTime now = LocalDateTime.now();

        if (debito.getDataLancamento().isAfter(ChronoLocalDate.from(now)))
        {
            throw new Exception("Data de Lançamento deve ser menor ou igual a data atual");
        }
    }

    public void validaDebito(DebitoPostDTO debito) throws Exception {
        this.validaDataLancamentoMenorAtual(debito);
    }

}

