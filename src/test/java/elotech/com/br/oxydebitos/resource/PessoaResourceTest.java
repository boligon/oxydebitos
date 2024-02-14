package elotech.com.br.oxydebitos.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import elotech.com.br.oxydebitos.dto.PessoaDTO;
import elotech.com.br.oxydebitos.service.PessoaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class PessoaResourceTest {

    private MockMvc mockMvc;

    @Mock
    private PessoaService pessoaService;

    @InjectMocks
    private PessoaResource pessoaResource;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(pessoaResource).build();
    }

    @Test
    void testFindById() throws Exception {
        PessoaDTO pessoaDTO = new PessoaDTO();
        pessoaDTO.setId(1L);
        pessoaDTO.setNome("Rafael");
        when(pessoaService.findById(1L)).thenReturn(pessoaDTO);

        mockMvc.perform(get("/api/pessoa/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nome").value("Rafael"));
    }

    @Test
    void testFindByCnpjCpf() throws Exception {
        PessoaDTO pessoaDTO = new PessoaDTO();
        pessoaDTO.setId(1L);
        pessoaDTO.setNome("Rafael");
        when(pessoaService.findByCnpjCpf("39921158015")).thenReturn(Collections.singletonList(pessoaDTO));

        mockMvc.perform(get("/api/pessoa/cnpjCpf").param("cnpjCpf", "39921158015"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].nome").value("Rafael"));
    }

    @Test
    void testSave() throws Exception {
        PessoaDTO pessoaDTO = new PessoaDTO();
        pessoaDTO.setId(1L);
        pessoaDTO.setNome("Rafael");
        when(pessoaService.save(any(PessoaDTO.class))).thenReturn(pessoaDTO);

        mockMvc.perform(post("/api/pessoa")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(pessoaDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nome").value("Rafael"));
    }

    @Test
    void testUpdate() throws Exception {
        PessoaDTO pessoaDTO = new PessoaDTO();
        pessoaDTO.setId(1L);
        pessoaDTO.setNome("Rafael Atualizado");
        when(pessoaService.update(eq(1L), any(PessoaDTO.class))).thenReturn(pessoaDTO);

        mockMvc.perform(put("/api/pessoa/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(pessoaDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nome").value("Rafael Atualizado"));
    }

    @Test
    void testDelete() throws Exception {
        mockMvc.perform(delete("/api/pessoa/1"))
                .andExpect(status().isNoContent());

        verify(pessoaService, times(1)).delete(1L);
    }
}
