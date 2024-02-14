package elotech.com.br.oxydebitos.service;

import elotech.com.br.oxydebitos.domain.Pessoa;
import elotech.com.br.oxydebitos.dto.PessoaDTO;
import elotech.com.br.oxydebitos.repository.PessoaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class PessoaServiceTest {

    @Mock
    private PessoaRepository pessoaRepository;

    @InjectMocks
    private PessoaService pessoaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindById() {
        Long id = 1L;
        Pessoa pessoa = new Pessoa();
        pessoa.setId(id);
        pessoa.setNome("Rafael");
        pessoa.setCnpjCpf("39921158015");
        when(pessoaRepository.findById(id)).thenReturn(Optional.of(pessoa));

        PessoaDTO result = pessoaService.findById(id);

        assertNotNull(result);
        assertEquals(id, result.getId());
        assertEquals("Rafael", result.getNome());
        assertEquals("39921158015", result.getCnpjCpf());
    }

    @Test
    void testFindByCnpjCpf() {
        String cnpjCpf = "39921158015";
        List<Pessoa> pessoas = new ArrayList<>();
        Pessoa pessoa1 = new Pessoa();
        pessoa1.setId(1L);
        pessoa1.setNome("Rafael1");
        pessoa1.setCnpjCpf(cnpjCpf);
        Pessoa pessoa2 = new Pessoa();
        pessoa2.setId(2L);
        pessoa2.setNome("Rafael2");
        pessoa2.setCnpjCpf(cnpjCpf);
        pessoas.add(pessoa1);
        pessoas.add(pessoa2);
        when(pessoaRepository.findByCnpjCpf(cnpjCpf)).thenReturn(pessoas);

        List<PessoaDTO> result = pessoaService.findByCnpjCpf(cnpjCpf);

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).getId());
        assertEquals("Rafael1", result.get(0).getNome());
        assertEquals(2L, result.get(1).getId());
        assertEquals("Rafael2", result.get(1).getNome());
    }

    @Test
    void testFindAll() {
        List<Pessoa> pessoas = new ArrayList<>();

        Pessoa pessoa = new Pessoa();
        pessoa.setId(1L);
        pessoa.setNome("Rafael1");
        pessoa.setCnpjCpf("47885970043");
        pessoas.add(pessoa);

        Pessoa pessoa2 = new Pessoa();
        pessoa2.setId(2L);
        pessoa2.setNome("Rafael2");
        pessoa2.setCnpjCpf("47885970043");
        pessoas.add(pessoa2);

        Page<Pessoa> page = new PageImpl<>(pessoas);
        Pageable pageable = mock(Pageable.class);
        when(pessoaRepository.findAll(pageable)).thenReturn(page);

        Page<PessoaDTO> result = pessoaService.findAll(pageable);

        assertNotNull(result);
        assertEquals(2, result.getTotalElements());
        assertEquals(1L, result.getContent().get(0).getId());
        assertEquals("Rafael1", result.getContent().get(0).getNome());
        assertEquals(2L, result.getContent().get(1).getId());
        assertEquals("Rafael2", result.getContent().get(1).getNome());
    }

    @Test
    void testSave() {
        PessoaDTO dto = new PessoaDTO();
        dto.setNome("Rafael");
        dto.setCnpjCpf("47885970043");

        Pessoa pessoaToSave = new Pessoa();
        pessoaToSave.setNome("Rafael");
        pessoaToSave.setCnpjCpf("47885970043");

        Pessoa savedPessoa = new Pessoa();
        savedPessoa.setId(1L);
        savedPessoa.setNome("Rafael");
        savedPessoa.setCnpjCpf("47885970043");

        when(pessoaRepository.save(any(Pessoa.class))).thenReturn(savedPessoa);

        PessoaDTO result = pessoaService.save(dto);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Rafael", result.getNome());
        assertEquals("47885970043", result.getCnpjCpf());
    }

    @Test
    void testDelete() {
        Long id = 1L;
        Pessoa pessoaToDelete = new Pessoa();
        pessoaToDelete.setId(id);
        pessoaToDelete.setNome("Rafael");
        pessoaToDelete.setCnpjCpf("47885970043");
        when(pessoaRepository.findById(id)).thenReturn(Optional.of(pessoaToDelete));

        pessoaService.delete(id);

        verify(pessoaRepository, times(1)).delete(pessoaToDelete);
    }

    @Test
    void testUpdate() {
        Long id = 1L;
        PessoaDTO requestPessoaDTO = new PessoaDTO();
        requestPessoaDTO.setId(id);
        requestPessoaDTO.setNome("Rafael Atualizado");
        requestPessoaDTO.setCnpjCpf("39921158015");

        Pessoa existingPessoa = new Pessoa();
        existingPessoa.setId(id);
        existingPessoa.setNome("Rafael");
        existingPessoa.setCnpjCpf("47885970043");
        when(pessoaRepository.findById(id)).thenReturn(Optional.of(existingPessoa));

        Pessoa updatedPessoa = new Pessoa();
        updatedPessoa.setId(id);
        updatedPessoa.setNome("Rafael Atualizado");
        updatedPessoa.setCnpjCpf("39921158015");
        when(pessoaRepository.save(any(Pessoa.class))).thenReturn(updatedPessoa);

        PessoaDTO result = pessoaService.update(id, requestPessoaDTO);

        assertNotNull(result);
        assertEquals(id, result.getId());
        assertEquals("Rafael Atualizado", result.getNome());
        assertEquals("39921158015", result.getCnpjCpf());
    }

}
