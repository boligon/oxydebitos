package elotech.com.br.oxydebitos.repository;


import elotech.com.br.oxydebitos.domain.Cancelamento;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;


@Rollback
@ExtendWith(SpringExtension.class)
@DataJpaTest
@Sql(statements = {
        "insert into pessoa " +
                "(id, nome, cnpjcpf, tipopessoa)" +
                "values(1, 'Maria', '14185730004', 'F');",
        "insert into debito " +
                "(id, pessoa, datalancamento)" +
                "values(1, 1, '2024-03-10');",
        "insert into debito " +
                "(id, pessoa, datalancamento)" +
                "values(2, 1, '2024-03-10');",
        "insert into debitoparcela " +
                "(id, iddebito, numeroparcela, datavencimento, situacaoparcela, valorparcela)" +
                "values(1, 1, 1, '2024-03-14','A',1000.00);",
        "insert into debitoparcela " +
                "(id, iddebito, numeroparcela, datavencimento, situacaoparcela, valorparcela)" +
                "values(2, 1, 2, '2024-04-14','A',1500.00);",
        "insert into debitoparcela " +
                "(id, iddebito, numeroparcela, datavencimento, situacaoparcela, valorparcela)" +
                "values(3, 2, 1, '2024-05-15','A',10000.00);",
        "insert into cancelamento " +
                "(id, numerocancelamento, datacancelamento, motivo) " +
                "values(1, 1, '2024-02-13','teste cancelamento');",
        "insert into cancelamentoitem " +
                "(id, cancelamento, debitoparcela, valorcancelado) " +
                "values(1, 1, 3 , 10000.00);"
})
class CancelamentoRepositoryIntegrationTest {

    @Autowired
    private CancelamentoRepository cancelamentoRepository;

    @Test
    void findAll_ReturnsCancelamentos() {
        List<Cancelamento> cancelamentos = cancelamentoRepository.findAll();
        assertTrue(!cancelamentos.isEmpty());
    }
}