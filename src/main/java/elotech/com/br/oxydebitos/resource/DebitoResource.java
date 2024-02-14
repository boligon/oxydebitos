package elotech.com.br.oxydebitos.resource;

import elotech.com.br.oxydebitos.dto.DebitoDTO;
import elotech.com.br.oxydebitos.dto.DebitoPostDTO;
import elotech.com.br.oxydebitos.dto.NovaParcelaDebitoDTO;
import elotech.com.br.oxydebitos.service.DebitoService;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/debito")
@RequiredArgsConstructor
@Data
public class DebitoResource {

    private final DebitoService service;

    @GetMapping("/{id}")
    public ResponseEntity<DebitoDTO> findById(@PathVariable @Valid Long id) {
        return ResponseEntity.ok(this.getService().findById(id));
    }

    @GetMapping
    public ResponseEntity<Page<DebitoDTO>> findAll(Pageable pageable) {
        return ResponseEntity.ok(this.getService().findAll(pageable));
    }

    @RequestMapping("/valortotal")
    @GetMapping
    public ResponseEntity<Double> getValorTotalDebitos() {
        Double valorTotalDebitos = this.getService().getValorTotalDebitos();

        return ResponseEntity.ok(valorTotalDebitos);
    }

    @PostMapping
    public ResponseEntity<DebitoDTO>
    save(@RequestBody @Valid DebitoPostDTO dto) throws Exception {
        return ResponseEntity.ok(getService().save(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DebitoDTO> update(@PathVariable @Valid Long id, @RequestBody @Valid DebitoDTO debitoRequestDTO) {
        return ResponseEntity.ok(this.getService().update(id, debitoRequestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable @Valid Long id) {
        this.getService().delete(id);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("{idDebito}/inserir-parcela")
    public ResponseEntity<DebitoDTO> inserirParcelaDebito(@PathVariable(name = "idDebito") @Valid Long idDebito,
                                                       @RequestBody @Valid NovaParcelaDebitoDTO novaParcelaDebitoDTO) throws Exception {

        return ResponseEntity.ok(this.getService().inserirNovaParcelaDebito(idDebito, novaParcelaDebitoDTO));
    }
}
