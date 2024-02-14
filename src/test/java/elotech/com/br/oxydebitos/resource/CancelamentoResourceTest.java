package elotech.com.br.oxydebitos.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import elotech.com.br.oxydebitos.dto.CancelamentoDTO;
import elotech.com.br.oxydebitos.dto.CancelamentoPostDTO;
import elotech.com.br.oxydebitos.service.CancelamentoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CancelamentoResourceTest {

    private MockMvc mockMvc;

    @Mock
    private CancelamentoService cancelamentoService;

    @InjectMocks
    private CancelamentoResource cancelamentoResource;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(cancelamentoResource).build();
    }

    @Test
    void testFindById() throws Exception {
        CancelamentoDTO cancelamentoDTO = new CancelamentoDTO();
        cancelamentoDTO.setId(1L);
        when(cancelamentoService.findById(anyLong())).thenReturn(cancelamentoDTO);

        mockMvc.perform(get("/api/cancelamento/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void testSave() throws Exception {
        CancelamentoPostDTO cancelamentoPostDTO = new CancelamentoPostDTO();
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(cancelamentoPostDTO);
        when(cancelamentoService.save(any(CancelamentoPostDTO.class))).thenReturn(new CancelamentoDTO());

        mockMvc.perform(post("/api/cancelamento")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());
    }
}
