package elotech.com.br.oxydebitos.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import elotech.com.br.oxydebitos.dto.ParcelaDTO;
import elotech.com.br.oxydebitos.dto.VencimentoParcelaDTO;
import elotech.com.br.oxydebitos.service.DebitoParcelaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class DebitoParcelaResourceTest {

    private MockMvc mockMvc;

    @Mock
    private DebitoParcelaService debitoParcelaService;

    @InjectMocks
    private DebitoParcelaResource debitoParcelaResource;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(debitoParcelaResource).build();
    }

    @Test
    void alterarVencimentoParcelas_ReturnsParcelaDTO() throws Exception {
        VencimentoParcelaDTO vencimentoParcelaDTO = new VencimentoParcelaDTO();
        vencimentoParcelaDTO.setNumeroParcela(1L);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(vencimentoParcelaDTO);
        when(debitoParcelaService.updateVencimentoParcela(anyLong(), any(VencimentoParcelaDTO.class))).thenReturn(new ParcelaDTO());

        mockMvc.perform(post("/api/debito/1/altera-vencimento")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());
    }
}
