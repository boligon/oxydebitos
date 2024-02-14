package elotech.com.br.oxydebitos.validator;

import elotech.com.br.oxydebitos.dto.DebitoPostDTO;
import elotech.com.br.oxydebitos.dto.ParcelaDTO;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.util.*;

@Component
public class DebitoParcelaValidator {

    private void validaValorParcelaMaiorZero(DebitoPostDTO debito) throws Exception {
        for (ParcelaDTO parcela : debito.getParcelas()) {
            if (Objects.isNull(parcela.getValorParcela()) ||
                    parcela.getValorParcela().compareTo(BigDecimal.ZERO) <= 0) {
                throw new Exception(String.format("Valor da Parcela %s deve ser maior que zero", parcela.getNumeroParcela()));
            }
        }
    }

    private void validaExisteMesmoNumeroParcela(DebitoPostDTO debito) throws Exception {
        final Map<Long, Integer> qtde = new HashMap<>();
        for (ParcelaDTO parcela: debito.getParcelas()) {
            qtde.put(parcela.getNumeroParcela(), Collections.frequency(debito.getParcelas(), parcela.getNumeroParcela()));
        }
        final Set<Long> chaves = qtde.keySet();
        for (final Long chave : chaves) {
            if (qtde.get(chave) > 1) {
                throw new Exception("Existe mais de um número de parcela para o débito");
            }
        }
    }

    private void validaDataVencimentoMaiorAtual(DebitoPostDTO debito) throws Exception {
        LocalDateTime now = LocalDateTime.now();
        for (ParcelaDTO parcela : debito.getParcelas()) {
            if (Objects.isNull(parcela.getDataVencimento()) ||
                    parcela.getDataVencimento().isBefore(ChronoLocalDate.from(now)))
                throw new Exception("Data de Vencimento deve ser maior que a data atual");
        }
    }

    private void validaPossuiUmaParcela(DebitoPostDTO debito) throws Exception {
        if (debito.getParcelas().isEmpty()) {
            throw new Exception("Débito deve possuir pelo menos uma parcela");
        }
    }

    public void validaDebitoParcela(DebitoPostDTO debito) throws Exception {
        this.validaValorParcelaMaiorZero(debito);

        this.validaPossuiUmaParcela(debito);

        this.validaDataVencimentoMaiorAtual(debito);

        this.validaExisteMesmoNumeroParcela(debito);

    }

}
