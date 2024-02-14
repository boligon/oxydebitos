package elotech.com.br.oxydebitos.resource;

import elotech.com.br.oxydebitos.dto.ParcelaDTO;
import elotech.com.br.oxydebitos.dto.VencimentoParcelaDTO;
import elotech.com.br.oxydebitos.service.DebitoParcelaService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/debito")
@RequiredArgsConstructor
@Data
public class DebitoParcelaResource {
    private final DebitoParcelaService service;

    @PostMapping("{idDebito}/altera-vencimento")
    public ResponseEntity<ParcelaDTO> alterarVencimentoParcelas(@PathVariable(name = "idDebito") Long idDebito,
                                                                      @RequestBody VencimentoParcelaDTO vencimentoParcelaDTO) {

        return ResponseEntity.ok(this.getService().updateVencimentoParcela(idDebito, vencimentoParcelaDTO));
    }

}
