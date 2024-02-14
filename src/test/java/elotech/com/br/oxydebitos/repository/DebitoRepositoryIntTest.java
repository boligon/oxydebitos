package elotech.com.br.oxydebitos.repository;

import elotech.com.br.oxydebitos.domain.Debito;
import elotech.com.br.oxydebitos.enums.SituacaoParcela;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Rollback
@Sql(statements = {
        "insert into pessoa " +
                "(id, nome, cnpjcpf, tipopessoa)" +
                "values(1, 'Maria', '14185730004', 'F')",
        "insert into debito " +
                "(id, pessoa, datalancamento)" +
                "values(1, 1, '2024-03-10')",
        "insert into debito " +
                "(id, pessoa, datalancamento)" +
                "values(2, 1, '2024-03-10')",
        "insert into debitoparcela " +
                "(id, iddebito, numeroparcela, datavencimento, situacaoparcela, valorparcela)" +
                "values(1, 1, 1, '2024-03-14','A',1000.00)",
        "insert into debitoparcela " +
                "(id, iddebito, numeroparcela, datavencimento, situacaoparcela, valorparcela)" +
                "values(2, 1, 2, '2024-04-14','A',1500.00)",
        "insert into debitoparcela " +
                "(id, iddebito, numeroparcela, datavencimento, situacaoparcela, valorparcela)" +
                "values(3, 2, 1, '2024-05-15','A',10000.00)"
})
class DebitoRepositoryIntegrationTest {

    @Autowired
    private DebitoRepository debitoRepository;

    @Test
    void findAllByPessoaId_ExistingId_ReturnsDebitos() {

        Long pessoaId = 1L;

        List<Debito> debitos = debitoRepository.findAllByPessoaId(pessoaId);

        assertTrue(!debitos.isEmpty());
    }

    @Test
    void findAllByPessoaCnpjCpf_ExistingCnpjCpf_ReturnsDebitos() {
        String cnpjCpf = "14185730004";
        List<Debito> debitos = debitoRepository.findAllByPessoaCnpjCpf(cnpjCpf);
        assertTrue(!debitos.isEmpty());
    }

    @Test
    void findAllByDataLancamentoBetween_ExistingDates_ReturnsDebitos() {
        LocalDate dataInicial = LocalDate.of(2024, 1, 1);
        LocalDate dataFinal = LocalDate.of(2024, 12, 31);
        List<Debito> debitos = debitoRepository.findAllByDataLancamentoBetween(dataInicial, dataFinal);
        assertTrue(!debitos.isEmpty());
    }

    @Test
    void findAllByPessoaNomeAndDataLancamentoBetween_ExistingNameAndDates_ReturnsDebitos() {
        String nome = "Maria";
        LocalDate dataInicial = LocalDate.of(2024, 1, 1);
        LocalDate dataFinal = LocalDate.of(2024, 12, 31);
        List<Debito> debitos = debitoRepository.findAllByPessoaNomeAndDataLancamentoBetween(nome, dataInicial, dataFinal);
        assertTrue(!debitos.isEmpty());
    }

    @Test
    void findByParcelas_SituacaoParcela_ExistingSituacaoParcela_ReturnsDebitos() {

        SituacaoParcela situacaoParcela = SituacaoParcela.ABERTO;
        List<Debito> debitos = debitoRepository.findByParcelas_SituacaoParcela(situacaoParcela);
        assertTrue(!debitos.isEmpty());
    }
}