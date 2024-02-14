package elotech.com.br.oxydebitos.service;

import elotech.com.br.oxydebitos.domain.Cancelamento;
import elotech.com.br.oxydebitos.dto.CancelamentoDTO;
import elotech.com.br.oxydebitos.dto.CancelamentoPostDTO;
import elotech.com.br.oxydebitos.dto.DebitoDTO;
import elotech.com.br.oxydebitos.repository.CancelamentoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class CancelamentoServiceTest {

    @Mock
    private CancelamentoRepository cancelamentoRepository;

    @Mock
    private DebitoService debitoService;

    @InjectMocks
    private CancelamentoService cancelamentoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindById() {
        Long id = 1L;
        Cancelamento cancelamento = new Cancelamento();
        cancelamento.setId(id);
        when(cancelamentoRepository.findById(id)).thenReturn(Optional.of(cancelamento));

        CancelamentoDTO result = cancelamentoService.findById(id);

        assertNotNull(result);
        assertEquals(id, result.getId());
    }

    @Test
    void testSave() {
        CancelamentoPostDTO dto = new CancelamentoPostDTO();
        dto.setItens(new ArrayList<>());

        DebitoDTO debitoDTO = new DebitoDTO();
        debitoDTO.setId(1L);
        debitoDTO.setDataLancamento(LocalDate.of(2024,2,20));

        when(debitoService.findById(anyLong())).thenReturn(debitoDTO);
        when(cancelamentoRepository.save(any(Cancelamento.class))).thenReturn(new Cancelamento());

        CancelamentoDTO result = cancelamentoService.save(dto);

        assertNotNull(result);
    }

}
