package elotech.com.br.oxydebitos.service;

import elotech.com.br.oxydebitos.domain.Pagamento;
import elotech.com.br.oxydebitos.dto.PagamentoDTO;
import elotech.com.br.oxydebitos.dto.PagamentoPostDTO;
import elotech.com.br.oxydebitos.repository.PagamentoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PagamentoServiceTest {

    @Mock
    private PagamentoRepository pagamentoRepository;

    @Mock
    private DebitoParcelaService debitoParcelaService;

    @Mock
    private DebitoService debitoService;

    @InjectMocks
    private PagamentoService pagamentoService;

    @Test
    public void testFindById() {
        Pagamento pagamento = new Pagamento();
        when(pagamentoRepository.findById(1L)).thenReturn(Optional.of(pagamento));

        PagamentoDTO result = pagamentoService.findById(1L);

        assertNotNull(result);
    }

    @Test
    public void testSave() {

        PagamentoPostDTO dto = new PagamentoPostDTO();

        Pagamento pagamento = new Pagamento();
        when(pagamentoRepository.save(any())).thenReturn(pagamento);

        PagamentoDTO result = pagamentoService.save(dto);

        assertNotNull(result);

    }
}
