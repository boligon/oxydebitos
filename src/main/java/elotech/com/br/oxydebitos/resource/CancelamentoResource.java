package elotech.com.br.oxydebitos.resource;

import elotech.com.br.oxydebitos.dto.CancelamentoDTO;
import elotech.com.br.oxydebitos.dto.CancelamentoPostDTO;
import elotech.com.br.oxydebitos.service.CancelamentoService;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cancelamento")
@RequiredArgsConstructor
@Data
public class CancelamentoResource {

    private final CancelamentoService service;

    @GetMapping("/{id}")
    public ResponseEntity<CancelamentoDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(this.getService().findById(id));
    }

    @GetMapping
    public ResponseEntity<Page<CancelamentoDTO>> findAll(Pageable pageable) {
        return ResponseEntity.ok(this.getService().findAll(pageable));
    }

    @PostMapping
    public ResponseEntity<CancelamentoDTO>
    save(@RequestBody @Valid CancelamentoPostDTO dto) { return ResponseEntity.ok(getService().save(dto)); }

}
