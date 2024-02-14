package elotech.com.br.oxydebitos.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import elotech.com.br.oxydebitos.dto.PagamentoDTO;
import elotech.com.br.oxydebitos.dto.PagamentoPostDTO;
import elotech.com.br.oxydebitos.service.PagamentoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class PagamentoResourceTest {

    private MockMvc mockMvc;

    @Mock
    private PagamentoService pagamentoService;

    @InjectMocks
    private PagamentoResource pagamentoResource;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(pagamentoResource).build();
    }

    @Test
    void testFindById() throws Exception {
        PagamentoDTO pagamentoDTO = new PagamentoDTO();
        pagamentoDTO.setId(1L);
        pagamentoDTO.setNumeroPagamento(1L);
        when(pagamentoService.findById(1L)).thenReturn(pagamentoDTO);

        mockMvc.perform(get("/api/pagamento/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.numeroPagamento").value(1L));
    }

    @Test
    void testSave() throws Exception {
        PagamentoPostDTO pagamentoPostDTO = new PagamentoPostDTO();
        pagamentoPostDTO.setId(1L);
        pagamentoPostDTO.setNumeroPagamento(1L);
        PagamentoDTO pagamentoDTO = new PagamentoDTO();
        pagamentoDTO.setId(1L);
        pagamentoDTO.setNumeroPagamento(1L);
        when(pagamentoService.save(any(PagamentoPostDTO.class))).thenReturn(pagamentoDTO);

        mockMvc.perform(post("/api/pagamento")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(pagamentoPostDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.numeroPagamento").value(1L));
    }
}
