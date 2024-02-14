package elotech.com.br.oxydebitos.resource;

import elotech.com.br.oxydebitos.dto.DebitoDTO;
import elotech.com.br.oxydebitos.service.DebitoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@RestController
@RequestMapping("   /api/extrato-debito")
public class ExtratoDebitoResource {

    private final DebitoService service;

    @GetMapping("/pessoa/{id}")
    public ResponseEntity<Page<DebitoDTO>> findAllByPessoa(Pageable pageable, @PathVariable @Valid Long id){
        return ResponseEntity.ok(this.service.findAllByPessoa(pageable, id));
    }

    @GetMapping("/pessoa")
    public ResponseEntity<Page<DebitoDTO>> findAllByCnpjCpf(Pageable pageable,
                                                            @RequestParam String cnpjCpf){
        return ResponseEntity.ok(this.service.findAllByCnpjCpf(pageable, cnpjCpf));
    }

    @GetMapping("/datalancamento")
    public ResponseEntity<Page<DebitoDTO>> findAllByPeriodo(Pageable pageable,
                                                            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicial,
                                                            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFinal){
        return ResponseEntity.ok(this.getService().findAllByPeriodoDataLancamento(pageable, dataInicial, dataFinal));
    }

    @GetMapping("/pessoa_datalancamento")
    public ResponseEntity<Page<DebitoDTO>> findAllByPessoaAndPeriodo(Pageable pageable,
                                                            @RequestParam String nome,
                                                            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicial,
                                                            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFinal){
        return ResponseEntity.ok(this.getService().findAllByPeriodoDataLancamentoAndPessoa(pageable, nome, dataInicial, dataFinal));
    }

    @GetMapping("/situacaoParcela")
    public ResponseEntity<Page<DebitoDTO>> findAllBySituacaoParcela(Pageable pageable,
                                                                    @RequestParam String situacao){
        return ResponseEntity.ok(this.service.findAllBySituacaoParcela(pageable, situacao));
    }

}
