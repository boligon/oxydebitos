package elotech.com.br.oxydebitos.service;

import elotech.com.br.oxydebitos.domain.Debito;
import elotech.com.br.oxydebitos.domain.Pessoa;
import elotech.com.br.oxydebitos.dto.DebitoDTO;
import elotech.com.br.oxydebitos.dto.DebitoPostDTO;
import elotech.com.br.oxydebitos.dto.ParcelaDTO;
import elotech.com.br.oxydebitos.repository.DebitoRepository;
import elotech.com.br.oxydebitos.repository.PessoaRepository;
import elotech.com.br.oxydebitos.validator.DebitoParcelaValidator;
import elotech.com.br.oxydebitos.validator.DebitoValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DebitoServiceTest {

    @Mock
    private DebitoRepository debitoRepository;

    @Mock
    private PessoaRepository pessoaRepository;

    @Mock
    private DebitoValidator debitoValidator;

    @Mock
    private DebitoParcelaValidator debitoParcelaValidator;

    @Mock
    private DebitoParcelaService debitoParcelaService;

    @InjectMocks
    private DebitoService debitoService;

    @Test
    void testFindById() {

        Long id = 1L;
        Debito debito = new Debito();
        debito.setId(1L);
        debito.setDataLancamento(LocalDate.of(2024,2,20));

        when(debitoRepository.findById(id)).thenReturn(Optional.of(debito));

        DebitoDTO result = debitoService.findById(id);

        assertNotNull(result);

    }

    @Test
    void testFindAll() {
        List<Debito> debitos = new ArrayList<>();

        Page<Debito> page = new PageImpl<>(debitos);
        when(debitoRepository.findAll(any(Pageable.class))).thenReturn(page);

        Page<DebitoDTO> result = debitoService.findAll(Pageable.unpaged());

        assertNotNull(result);
    }

    @Test
    void testSave() throws Exception {
        Long pessoaId = 1L;
        LocalDate dataLancamento = LocalDate.now();
        Double valorParcela = 100.0;

        Pessoa pessoa = new Pessoa();
        pessoa.setId(pessoaId);
        pessoa.setNome("Rafael");
        pessoa.setCnpjCpf("39921158015");
        when(pessoaRepository.findById(pessoaId)).thenReturn(Optional.of(pessoa));

        DebitoPostDTO dto = new DebitoPostDTO();
        dto.setPessoa(pessoaId);
        dto.setDataLancamento(dataLancamento);
        List<ParcelaDTO> parcelas = new ArrayList<>();

        ParcelaDTO parcelaDTO = new ParcelaDTO();
        parcelaDTO.setId(1L);
        parcelaDTO.setDataVencimento(LocalDate.now());
        parcelaDTO.setValorParcela(BigDecimal.valueOf(1000.00));

        parcelas.add(parcelaDTO);
        dto.setParcelas(parcelas);

        Debito debito = new Debito();
        when(debitoRepository.save(any(Debito.class))).thenReturn(debito);

        doNothing().when(debitoValidator).validaDebito(dto);
        doNothing().when(debitoParcelaValidator).validaDebitoParcela(dto);

        DebitoDTO result = debitoService.save(dto);

        assertNotNull(result);
    }

}