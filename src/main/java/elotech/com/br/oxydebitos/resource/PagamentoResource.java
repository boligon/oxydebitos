package elotech.com.br.oxydebitos.resource;

import elotech.com.br.oxydebitos.dto.PagamentoDTO;
import elotech.com.br.oxydebitos.dto.PagamentoPostDTO;
import elotech.com.br.oxydebitos.service.PagamentoService;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pagamento")
@RequiredArgsConstructor
@Data
public class PagamentoResource {
    private final PagamentoService service;

    @GetMapping("/{id}")
    public ResponseEntity<PagamentoDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(this.getService().findById(id));
    }

    @GetMapping
    public ResponseEntity<Page<PagamentoDTO>> findAll(Pageable pageable) {
        return ResponseEntity.ok(this.getService().findAll(pageable));
    }

    @PostMapping
    public ResponseEntity<PagamentoDTO>
    save(@RequestBody @Valid PagamentoPostDTO dto) {
        return ResponseEntity.ok(getService().save(dto));
    }

}
