package elotech.com.br.oxydebitos.resource;

import elotech.com.br.oxydebitos.dto.PessoaDTO;
import elotech.com.br.oxydebitos.service.PessoaService;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pessoa")
@RequiredArgsConstructor
@Data
public class PessoaResource {
    private final PessoaService service;

    @GetMapping("/{id}")
    public ResponseEntity<PessoaDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(this.getService().findById(id));
    }

    @GetMapping("/cnpjCpf")
    public ResponseEntity<List<PessoaDTO>> findByCnpjCpf(@RequestParam String cnpjCpf) {
        return ResponseEntity.ok(this.getService().findByCnpjCpf(cnpjCpf));
    }

    @GetMapping
    public ResponseEntity<Page<PessoaDTO>> findAll(Pageable pageable) {
        return ResponseEntity.ok(this.getService().findAll(pageable));
    }

    @PostMapping
    public ResponseEntity<PessoaDTO>
    save(@RequestBody @Valid PessoaDTO dto)  {
        return ResponseEntity.ok(getService().save(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PessoaDTO> update(@PathVariable Long id, @RequestBody PessoaDTO pessoaRequestDTO) {
        return ResponseEntity.ok(this.getService().update(id, pessoaRequestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.getService().delete(id);

        return ResponseEntity.noContent().build();
    }

}
