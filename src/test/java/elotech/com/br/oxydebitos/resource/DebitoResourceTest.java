package elotech.com.br.oxydebitos.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import elotech.com.br.oxydebitos.dto.DebitoDTO;
import elotech.com.br.oxydebitos.dto.DebitoPostDTO;
import elotech.com.br.oxydebitos.service.DebitoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class DebitoResourceTest {

    private MockMvc mockMvc;

    @Mock
    private DebitoService debitoService;

    @InjectMocks
    private DebitoResource debitoResource;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(debitoResource).build();
    }

    @Test
    void testFindById() throws Exception {
        DebitoDTO debitoDTO = new DebitoDTO();
        debitoDTO.setId(1L);
        when(debitoService.findById(anyLong())).thenReturn(debitoDTO);

        mockMvc.perform(get("/api/debito/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void testGetValorTotalDebitos() throws Exception {
        when(debitoService.getValorTotalDebitos()).thenReturn(100.0);

        mockMvc.perform(get("/api/debito/valortotal"))
                .andExpect(status().isOk())
                .andExpect(content().string("100.0"));
    }

    @Test
    void testSave() throws Exception {
        DebitoPostDTO debitoPostDTO = new DebitoPostDTO();
        debitoPostDTO.setPessoa(1L);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(debitoPostDTO);
        when(debitoService.save(any(DebitoPostDTO.class))).thenReturn(new DebitoDTO());

        mockMvc.perform(post("/api/debito")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdate() throws Exception {
        DebitoDTO debitoDTO = new DebitoDTO();
        debitoDTO.setId(1L);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(debitoDTO);
        when(debitoService.update(anyLong(), any(DebitoDTO.class))).thenReturn(debitoDTO);

        mockMvc.perform(put("/api/debito/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void testDelete() throws Exception {
        doNothing().when(debitoService).delete(anyLong());

        mockMvc.perform(delete("/api/debito/1"))
                .andExpect(status().isNoContent());
    }

}
