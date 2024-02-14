package elotech.com.br.oxydebitos.repository;

import elotech.com.br.oxydebitos.domain.Pagamento;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class PagamentoRepositoryIntTest {

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Test
    void testSave() {
        Pagamento pagamento = new Pagamento();
        pagamento.setId(1L);
        pagamento.setNumeroPagamento(1L);
        pagamento.setDataPagamento(LocalDate.now());

        Pagamento savedPagamento = pagamentoRepository.save(pagamento);
        Pagamento retrievedPagamento = pagamentoRepository.findById(savedPagamento.getId()).orElse(null);

        assertEquals(savedPagamento, retrievedPagamento);
    }

    @Test
    void testFindAll() {
        List<Pagamento> pagamentos = pagamentoRepository.findAll();

        assertEquals(0, pagamentos.size());
    }
}
